package com.arabam.android.assignment.feature.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.core.common.R.color
import com.arabam.android.assignment.core.common.R.string

@Composable
fun DetailsTabLayout(
    modifier: Modifier = Modifier
) {

    val tabs = listOf(
        stringResource(id = string.advert_info_tab_title),
        stringResource(id = string.advert_description_tab_title)
    )

    var selectedTabIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        TabIndicator(
            Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
        )
    }

    TabRow(
        backgroundColor = colorResource(id = color.primary_color),
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        indicator = indicator
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                }) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = tab,
                    style = MaterialTheme.typography.body2,
                    color = colorResource(id = color.on_primary)
                )
            }
        }
    }
}

@Composable
fun TabIndicator(
    modifier: Modifier = Modifier,
    color: Color = colorResource(id = R.color.on_primary)
) {
    Spacer(
        modifier
            .padding(horizontal = 24.dp)
            .height(2.dp)
            .background(color, RoundedCornerShape(topStartPercent = 100, topEndPercent = 100))
    )
}

@Preview
@Composable
fun DetailsTabLayoutPrev() {
    MaterialTheme {
        DetailsTabLayout()
    }
}