package com.arabam.android.assignment.feature.listing.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R.color
import com.arabam.android.assignment.core.common.R.drawable
import com.arabam.android.assignment.feature.listing.R

@Composable
fun SortListTopBar(
    clearTextVisible: Boolean,
    onClearTextClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val paddingOfParentRow = 16.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingOfParentRow),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(painter = painterResource(id = drawable.ic_sort), contentDescription = null)
            Text(
                text = stringResource(id = R.string.sort),
                fontWeight = FontWeight.Bold,
                color = colorResource(
                    id = color.primary_text_color
                ),
            )
        }

        AnimatedVisibility(
            visible = clearTextVisible,
            enter = slideInHorizontally(
                initialOffsetX = { it + paddingOfParentRow.value.toInt() * 2 },
                animationSpec = tween()
            ),
            exit = slideOutHorizontally(
                targetOffsetX = {
                    it + paddingOfParentRow.value.toInt() * 2
                },
                animationSpec = tween(easing = FastOutLinearInEasing)
            )
        ) {
            Text(
                text = stringResource(id = R.string.clear),
                color = colorResource(id = color.error),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onClearTextClick()
                }
            )
        }
    }
}

@Preview
@Composable
fun SortListTopBarPrev() {
    SortListTopBar(true, {})
}

