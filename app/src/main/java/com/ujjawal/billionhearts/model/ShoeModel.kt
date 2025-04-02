package com.ujjawal.billionhearts.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.ujjawal.billionhearts.R

data class ShoeModel(
    val name: String,
    val price: String,
    val textColor: String,
    val priceTextColor: String,
    val dividerColor: String,
    val cardColor: String,
    val currency: String = "₹",
    val shoeSizes: List<ShoeSize>,
    val variants: List<Int>,
    val description: String,
    @DrawableRes val image: Int
) {
    fun getFormattedPrice() = "$currency $price"
}

fun String.toColor(): Color {
    return Color(this.toColorInt())
}

fun getShoes() = listOf<ShoeModel>(
    ShoeModel(
        name = "Alpha Savage",
        price = "8,895",
        textColor = "#FFFFFF",
        priceTextColor = "#B3FFFFFF",
        dividerColor = "#4DFFFFFF",
        cardColor = "#E24C4D",
        shoeSizes = getShoeSizes(),
        variants = dummyVariants(),
        description = getDescription(),
        image = R.drawable.red_without_shadow
    ),
    ShoeModel(
        name = "Air Max",
        price = "11,897",
        textColor = "#1F2732",
        priceTextColor = "#B31F2732",
        dividerColor = "#4D1F2732",
        cardColor = "#FDBA62",
        shoeSizes = getShoeSizes(),
        variants = dummyVariants(),
        description = getDescription(),
        image = R.drawable.yellow_without_shadow
    ),
    ShoeModel(
        name = "KD13 EP",
        price = "12,995",
        textColor = "#FFFFFF",
        priceTextColor = "#B3FFFFFF",
        dividerColor = "#4DFFFFFF",
        cardColor = "#4B81F4",
        shoeSizes = getShoeSizes(),
        variants = dummyVariants(),
        description = getDescription(),
        image = R.drawable.blu_without_shadow
    )
)

fun dummyVariants() = listOf(
    R.drawable.red_without_shadow,
    R.drawable.yellow_without_shadow,
    R.drawable.blu_without_shadow
)

fun getDescription() = "In the game's crucial moments, KD thrives. He takes over on both ends of the court, making defenders fear his unstopp He takes over on both ends of the court, making defenders fear his unstopp He takes over on both ends of the court, making defenders fear his unstopp"