package com.ujjawal.billionhearts.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ujjawal.billionhearts.model.ShoeModel
import com.ujjawal.billionhearts.model.getShoes
import com.ujjawal.billionhearts.ui.theme.avalon
import com.ujjawal.billionhearts.ui.theme.avenir
import com.ujjawal.billionhearts.ui.theme.gotham

@Composable
fun ShoeListUI(
    modifier: Modifier = Modifier,
    shoes: List<ShoeModel>
) {

    Column (
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 16.dp)
    ){

        Text(
            text = "243 OPTIONS", // replace with shoes.size
            style = TextStyle(
                color = Color(0xB31F2732),
                fontSize = 14.sp,
                fontFamily = avenir,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        shoes.forEach {
            HorizontalDivider(
                color = Color(0xFFF4F4F4)
            )

            ShoeListItem(
                shoe = it
            )

        }


    }

}

@Composable
private fun ShoeListItem(
    modifier: Modifier = Modifier,
    shoe: ShoeModel
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = shoe.image),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .height(72.dp)
        )

        Column (
            modifier = Modifier.weight(1f).padding(start = 18.dp),
        ){

            Text(
                text = shoe.name,
                style = TextStyle(
                    color = Color(0xFF1F2732),
                    fontSize = 16.sp,
                    fontFamily = gotham,
                    lineHeight = 18.sp,
                    letterSpacing = (-0.5).sp,
                    fontWeight = FontWeight.Medium
                )
            )

            Text(
                text = shoe.getFormattedPrice(),
                style = TextStyle(
                    color = Color(0x801F2732),
                    fontSize = 14.sp,
                    fontFamily = avalon,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.padding(top = 8.dp)
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ShoeListUIPreview() {
    ShoeListUI(shoes = getShoes())
}