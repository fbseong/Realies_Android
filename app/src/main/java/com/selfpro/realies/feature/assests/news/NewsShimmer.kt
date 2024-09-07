package com.selfpro.realies.feature.assests.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.selfpro.realies.util.shimmerEffect.ShimmerNewsDateBox
import com.selfpro.realies.util.shimmerEffect.ShimmerNewsTitleBox
import com.selfpro.realies.util.shimmerEffect.ShimmerNewsWithdrawnLogo
import com.selfpro.realies.util.shimmerEffect.shimmerEffect

@Composable
fun NewsShimmerColumnItem(
) {
    Column(modifier = Modifier.padding(horizontal = 15.dp)) {
        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(12.dp)),
        ) {
            Box(
                modifier = Modifier
                    .background(shimmerEffect())
                    .height(240.dp)
                    .fillMaxWidth()
            )
        }
        ShimmerNewsWithdrawnLogo()
        ShimmerNewsTitleBox()
        ShimmerNewsDateBox()
    }
}