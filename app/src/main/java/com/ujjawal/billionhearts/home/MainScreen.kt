package com.ujjawal.billionhearts.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ujjawal.billionhearts.R
import com.ujjawal.billionhearts.home.components.ShoeListUI
import com.ujjawal.billionhearts.home.components.ShoePager
import com.ujjawal.billionhearts.home.components.ShoeTabs
import com.ujjawal.billionhearts.model.getShoes
import com.ujjawal.billionhearts.ui.theme.avalon

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    topPadding: () -> Dp,
    onClick: (index: Int) -> Unit,
    animatedContentScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope
) {
    Column (
        modifier = modifier.fillMaxSize()
    ){
        with(animatedContentScope) {
            with(sharedTransitionScope) {
                Row (
                    modifier = Modifier
                        .renderInSharedTransitionScopeOverlay(
                            zIndexInOverlay = 1f
                        ).animateEnterExit(
                            exit = fadeOut(),
                            enter = fadeIn()
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                        .padding(top = topPadding() + 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ){
                    Icon(
                        painter = painterResource(R.drawable.icon_back),
                        contentDescription = null
                    )
                    Icon(
                        painter = painterResource(R.drawable.icon_search),
                        contentDescription = null
                    )
                }
            }
        }

        Text(
            text = "Shoes",
            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
            style = TextStyle(
                color = Color(0xFF1F2732),
                fontSize = 26.sp,
                fontFamily = avalon,
                fontWeight = FontWeight.Bold
            ),
        )

        var currentTab by remember {
            mutableIntStateOf(0)
        }

        ShoeTabs(
            modifier = Modifier.padding(top = 16.dp, start = 8.dp),
            tabs = listOf("All", "Air Max", "Presto", "Huarache", "Air Force"),
            selectedTab = { currentTab },
            onTabSelected = {
                currentTab = it
            }
        )

        ShoePager(
            modifier = Modifier.padding(top = 8.dp),
            onClick = onClick,
            animatedContentScope = animatedContentScope,
            sharedTransitionScope = sharedTransitionScope
        )

        Spacer(modifier = Modifier.height(40.dp))

        ShoeListUI(
            shoes = getShoes()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
//    MainScreen(
//        topPadding = { 0.dp },
//        onClick = { },
//        animatedContentScope = AnimatedContentScope,
//        sharedTransitionScope = this@SharedTransitionLayout
//    )
}