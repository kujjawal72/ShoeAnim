package com.ujjawal.billionhearts.details

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ujjawal.billionhearts.R
import com.ujjawal.billionhearts.details.components.ShoeSizeOptions
import com.ujjawal.billionhearts.details.components.ShoeVariants
import com.ujjawal.billionhearts.model.ShoeModel
import com.ujjawal.billionhearts.model.toColor
import com.ujjawal.billionhearts.ui.theme.avalon
import com.ujjawal.billionhearts.ui.theme.avenir
import com.ujjawal.billionhearts.ui.theme.avenir_book
import com.ujjawal.billionhearts.utils.noRippleClickable

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    topPadding: () -> Dp,
    shoe: ShoeModel,
    onBackPress: () -> Unit,
    animatedContentScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {

        with(sharedTransitionScope) {
            CurveBackground(
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "bg" + shoe.name),
                        animatedVisibilityScope = animatedContentScope,
                        resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                    ),
                color = shoe.cardColor.toColor()
            )
        }

        with(animatedContentScope) {
            with(sharedTransitionScope) {
                Row(
                    modifier = Modifier
                        .renderInSharedTransitionScopeOverlay(
                            zIndexInOverlay = 1f
                        )
                        .animateEnterExit(
                            exit = fadeOut(),
                            enter = fadeIn()
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                        .padding(top = topPadding() + 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_back),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.noRippleClickable(onClick = onBackPress)
                    )
                    Icon(
                        painter = painterResource(R.drawable.icon_fav),
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            val detailsBoundTransform = BoundsTransform { initialBounds, targetBounds ->
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
                            // boundsTransform = detailsBoundTransform
                        )
                        .padding(start = 24.dp, top = 80.dp)
                )
            }

            with(sharedTransitionScope) {

                Column(
                    modifier = Modifier.fillMaxWidth().skipToLookaheadSize()
                ) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = shoe.name,
                            style = TextStyle(
                                color = Color(0xFF1F2732),
                                fontSize = 26.sp,
                                fontFamily = avenir,
                                fontWeight = FontWeight.Normal
                            )
                        )

                        Text(
                            text = shoe.getFormattedPrice(),
                            style = TextStyle(
                                color = Color(0xFF1F2732),
                                fontSize = 18.sp,
                                fontFamily = avalon,
                                fontWeight = FontWeight.Bold
                            )
                        )

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomEllipsizedText(
                        text = shoe.description,
                        textStyle = TextStyle(
                            color = Color(0xFF7B8A9E),
                            fontSize = 16.sp,
                            fontFamily = avenir_book,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    ShoeVariants(
                        shoeVariants = shoe.variants,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    ShoeSizeOptions(
                        shoeSizes = shoe.shoeSizes,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                }

            }
        }

        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1F2732),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Add to Bag",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = avenir,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun CustomEllipsizedText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = TextStyle(fontSize = 16.sp),
    maxLines: Int = 3
) {
    var isExpanded by remember { mutableStateOf(false) }


    val textMeasurer = rememberTextMeasurer()

    var availableWidthPx by remember { mutableIntStateOf(0) }
    var finalDisplayText by remember { mutableStateOf<AnnotatedString>(AnnotatedString(text)) }

    LaunchedEffect(text, availableWidthPx, isExpanded) {
        if (isExpanded || availableWidthPx == 0) {
            finalDisplayText = AnnotatedString(text)
            return@LaunchedEffect
        }

        val fullTextLayout = textMeasurer.measure(
            text = AnnotatedString(text),
            style = textStyle,
            constraints = Constraints(maxWidth = availableWidthPx),
            maxLines = maxLines
        )

        if (!fullTextLayout.hasVisualOverflow) {
            finalDisplayText = AnnotatedString(text)
            return@LaunchedEffect
        }

        var low = 0
        var high = text.length
        var visibleText = text

        while (low <= high) {
            val mid = (low + high) / 2
            val candidate = text.take(mid).trimEnd()

            val candidateAnnotated = buildAnnotatedString {
                append(candidate)
                append("... ")
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFF1F2732))) {
                    append("MORE")
                }
            }

            val layout = textMeasurer.measure(
                text = candidateAnnotated,
                style = textStyle,
                constraints = Constraints(maxWidth = availableWidthPx),
                maxLines = maxLines
            )

            if (layout.hasVisualOverflow) {
                high = mid - 1
            } else {
                visibleText = candidate
                low = mid + 1
            }
        }

        finalDisplayText = buildAnnotatedString {
            append(visibleText.trimEnd())
            append("... ")
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFF1F2732))) {
                append("MORE")
            }
        }
    }

    Box(
        modifier = modifier
            .onSizeChanged { availableWidthPx = it.width }
            .noRippleClickable { isExpanded = true }
    ) {
        BasicText(
            text = finalDisplayText,
            style = textStyle
        )
    }
}


@Composable
private fun CurveBackground(
    modifier: Modifier,
    color: Color
) {

    val pxValue = with(LocalDensity.current) { 200.dp.toPx() }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .graphicsLayer {
                translationX = 180f
                translationY = -pxValue
                scaleX = 1.6f
            }
            .background(shape = CircleShape, color = color)
    )
}

@Preview
@Composable
private fun CurveBackgroundPreview() {
    Box(
        Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        CurveBackground(
            color = Color.Blue,
            modifier = Modifier
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsScreenPreview() {
//    DetailsScreen(
//        topPadding = { 0.dp },
//        shoeModel = getShoes()[0],
//        animatedContentScope = this,
//        sharedTransitionScope = this@SharedTransitionLayout
//    )
}