package hitsedu.interpreter.processor

import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.interpreter.syntax.Parser

fun OperationVariable.process(
    variables: MutableList<OperationVariable>,
    arrays: MutableList<OperationArray>
): OperationVariable {
    if (value.value.startsWith("\"") && value.value.endsWith("\"")) {
        return this
    }

    if (value.value == "true" || value.value == "false") {
        return this
    }

    fun resolve(name: String): Int {
        return variables.find { it.name == name }?.value?.value?.toIntOrNull()
            ?: arrays.firstOrNull { array ->
                name.startsWith(array.name + "[") && name.endsWith("]")
            }?.let { array ->
                val indexStr = name.substring(array.name.length + 1, name.length - 1)
                val index = indexStr.toIntOrNull() ?: return@let null
                array.values.getOrNull(index)?.value?.toIntOrNull()
            } ?: error("Cannot resolve value: $name")
    }

    fun assignVar(name: String, value: Int) {
        val index = variables.indexOfFirst { it.name == name }
        if (index != -1) {
            variables[index] = variables[index].copy(value = Value(value.toString()))
        } else {
            variables.add(OperationVariable(name, Value(value.toString())))
        }
    }

    fun assignArray(name: String, index: Int, value: Int) {
        val array = arrays.find { it.name == name } ?: error("Array $name not found")
        if (index !in array.values.indices) error("Index $index out of bounds for array $name")

        val newValues = array.values.toMutableList().apply {
            this[index] = Value(value.toString())
        }
        arrays[arrays.indexOf(array)] = array.copy(values = newValues)
    }

    val result = Parser.parseAssignment(
        exp = this.value.value,
        resolve = ::resolve,
        assignVar = ::assignVar,
        assignArray = ::assignArray
    )

    return this.copy(value = Value(result.toString()))
}