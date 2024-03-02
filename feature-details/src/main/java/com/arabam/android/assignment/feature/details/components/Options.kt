package com.arabam.android.assignment.feature.details.components

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.core.common.R.color
import com.arabam.android.assignment.feature.details.model.Option
import com.arabam.android.assignment.feature.details.model.OptionType

@Composable
fun Options(
    isFav: Boolean,
    options: List<Option>,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            options.forEach { option ->
                if (option.type == OptionType.FAVORITE) {
                    if (isFav) {
                        option.icon = R.drawable.ic_star
                        option.tint = color.fav_star_color
                    } else {
                        option.icon = R.drawable.ic_star_outlined
                    }
                }
                OptionButton(icon = option.icon, onOptionClick = option::action, tint = option.tint)
            }
        }
    }
}

@Composable
fun OptionButton(
    @DrawableRes icon: Int,
    @ColorRes tint: Int,
    onOptionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier.size(64.dp),
        onClick = onOptionClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = color.primary_color),
            contentColor = colorResource(id = color.on_primary)
        ),
        elevation = ButtonDefaults.elevation(8.dp),
        shape = CircleShape
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = colorResource(id = tint)
        )
    }
}

@Preview
@Composable
fun OptionsPrev() {
    Options(
        isFav = true,
        options = (
                listOf(
                    Option(icon = R.drawable.ic_phone, type = OptionType.CALL),
                    Option(icon = R.drawable.ic_phone, type = OptionType.CALL),
                    Option(icon = R.drawable.ic_phone, type = OptionType.CALL),
                ))
    )
}

@Preview
@Composable
fun OptionButtonPrev() {
    OptionButton(
        onOptionClick = { /*TODO*/ },
        icon = R.drawable.ic_phone,
        tint = color.fav_star_color
    )
}