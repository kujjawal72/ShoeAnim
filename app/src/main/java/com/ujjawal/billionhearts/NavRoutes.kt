package com.ujjawal.billionhearts

import kotlinx.serialization.Serializable


sealed class NavRoutes {
    @Serializable
    object MainScreen

    @Serializable
    data class DetailsScreen(
        val index: Int
    )
}