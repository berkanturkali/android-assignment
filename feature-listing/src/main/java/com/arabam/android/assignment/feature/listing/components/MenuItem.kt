package com.arabam.android.assignment.feature.listing.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R

@Composable
fun MenuItem(
    size: Dp,
    menuButtonSize:Dp,
    label: String,
    @DrawableRes icon: Int,
    onMenuItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelModifier: Modifier = Modifier,
    parentModifier: Modifier = Modifier,
) {

    Row(
        modifier = parentModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MenuItemLabel(label = label, modifier = labelModifier)
        Button(
            onClick = {
                onMenuItemClick(label)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(
                    R.color.primary_color,
                ),
                contentColor = colorResource(R.color.on_primary)
            ),
            modifier = modifier
                .padding(horizontal = menuButtonSize / 2f - size / 2f)
                .clip(CircleShape)
                .size(size),

            contentPadding = PaddingValues(0.dp),
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(size / 1.8f)
            )
        }
    }
}

@Preview
@Composable
fun MenuItemPrev() {
    MenuItem(
        size = 24.dp,
        icon = R.drawable.ic_add,
        label = "Label",
        onMenuItemClick = { /*TODO*/ },
        menuButtonSize = 36.dp
        )
}