package com.selfpro.realies

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.selfpro.realies.data.model.NavData
import com.selfpro.realies.util.common.SpColor

@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: MainViewModel = viewModel()
) {

    val bottomNavigationVisible by viewModel.bottomNavigationVisible.collectAsState()

    AnimatedVisibility(
        visible = bottomNavigationVisible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 300),
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 300),
            targetOffsetY = { it }
        )
    ) {

        Column(modifier = modifier) {
            HorizontalDivider(thickness = 0.5.dp, color = SpColor.StrokeGray)


            val selectedNavData = remember { mutableStateOf(viewModel.navDataList[0]) }

            Row(modifier.fillMaxWidth()) {
                viewModel.navDataList.forEach { navData ->
                    BottomNavigationItem(
                        modifier = Modifier.weight(1f),
                        navData = navData,
                        selectedNavData = selectedNavData,
//                        navHostController = navController as NavHostController
                        navHostController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    modifier: Modifier,
    navData: NavData,
    selectedNavData: MutableState<NavData>,
    navHostController: NavHostController
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(interactionSource = interactionSource, indication = null) {
                if (navData.iconSelected != null) {
                    selectedNavData.value = navData
                }
                navData.onClick()
                navHostController.navigate(navData.id)
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
                .size(iconSize),
            painter = painterResource(id = painter!!),
            contentDescription = null
        )
        if (!navData.text.isNullOrEmpty()) {
            Text(
                modifier = Modifier,
                text = navData.text!!,
                textAlign = TextAlign.Center,
                fontSize = 10.sp
            )
        }

    }
}