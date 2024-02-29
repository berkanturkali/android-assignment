package com.arabam.android.assignment.feature.listing.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R

@Composable
fun ArabamCheckBox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    label: String? = null,
    size: Dp = 18.dp,
    shape: Shape = CircleShape,
    checkedColor: Color = colorResource(id = R.color.primary_color),
    unCheckedColor: Color = Color.Transparent,
    onValueChange: (Boolean) -> Unit,
) {

    val checkboxColor: Color by animateColorAsState(
        targetValue = if (isChecked) checkedColor else unCheckedColor,
        label = "checkboxColor"
    )

    val borderColor = if (isChecked) unCheckedColor else checkedColor

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(48.dp)
            .toggleable(value = isChecked, role = Role.Checkbox, onValueChange = onValueChange)
    ) {

        Box(
            modifier = Modifier
                .size(size)
                .background(color = checkboxColor, shape = shape)
                .border(width = 0.5.dp, color = borderColor, shape = shape)
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = scaleIn(),
                exit = fadeOut()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_rounded_check),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
        label?.let {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = label,
                color = colorResource(id = R.color.primary_color)
            )
        }
    }
}

@Preview
@Composable
fun ArabamCheckBoxPrev() {
    ArabamCheckBox(label = "Label", isChecked = true, onValueChange = {})
}