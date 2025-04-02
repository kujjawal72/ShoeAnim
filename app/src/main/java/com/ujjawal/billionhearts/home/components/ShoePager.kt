package com.ujjawal.billionhearts.home.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.ujjawal.billionhearts.model.ShoeModel
import com.ujjawal.billionhearts.model.getShoes
import com.ujjawal.billionhearts.model.toColor
import com.ujjawal.billionhearts.ui.theme.avalon
import com.ujjawal.billionhearts.ui.theme.gotham

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ShoePager(
    modifier: Modifier = Modifier,
    onClick: (index: Int) -> Unit,
    animatedContentScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope
) {
    ShoePagerUI(
        modifier = modifier.padding(top = 16.dp),
        items = getShoes(),
        onClick = onClick,
        animatedContentScope = animatedContentScope,
        sharedTransitionScope = sharedTransitionScope
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ShoePagerUI(
    modifier: Modifier = Modifier,
    items: List<ShoeModel>,
    onClick: (index: Int) -> Unit,
    animatedContentScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope
) {
    val pagerState = rememberPagerState(initialPage = 0) { items.size }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    HorizontalPager(
        modifier = modifier,
        state = pagerState, pageContent = {
            ShoeDetails(shoe = items[it], offsetDistanceInPages = {
                pagerState.getOffsetDistanceInPages(it)
            }, onClick = { onClick.invoke(it) }, animatedContentScope = animatedContentScope, sharedTransitionScope = sharedTransitionScope)
        }, pageSize = PageSize.Fixed((screenWidth * 0.85).dp),
        contentPadding = PaddingValues(start = 18.dp, end = 48.dp)
    )
}

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationSpecApi::class)
@Composable
fun ShoeDetails(
    modifier: Modifier = Modifier,
    shoe: ShoeModel,
    offsetDistanceInPages: () -> Float,
    onClick: () -> Unit,
    animatedContentScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        color = Color.White
    ) {
        Box (
            modifier = Modifier.fillMaxWidth()
        ){
            with(sharedTransitionScope) {
                Column(
                    modifier = Modifier
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "bg" + shoe.name),
                            animatedVisibilityScope = animatedContentScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds,
                        )
                        .fillMaxWidth(fraction = 0.85f)
                        .height(325.dp)
                        .background(
                            color = shoe.cardColor.toColor(),
                            shape = RoundedCornerShape(
                                size = 16.dp
                            )
                        )
                        .padding(all = 16.dp)
                ) {
                    Text(
                        text = shoe.name,
                        style = TextStyle(
                            fontFamily = gotham,
                            color = shoe.textColor.toColor(),
                            fontWeight = FontWeight.Normal,
                            fontSize = 24.sp
                        )
                    )
                    Text(
                        text = shoe.getFormattedPrice(),
                        style = TextStyle(
                            fontFamily = avalon,
                            color = shoe.priceTextColor.toColor(),
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    VerticalDivider(
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp, bottom = 16.dp),
                        color = shoe.dividerColor.toColor(),
                    )
                }
            }

            val rotationValue by remember {
                derivedStateOf {
                    val pageOffset = offsetDistanceInPages()
                    when {
                        pageOffset < 0 -> lerp(10f, -30f, (1f + pageOffset).coerceIn(0f, 1f)) // for pageOffset 0 it is -30f & for -1 it is 10f
                        else -> lerp(-60f, -30f, (1f - pageOffset).coerceIn(0f, 1f)) // for pageOffset 0 it is -30f & for 1 it is -60f
                    }
                }
            }

            val pagerBoundsTransform = BoundsTransform { initialBounds, targetBounds ->
                keyframes {
                    durationMillis = 300 // Adjust duration as needed
                    initialBounds at 0 using LinearEasing
                    targetBounds at 300
                }
            }
            with(sharedTransitionScope) {
                Image(
                    painter = painterResource(id = shoe.image),
                    contentDescription = null,
                    modifier = Modifier
                        .sharedElement(
                            state = sharedTransitionScope.rememberSharedContentState(key = shoe.name),
                            animatedVisibilityScope = animatedContentScope,
                            //boundsTransform = pagerBoundsTransform
                        )
                        .padding(end = 24.dp)
                        .align(Alignment.CenterEnd)
                        .graphicsLayer {
                            rotationZ = rotationValue
                        }
                )
            }
        }

    }
}

@Preview
@Composable
private fun ShoePagerPreview() {
//    ShoePager(
//        modifier = Modifier
//            .background(Color.White),
//        onClick = { },
//        animatedContentScope = animatedContentScope,
//        sharedTransitionScope = sharedTransitionScope
//    )
}