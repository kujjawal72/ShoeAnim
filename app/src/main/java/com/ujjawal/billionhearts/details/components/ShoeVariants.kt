package com.ujjawal.billionhearts.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ujjawal.billionhearts.model.dummyVariants
import com.ujjawal.billionhearts.utils.noRippleClickable

@Composable
fun ShoeVariants(
    modifier: Modifier = Modifier,
    shoeVariants: List<Int>
) {
    var selected by remember {
        mutableIntStateOf(0)
    }
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        shoeVariants.forEachIndexed { index, it ->
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .width(80.dp)
                    .height(72.dp)
                    .noRippleClickable(
                        onClick = {
                            selected = index
                        }
                    )
                    .background(
                        color = Color(0xFFF4F4F4),
                        shape = RoundedCornerShape(18.dp)
                    )
                    .then(
                        if (selected == index) {
                            Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFF1F2732),
                                    shape = RoundedCornerShape(18.dp)
                                )
                                .border(
                                    width = 2.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(18.dp)
                                )
                        } else {
                            Modifier
                        }
                    )
                    .padding(all = 12.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ShoeVariantsPreview() {
    ShoeVariants(
        shoeVariants = dummyVariants()
    )
}