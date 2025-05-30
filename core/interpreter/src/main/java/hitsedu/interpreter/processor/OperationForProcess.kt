package hitsedu.interpreter.processor

import hitsedu.interpreter.models.ConsoleOutput
import hitsedu.interpreter.models.E
import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationArrayIndex
import hitsedu.interpreter.models.operation.OperationFor
import hitsedu.interpreter.models.operation.OperationIf
import hitsedu.interpreter.models.operation.OperationOutput
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.interpreter.syntax.ParserLogic
import hitsedu.interpreter.utils.Type

fun OperationFor.process(
    variables: MutableList<OperationVariable>,
    arrays: MutableList<OperationArray>,
    console: MutableList<ConsoleOutput>
): E? {
    val outputs = mutableListOf<ConsoleOutput>()
    val initParts = variable.value.split("=").map { it.trim() }
    if (initParts.size != 2) {
        val error = E("Invalid for loop initialization: ${variable.value}", id)
        return error
    }
    val varName = initParts[0]
    val initValue = initParts[1]

    val processedInitValue = try {
        Value(initValue).process(variables, arrays)?.also {
        } ?: run {
            val error = E("Failed to process initial value: $initValue", id)
            return error
        }
    } catch (e: Exception) {
        val error = E("Error processing initial value: ${e.message}", id)
        return error
    }

    variables.find { it.name == varName }?.let {
        variables[variables.indexOf(it)] = it.copy(value = processedInitValue)
    } ?: run {
        variables.add(OperationVariable(varName, processedInitValue))
    }

    var iteration = 0
    while (true) {
        iteration++

        val conditionResult = try {
            ParserLogic.parseLogicExpression(
                condition.value,
                resolve = { name ->
                    Value(name).process(variables, arrays)?.let { value ->
                        val result = when (value.type) {
                            Type.BOOLEAN -> value.value.toBoolean()
                            Type.INT -> value.value.toInt()
                            Type.DOUBLE -> value.value.toDouble()
                            else -> throw Exception("Condition must evaluate to boolean or number")
                        }
                        result
                    } ?: throw Exception("Cannot resolve value: $name")
                }
            )
        } catch (e: Exception) {
            val error = E("Error in for loop condition: ${e.message}", id)
            return error
        }

        if (!conditionResult) {
            break
        }

        for (op in scope.operations) {
            when (val result = when (op) {
                is OperationVariable -> {
                    op.process(variables, arrays)
                }

                is OperationArray -> {
                    op.process(variables, arrays)
                }

                is OperationArrayIndex -> {
                    op.process(variables, arrays)
                }

                is OperationOutput -> {
                    val output = op.process(variables, arrays)
                    outputs.add(output)
                    output.exception?.let { return it }
                    null
                }

                is OperationIf -> {
                    when (val conditionResult = op.process(variables, arrays)) {
                        true -> {
                            for (innerOp in op.scope.operations) {
                                when (val innerResult = when (innerOp) {
                                    is OperationVariable -> innerOp.process(variables, arrays)
                                    is OperationArray -> innerOp.process(variables, arrays)
                                    is OperationArrayIndex -> innerOp.process(variables, arrays)
                                    is OperationOutput -> innerOp.process(variables, arrays).exception
                                    is OperationIf -> innerOp.process(variables, arrays)
                                        ?.let { E("If condition failed", innerOp.id) }

                                    is OperationFor -> innerOp.process(variables, arrays, console)
                                    else -> E("Unsupported operation in if body", innerOp.id)
                                }) {
                                    null -> continue
                                    else -> return innerResult
                                }
                            }
                            null
                        }

                        false -> {
                            null
                        }

                        else -> conditionResult?.let { E("If condition evaluation failed", op.id) }
                            ?: E("Unknown if condition error", op.id)
                    }
                }

                is OperationFor -> {
                    op.process(variables, arrays, console)
                }

                else -> {
                    val error = E("Unsupported operation in for loop body", op.id)
                    error
                }
            }) {
                null -> {
                    continue
                }

                else -> {
                    return result
                }
            }
        }

        val stepExpression = value.value
        val processedStepValue = try {
            val currentVar = variables.find { it.name == varName }
                ?: return E("Loop variable $varName not found", id)

            val currentValue = currentVar.value.value

            val expressionToProcess = stepExpression.replace(varName, currentValue)

            Value(expressionToProcess).process(variables, arrays) ?: return E("Failed to process step value: $stepExpression", id)
        } catch (e: Exception) {
            val error = E("Error processing step value: ${e.message}", id)
            return error
        }

        variables.find { it.name == varName }?.let {
            variables[variables.indexOf(it)] = it.copy(value = processedStepValue)
        } ?: return E("Loop variable $varName not found", id)
    }
    outputs.forEach { console.add(it) }
    return null
}