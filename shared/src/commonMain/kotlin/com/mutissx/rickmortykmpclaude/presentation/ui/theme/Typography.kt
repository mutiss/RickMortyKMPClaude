package com.mutissx.rickmortykmpclaude.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import rickmortykmpclaude.shared.generated.resources.Res
import rickmortykmpclaude.shared.generated.resources.jetbrains_mono_bold
import rickmortykmpclaude.shared.generated.resources.jetbrains_mono_regular
import rickmortykmpclaude.shared.generated.resources.sora_variable

@Composable
fun soraFamily() = FontFamily(
    Font(Res.font.sora_variable, FontWeight.Normal),
    Font(Res.font.sora_variable, FontWeight.Medium),
    Font(Res.font.sora_variable, FontWeight.SemiBold),
    Font(Res.font.sora_variable, FontWeight.Bold),
    Font(Res.font.sora_variable, FontWeight.ExtraBold)
)

@Composable
fun jetBrainsMonoFamily() = FontFamily(
    Font(Res.font.jetbrains_mono_regular, FontWeight.Normal),
    Font(Res.font.jetbrains_mono_bold, FontWeight.Bold)
)

@Composable
fun appTypography(): Typography {
    val sora = soraFamily()
    val mono = jetBrainsMonoFamily()
    return Typography(
        displayLarge = TextStyle(
            fontFamily = sora,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 40.sp,
            lineHeight = 48.sp,
            letterSpacing = (-0.8).sp
        ),
        displayMedium = TextStyle(
            fontFamily = sora,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = (-0.32).sp
        ),
        displaySmall = TextStyle(
            fontFamily = sora,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = sora,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = (-0.32).sp
        ),
        headlineMedium = TextStyle(
            fontFamily = sora,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = sora,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 28.sp
        ),
        titleLarge = TextStyle(
            fontFamily = sora,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 28.sp
        ),
        titleMedium = TextStyle(
            fontFamily = mono,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        titleSmall = TextStyle(
            fontFamily = mono,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = mono,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 28.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = mono,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        bodySmall = TextStyle(
            fontFamily = mono,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        labelLarge = TextStyle(
            fontFamily = mono,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.2.sp
        ),
        labelMedium = TextStyle(
            fontFamily = mono,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.2.sp
        ),
        labelSmall = TextStyle(
            fontFamily = mono,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            letterSpacing = 1.0.sp
        )
    )
}
