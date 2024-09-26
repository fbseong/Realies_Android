package com.selfpro.realies.feature.main.bottom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.selfpro.realies.R
import com.selfpro.realies.data.model.NavData
import com.selfpro.realies.feature.assests.news.CategoryItem
import com.selfpro.realies.feature.assests.news.CategoryRealiesItem
import com.selfpro.realies.ui.color.SpColor
import com.selfpro.realies.util.SpLog

@Composable
fun BottomNavigationBar(modifier: Modifier, navController: NavController) {
    SpLog.d("BottomNavigationBar")
    Column(modifier = modifier) {
        HorizontalDivider(thickness = 0.5.dp, color = SpColor.StrokeGray)

        val navDataList = listOf(
            NavData(
                text = "릴리즈",
                icon = painterResource(R.drawable.ic_navigation_realies),
                iconSelected = painterResource(R.drawable.ic_navigation_realies_selected),
                onClick = { navController.navigate("realies") }
            ),
            NavData(
                text = "구독",
                icon = painterResource(R.drawable.ic_navigation_subscribe),
                iconSelected = painterResource(R.drawable.ic_navigation_subscribe_selected),
                onClick = {navController.navigate("subscribes")}
            ),
            NavData(
                icon = painterResource(R.drawable.ic_navigation_add),
//                onClick = {navController.navigate("add")}
            ),
            NavData(
                text = "도전 뉴스",
                icon = painterResource(R.drawable.ic_navigation_challenge),
                iconSelected = painterResource(R.drawable.ic_navigation_challenge_selected),
                onClick = {navController.navigate("challenges")}
            ),
            NavData(
                text = "나",
                icon = painterResource(R.drawable.ic_navigation_my),
                iconSelected = painterResource(R.drawable.ic_navigation_my_selected),
                onClick = {navController.navigate("my")}
            ),
        )

        val selectedNavData = remember { mutableStateOf(navDataList[0]) }

        Row(modifier.fillMaxWidth()) {
            navDataList.forEach { navData ->
                BottomNavigationItem(
                    modifier = Modifier.weight(1f),
                    navData = navData,
                    selectedNavData = selectedNavData
                )
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    modifier: Modifier, navData: NavData, selectedNavData: MutableState<NavData>
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(interactionSource = interactionSource, indication = null) {
                if (navData.iconSelected != null) {
                    selectedNavData.value = navData
                    navData.onClick()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val iconSize = if (navData.text.isNullOrEmpty()) 28.dp else 18.dp
        val painter =
            if (selectedNavData.value == navData && navData.iconSelected != null) navData.iconSelected
            else navData.icon

        Image(
            modifier = Modifier
                .padding(
                    top = 3.dp,
                    bottom = 4.dp
                )
                .size(iconSize), painter = painter, contentDescription = null
        )
        if (!navData.text.isNullOrEmpty()) {
            Text(
                modifier = Modifier,
                text = navData.text,
                textAlign = TextAlign.Center,
                fontSize = 10.sp
            )
        }
    }
}