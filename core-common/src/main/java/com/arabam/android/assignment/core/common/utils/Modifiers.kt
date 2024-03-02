package com.arabam.android.assignment.core.common.utils

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

fun Modifier.shimmerModifier(
    widthOfShadowBrush: Int = 300,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
    shape: Shape = RoundedCornerShape(16.dp)
): Modifier {
    return composed {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.3f),
            Color.LightGray.copy(alpha = 0.5f),
            Color.LightGray.copy(alpha = 1.0f),
            Color.LightGray.copy(alpha = 0.5f),
            Color.LightGray.copy(alpha = 0.3f),
        )

        val transition = rememberInfiniteTransition(label = "")

        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart,
            ),
            label = "Shimmer loading animation",
        )
        background(
            brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                end = Offset(x = translateAnimation.value, y = angleOfAxisY),
            ),
            shape = shape
        )
    }
}