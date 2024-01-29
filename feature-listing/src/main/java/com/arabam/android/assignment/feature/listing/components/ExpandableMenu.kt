package com.arabam.android.assignment.feature.listing.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R.*
import com.arabam.android.assignment.feature.listing.model.FilterMenuItem
import kotlinx.coroutines.delay

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableMenu(
    menuList: List<FilterMenuItem>,
    onItemClick: (FilterMenuItem) -> Unit,
    modifier: Modifier = Modifier,
) {

    val buttonSize = 56.dp

    val animatableList = mutableListOf<Animatable<Float, AnimationVector1D>>()

    repeat(menuList.size) {
        val animatable = remember {
            Animatable(initialValue = 0f)
        }
        animatableList.add(animatable)
    }

    val values = animatableList.map { it.value }

    var buttonState by rememberSaveable {
        mutableStateOf(ButtonState.COLLAPSED)
    }


    val transitionState = remember {
        MutableTransitionState(buttonState).apply {
            targetState = ButtonState.EXPANDED
        }
    }

    val transition = updateTransition(targetState = transitionState, null)

    val iconRotationDegree by transition.animateFloat(
        transitionSpec = {
            spring(dampingRatio = Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessLow)
        },
        label = "rotation"
    ) {
        if (buttonState == ButtonState.EXPANDED) 135f else 0f
    }

    val reversedValues = if (buttonState == ButtonState.COLLAPSED) values else values.reversed()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        menuList.forEachIndexed { index, _ ->
            LaunchedEffect(key1 = buttonState) {
                val delayDuration = if (buttonState == ButtonState.EXPANDED) 200L else 75L
                delay(delayDuration * index)
                animatableList[index].animateTo(
                    targetValue = if (buttonState == ButtonState.EXPANDED) 1f else 0f,
                    animationSpec = tween(easing = LinearOutSlowInEasing, durationMillis = 250)
                )
            }
            MenuItem(
                modifier = Modifier
                    .scale(reversedValues[index])
                    .alpha(reversedValues[index]),
                labelModifier = Modifier.alpha(reversedValues[index]),
                size = buttonSize / 1.2f,
                menuButtonSize = buttonSize,
                icon = menuList[index].icon,
                label = stringResource(menuList[index].label),
                onMenuItemClick = {
                    onItemClick(menuList[index])
                }
            )
        }
        MenuButton(
            buttonSize = buttonSize,
            iconRotationDegree = iconRotationDegree,
            onButtonClick = {
                buttonState =
                    if (buttonState == ButtonState.EXPANDED) ButtonState.COLLAPSED else ButtonState.EXPANDED
            })

    }
}


enum class ButtonState {
    COLLAPSED,
    EXPANDED
}

@Preview
@Composable
fun ExpandableMenuPrev() {
    ExpandableMenu(emptyList(), {})
}