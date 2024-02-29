package com.arabam.android.assignment.feature.listing.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.feature.listing.model.category.CategoryItem

@Composable
fun ModelListItem(
    model: CategoryItem,
    preSelectedItem: CategoryItem,
    isChecked: Boolean,
    onItemClick: (CategoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (model == preSelectedItem) {
                    onItemClick(CategoryItem())
                    return@clickable
                }
                onItemClick(model)
            }
            .padding(horizontal = 16.dp),
    ) {
        val (name, checkbox) = createRefs()

        Text(
            text = model.name!!,
            color = colorResource(id = R.color.primary_color),
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .constrainAs(name) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(checkbox.start)
                    width = Dimension.fillToConstraints
                }
                .padding(end = 12.dp),
            maxLines = 2
        )
        ArabamCheckBox(
            isChecked = isChecked,
            onValueChange = { },
            modifier = Modifier.constrainAs(checkbox) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ModelListItemPrev() {
    ModelListItem(
        model = CategoryItem(name = "Model"),
        preSelectedItem = CategoryItem(),
        onItemClick = {},
        isChecked = true
    )
}