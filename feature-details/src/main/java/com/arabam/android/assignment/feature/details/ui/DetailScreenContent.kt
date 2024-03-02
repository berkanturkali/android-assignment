package com.arabam.android.assignment.feature.details.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.core.model.Category
import com.arabam.android.assignment.core.model.DetailAdvert
import com.arabam.android.assignment.core.model.ListingAdvert
import com.arabam.android.assignment.core.model.Location
import com.arabam.android.assignment.core.model.UserInfo
import com.arabam.android.assignment.feature.details.components.AdvertImagePager
import com.arabam.android.assignment.feature.details.components.DetailsTabLayout
import com.arabam.android.assignment.feature.details.components.LastVisitedAdverts
import com.arabam.android.assignment.feature.details.components.Options
import com.arabam.android.assignment.feature.details.model.Option
import com.arabam.android.assignment.feature.details.model.OptionType
import com.arabam.android.assignment.feature.details.model.info.Info
import com.arabam.android.assignment.feature.details.model.info.getInfoList

@Composable
fun DetailScreenContent(
    description: String,
    images: List<String>,
    infoList: List<Info>,
    options: List<Option>,
    lastVisitedAdverts: List<ListingAdvert>,
    isFav: Boolean,
    onAdvertImageClick: (position: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier.fillMaxSize()) {

        item {
            AdvertImagePager(
                modifier = Modifier
                    .height(200.dp),
                images = images,
                pageCount = images.size,
                onImageClick = { _, position ->
                    onAdvertImageClick(position)
                })
        }
        item {
            DetailsTabLayout(
                infoList = infoList,
                descriptionText = description
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
            Options(options = options, isFav = isFav)
        }

        item {
            LastVisitedAdverts(adverts = lastVisitedAdverts)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenContentPrev() {
    MaterialTheme {
        DetailScreenContent(
            description = "Description",
            images = emptyList(),
            infoList = getInfoList(
                DetailAdvert(
                    category = Category(
                        id = 12799,
                        name = "otomobil/volkswagen-new-beetle-1-4-tsi-design"
                    ),
                    date = "2020-11-30T00:00:00",
                    dateFormatted = "30 Kasım 2020",
                    id = 7333920,
                    location = Location(
                        cityName = "Adana",
                        townName = "Ceyhan"
                    ),
                    modelName = "Beetle 1.4 TSI Design DSG",
                    photos = emptyList(),
                    price = 350000,
                    priceFormatted = "350000 TL",
                    properties = emptyList(),
                    text = "Lorem İpsum",
                    userInfo = UserInfo(
                        id = 2409290,
                        nameSurname = "Sonia Norris",
                        phone = "1312312321123",
                        phoneFormatted = "12313123121231"
                    ),
                    title = "VW BEETLE 14 TSİ RLİNE"
                ),
                context = LocalContext.current
            ),
            options = (
                    listOf(
                        Option(icon = R.drawable.ic_phone, type = OptionType.CALL),
                        Option(icon = R.drawable.ic_phone, type = OptionType.CALL),
                        Option(icon = R.drawable.ic_phone, type = OptionType.CALL),
                    )),
            lastVisitedAdverts = listOf(
                ListingAdvert(
                    category = Category(
                        id = 12799,
                        name = "otomobil/volkswagen-new-beetle-1-4-tsi-design"
                    ),
                    date = "2020-11-30T00:00:00",
                    dateFormatted = "30 Kasım 2020",
                    id = 7333920,
                    location = Location(
                        cityName = "Adana",
                        townName = "Ceyhan"
                    ),
                    modelName = "Beetle 1.4 TSI Design DSG",
                    price = 350000,
                    priceFormatted = "350000 TL",
                    properties = emptyList(),
                    photo = "",
                    title = "VW BEETLE 14 TSİ RLİNE"
                )
            ),
            onAdvertImageClick = {},
            isFav = true
        )
    }
}