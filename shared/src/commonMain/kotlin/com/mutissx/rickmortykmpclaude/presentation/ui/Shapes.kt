package com.mutissx.rickmortykmpclaude.presentation.ui

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

/** clip-path: polygon(0 0, 90% 0, 100% 10%, 100% 100%, 10% 100%, 0 90%) */
object ClipNotchShape : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val notch = 0.10f
        return Outline.Generic(Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width * (1f - notch), 0f)
            lineTo(size.width, size.height * notch)
            lineTo(size.width, size.height)
            lineTo(size.width * notch, size.height)
            lineTo(0f, size.height * (1f - notch))
            close()
        })
    }
}

/** clip-path: polygon(0 0, 90% 0, 100% 10%, 100% 100%, 0 100%) — top-right chamfer only */
object ClipCornerShape : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val notch = 0.10f
        return Outline.Generic(Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width * (1f - notch), 0f)
            lineTo(size.width, size.height * notch)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        })
    }
}
