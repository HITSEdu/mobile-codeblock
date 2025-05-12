package hitsedu.ui_kit.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import hitsedu.ui_kit.R

val Tektur = FontFamily(
    Font(R.font.tektur)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Tektur,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Tektur,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = Tektur,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Tektur,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = Tektur,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = Tektur,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),


)