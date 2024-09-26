package com.selfpro.realies.feature.main.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.selfpro.realies.R
import com.selfpro.realies.ui.color.SpColor
import com.selfpro.realies.util.AlertScreenData
import com.selfpro.realies.util.shimmerLoading

@Composable
fun GeneratedTitleText(titleFieldValue: String, isLoading: Boolean) {
    Text(
        text = titleFieldValue,
        style = TextStyle(
            fontSize = 14.sp,
            color = SpColor.Theme,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .padding(top = 8.dp, start = 15.dp, end = 15.dp)
            .background(
                brush = shimmerLoading(isLoading),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 0.5.dp,
                brush = Brush.horizontalGradient(
                    listOf(
                        SpColor.ThemeStart,
                        SpColor.ThemeEnd
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .fillMaxWidth()
            .padding(15.dp)
    )
}

@Composable
fun CloseBox(onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    Image(
        modifier = Modifier
            .height(25.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            },
        painter = painterResource(id = R.drawable.ic_close),
        contentDescription = "",
    )
}

@Composable
fun SendNewsCheckBox(isSend: Boolean, isTitleInit: Boolean, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .height(25.dp)
            .width(25.dp)
    ) {
        if (isSend) {
            CircularProgressIndicator(
                strokeWidth = 1.dp,
                color = SpColor.Theme,
                modifier = Modifier.padding(3.dp)
            )
        } else {
            val checkAlpha = if (isTitleInit) 1f else 0.3f

            Image(
                modifier = Modifier
                    .alpha(checkAlpha)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onClick()
                    },
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "",
            )
        }
    }
}

@Composable
fun GenerateTitleButton(
    modifier: Modifier,
    isVisible: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val textEndPadding = if (isVisible) 0.dp else 15.dp
    val loadingTextColor = if (isLoading) SpColor.StrokeGray else SpColor.Black
    val loadingText = if (isLoading) "생성 중" else "제목 생성하기"

    val interactionSource = remember { MutableInteractionSource() }

    Text(
        text = loadingText,
        style = TextStyle(
            fontSize = 14.sp,
            color = loadingTextColor,
            textAlign = TextAlign.Center
        ),
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
            .padding(start = 15.dp, end = textEndPadding)
            .background(
                color = SpColor.BoxGray,
                shape = RoundedCornerShape(12.dp)
            )
            .fillMaxWidth()
            .padding(9.dp)
    )
}

@Composable
fun TitleReport(onClick: () -> Unit) {
    Text(
        text = "제목 신고",
        style = TextStyle(
            fontSize = 14.sp,
            color = SpColor.Black,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .padding(start = 8.dp, end = 15.dp)
            .background(color = SpColor.BoxGray, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 25.dp, vertical = 9.dp)
            .clickable {
                onClick()
            }
    )
}

@Composable
fun AlertScreen(alertScreenData: AlertScreenData) {
    val interactionSource = remember { MutableInteractionSource() }
    Dialog(onDismissRequest = { alertScreenData.onDismiss }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(20.dp)
        ) {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    text = alertScreenData.title,
                    fontWeight = FontWeight.Bold,
                    color = SpColor.Black
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    text = alertScreenData.message,
                    color = SpColor.Black
                )

                Spacer(modifier = Modifier.height(24.dp))
                Row {
                    Text(
                        text = alertScreenData.confirmText,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { alertScreenData.onConfirm() }
                            .weight(1f)
                            .background(
                                color = SpColor.Theme,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 14.dp)
                    )

                    if (alertScreenData.dismissText.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = alertScreenData.dismissText,
                            textAlign = TextAlign.Center,
                            color = SpColor.StrokeGray,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) { alertScreenData.onDismiss() }
                                .weight(1f)
                                .background(
                                    shape = RoundedCornerShape(12.dp),
                                    color = SpColor.Unable,
                                )
                                .padding(vertical = 14.dp)
                        )
                    }
                }
            }
        }
    }
}