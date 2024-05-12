package com.arabam.android.assignment.feature.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.core.common.utils.formatPrice
import com.arabam.android.assignment.core.model.ListingAdvert

@Composable
fun LastVisitedAdverts(
    adverts: List<ListingAdvert>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        LastVisitedAdvertsTitle(modifier = Modifier.padding(12.dp))

        LastVisitedAdvertsList(advertList = adverts)
    }

}

@Composable
fun LastVisitedAdvertsTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.last_visited_adverts),
        style = MaterialTheme.typography.h6,
        color = colorResource(id = R.color.primary_text_color)
    )
}

@Composable
fun LastVisitedAdvertsList(
    advertList: List<ListingAdvert>,
    modifier: Modifier = Modifier
) {

    LazyRow(modifier = modifier) {
        items(advertList) { advert ->
            LastVisitedAdvertItem(
                image = advert.photo,
                price = String.format(
                    stringResource(id = R.string.advert_price),
                    advert.price.formatPrice()
                ),
                modelName = advert.modelName,
                address = String.format(
                    stringResource(id = R.string.advert_location),
                    advert.location.cityName,
                    advert.location.townName
                ),
            )
        }
    }
}

@Composable
fun LastVisitedAdvertItem(
    image: String,
    price: String,
    modelName: String,
    address: String,
    modifier: Modifier = Modifier
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(image)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    )

    Card(
        modifier = modifier
            .width(320.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .background(
                            colorResource(id = R.color.primary_color),
                            RoundedCornerShape(2.dp)
                        )
                        .align(Alignment.BottomStart)
                ) {
                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = price,
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.SemiBold),
                        color = colorResource(id = R.color.on_primary)
                    )
                }
            }


            Text(
                text = modelName,
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.SemiBold),
                color = colorResource(id = R.color.primary_text_color),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp)
            )

            Text(
                text = address,
                style = MaterialTheme.typography.caption,
                color = colorResource(id = R.color.primary_text_color),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun LastVisitedAdvertItemPrev() {
    MaterialTheme {
        LastVisitedAdvertItem(
            image = "",
            price = "450.000 TL",
            modelName = "Model",
            address = "Karşıyaka/İzmir",
        )
    }
}