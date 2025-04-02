package com.ujjawal.billionhearts.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ujjawal.billionhearts.model.ShoeSize
import com.ujjawal.billionhearts.model.getShoeSizes
import com.ujjawal.billionhearts.ui.theme.avenir
import com.ujjawal.billionhearts.ui.theme.avenir_roman
import com.ujjawal.billionhearts.utils.noRippleClickable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShoeSizeOptions(
    modifier: Modifier = Modifier,
    shoeSizes: List<ShoeSize>
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Text(
            text = "Select Size",
            style = TextStyle(
                color = Color(0xFF1F2732),
                fontSize = 18.sp,
                fontFamily = avenir,
                fontWeight = FontWeight.Normal
            )
        )

        var selectedIndex by remember {
            mutableIntStateOf(3)
        }

        FlowRow (
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(top = 12.dp, start = 4.dp)
                .wrapContentHeight(align = Alignment.Top),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            maxItemsInEachRow = 4,
        ){
            shoeSizes.forEachIndexed { index, it ->
                ShoeSizeItem(
                    shoeSize = it,
                    isSelected = { selectedIndex == index},
                    onSelected = {
                        selectedIndex = index
                    }
                )
            }

        }

    }
}

private val selectedSizeTextStyle = TextStyle(
    fontFamily = avenir,
    color = Color(0xFF1F2732),
    fontWeight = FontWeight.Normal,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    fontSize = 14.sp
)

private val unselectedSizeTextStyle = TextStyle(
    fontFamily = avenir_roman,
    color = Color(0xFF1F2732),
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
)

@Composable
private fun ShoeSizeItem(
    shoeSize: ShoeSize,
    isSelected: () -> Boolean,
    onSelected: () -> Unit
) {
    Text(
        text = shoeSize.size,
        style = if (isSelected()) selectedSizeTextStyle else unselectedSizeTextStyle,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .widthIn(min = 80.dp)
            .noRippleClickable(
                role = Role.Tab,
                onClick = {
                    if (shoeSize.isAvailable) onSelected()
                },
                enabled = shoeSize.isAvailable
            )
            .background(
                color = if (shoeSize.isAvailable) {
                    Color.White
                } else {
                    Color(0xFFF6F6F6)
                },
                shape = RoundedCornerShape(12.dp),
            )
            .border(
                width = 1.dp,
                color = if (shoeSize.isAvailable) {
                    if (isSelected()) {
                        Color(0xFF1F2732)
                    } else {
                        Color(0xFFD8D8D8)
                    }
                } else {
                    Color(0xFFD8D8D8)
                },
                shape = RoundedCornerShape(12.dp)
            )
            .padding(
                top = 12.dp,
                bottom = 10.dp
            )
    )
}

@Preview
@Composable
private fun ShoeSizeItemPreview() {
    ShoeSizeItem(
        shoeSize = ShoeSize("UK 6", true),
        isSelected = { false },
        onSelected = {}
    )
}

@Preview
@Composable
private fun ShoeSizeOptionsPreview() {
    ShoeSizeOptions(
        shoeSizes = getShoeSizes()
    )
}