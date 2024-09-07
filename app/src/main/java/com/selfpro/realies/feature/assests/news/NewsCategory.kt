package com.selfpro.realies.feature.assests.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.selfpro.realies.ui.color.SpColor

@Composable
fun NewsCategory(){
    val items = listOf("릴리즈", "엔터테인먼트", "테크", "연애","스포츠","경제")
    var selectedItem by remember { mutableStateOf<String?>(null) }
    var selectedRealies by remember { mutableStateOf(false) }

    LazyRow(
        modifier = Modifier
            .background(color = SpColor.White)
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items.size) {
            val item = items[it]
            val isSelected = item == selectedItem

            if (it == 0) {
                CategoryRealiesItem(categoryInfo = items[0], isSelected = selectedRealies) {
                    selectedRealies = !selectedRealies
                }

            } else {
                CategoryItem(categoryInfo = items[it], isSelected = isSelected) {
                    selectedItem = if (selectedItem == item) {
                        null
                    } else {
                        item
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(categoryInfo: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) SpColor.StrokeGray else SpColor.BoxGray
    val textColor = if (isSelected) SpColor.BoxGray else SpColor.StrokeGray

    val interactionSource = remember { MutableInteractionSource() }

    Text(
        text = categoryInfo,
        color = textColor,
        fontSize = 12.sp,
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = SpColor.StrokeGray,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            )
            .background(color = backgroundColor, shape = RoundedCornerShape(100.dp))
            .padding(horizontal = 20.dp, vertical = 8.dp)
    )
}

@Composable
fun CategoryRealiesItem(categoryInfo: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundBrush = if (isSelected) Brush.horizontalGradient(
        listOf(
            SpColor.ThemeStart,
            SpColor.ThemeEnd
        )
    ) else Brush.horizontalGradient(
        listOf(
            SpColor.BoxGray,
            SpColor.BoxGray
        )
    )

    val textColor = if (isSelected) SpColor.White else SpColor.StrokeGray

    val interactionSource = remember { MutableInteractionSource() }

    Text(
        text = categoryInfo,
        color = textColor,
        fontSize = 12.sp,
        modifier = Modifier
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            )
            .border(
                brush = Brush.horizontalGradient(
                    listOf(
                        SpColor.ThemeStart,
                        SpColor.ThemeEnd
                    )
                ),
                width = 0.5.dp,
                shape = RoundedCornerShape(100.dp)
            )
            .background(brush = backgroundBrush, shape = RoundedCornerShape(100.dp))
            .padding(horizontal = 20.dp, vertical = 8.dp)
    )
}

