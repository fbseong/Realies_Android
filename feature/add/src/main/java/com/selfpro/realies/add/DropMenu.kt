package com.selfpro.realies.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.selfpro.realies.data.model.NewsAddMenuModel
import com.selfpro.realies.util.common.SpColor

@Composable
fun DropMenuLazyRow(items: List<NewsAddMenuModel>) {
    Box {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .shadow(
                    5.dp,
                    shape = RoundedCornerShape(12.dp),
                    ambientColor = SpColor.BoxGray
                )
                .background(color = SpColor.White, shape = RoundedCornerShape(12.dp))
                .height(200.dp),
            contentPadding = PaddingValues(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item {
                Text(
                    text = "명령어 목록",
                    color = SpColor.StrokeGray,
                    fontSize = 12.sp,
                )
            }
            items(items.size) {
                DropMenuItem(data = items[it])
            }
        }

    }
}

@Composable
fun DropMenuItem(data: NewsAddMenuModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { data.onClick() }
    ) {
        Row(
            modifier = Modifier
                .height(55.dp)
                .border(0.5.dp, color = SpColor.Void),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                imageVector = data.icon,
                contentDescription = "",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(6.dp))
                    .width(55.dp)
                    .height(55.dp)
            )
            Column(
                modifier = Modifier.padding(start = 6.dp, top = 3.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = data.title,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = data.description,
                    color = SpColor.StrokeGray,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}