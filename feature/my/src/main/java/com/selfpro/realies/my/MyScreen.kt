package com.selfpro.realies.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.common.SpLog
import com.selfpro.realies.util.icon.IcRealieslogo
import com.selfpro.realies.util.icon.SpIcon

@Composable
fun MyScreen(navHostController: NavHostController) {
    GuestScreen()
}

//@Composable
//fun MyScreen(sharedViewModel: SharedViewModel) {
//    if (sharedViewModel.memberClass == MemberClass.Guest) {
//        GuestScreen(sharedViewModel)
//    } else {
//        JournalistScreen()
//    }
//}

@Composable
fun GuestScreen() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SpColor.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "ⓘ", fontSize = 20.sp, modifier = Modifier.padding(bottom = 3.dp))
            Text(
                text = "릴리즈에 로그인하시면\n다양한 서비스를\n이용할 수 있어요",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 30.dp)
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth()
                    .background(color = SpColor.Theme, shape = RoundedCornerShape(12.dp))
                    .padding(12.dp), text = "로그인", color = SpColor.White,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun JournalistScreen() {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = SpColor.White)
            .padding(15.dp)
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = SpColor.BoxGray,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(15.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .height(80.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        imageVector = SpIcon.IcRealieslogo,
                        contentDescription = null
                    )
                    Column(Modifier.padding(8.dp)) {
                        Text(text = "릴리즈", fontSize = 16.sp)
                        Text(text = "견습 기자", color = SpColor.StrokeGray)
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(
                                color = SpColor.BoxGray,
                                shape = RoundedCornerShape(12.dp)
                            ),
                    ) {
                        Column(
                            modifier = Modifier.padding(25.dp),
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = "0",
                                color = SpColor.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "게시중",
                                color = SpColor.StrokeGray,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(
                                color = SpColor.BoxGray,
                                shape = RoundedCornerShape(12.dp)
                            ),
                    ) {
                        Column(
                            modifier = Modifier.padding(25.dp),
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = "0",
                                color = SpColor.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "구독중",
                                color = SpColor.StrokeGray,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(
                                color = SpColor.BoxGray,
                                shape = RoundedCornerShape(12.dp)
                            ),
                    ) {
                        Column(
                            modifier = Modifier.padding(25.dp),
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = "0",
                                fontSize = 16.sp,
                                color = SpColor.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "구독자",
                                color = SpColor.StrokeGray,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}