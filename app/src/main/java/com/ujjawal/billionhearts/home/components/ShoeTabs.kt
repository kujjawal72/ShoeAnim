package com.ujjawal.billionhearts.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ujjawal.billionhearts.ui.theme.avenir
import com.ujjawal.billionhearts.ui.theme.avenir_roman
import com.ujjawal.billionhearts.utils.noRippleClickable

val selectedTabTextStyle = TextStyle(
    fontFamily = avenir,
    color = Color.White,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
)

val unselectedTabTextStyle = TextStyle(
    fontFamily = avenir_roman,
    color = Color(0xFF1F2732),
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
)


@Composable
fun ShoeTabs(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    selectedTab: () -> Int,
    onTabSelected: (Int) -> Unit,
) {

    val selectedTabIndex = selectedTab()

    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEachIndexed { index, tab ->
            val isSelected = index == selectedTabIndex
            Text(
                text = tab,
                style = if (isSelected) selectedTabTextStyle else unselectedTabTextStyle,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .noRippleClickable(
                        role = Role.Tab,
                        onClick = {
                            onTabSelected(index)
                        }
                    ).background(
                        color = if (isSelected) {
                            Color(0xFF1F2732)
                        } else {
                            Color(0xFFF4F4F4)
                        },
                        shape = RoundedCornerShape(18.dp),
                    ).border(
                        width = 1.dp,
                        color = if (isSelected) {
                            Color(0xFF1F2732)
                        } else {
                            Color(0xFFD8D8D8)
                        },
                        shape = RoundedCornerShape(18.dp)
                    ).padding(
                        horizontal = 22.dp
                    ).padding(
                        top = 12.dp,
                        bottom = 10.dp
                    )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShoeTabsPreview() {
    ShoeTabs(tabs = listOf("All", "Tab 2", "Tab 3"), selectedTab = { 0 }, onTabSelected = {})
}