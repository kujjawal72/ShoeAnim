package com.ujjawal.billionhearts.model

data class ShoeSize(
    val size: String,
    val isAvailable: Boolean
)

fun getShoeSizes() = listOf(
    ShoeSize("UK 6", true),
    ShoeSize("UK 7", true),
    ShoeSize("UK 8", true),
    ShoeSize("UK 9", true),
    ShoeSize("UK 10", true),
    ShoeSize("UK 11", false),
    ShoeSize("UK 12", true),
    ShoeSize("UK 13", false)
)