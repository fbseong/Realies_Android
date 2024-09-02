package com.selfpro.realies.feature.main.add

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.selfpro.realies.R
import com.selfpro.realies.ui.color.SpColor
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun AddScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        val scrollState = rememberScrollState()

        Column(modifier = Modifier.verticalScroll(scrollState)) {
            var textFieldValue by rememberSaveable { mutableStateOf("") }

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 500.dp)
                    .padding(15.dp)
                    .background(
                        color = SpColor.BoxGray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(15.dp),
                textStyle = TextStyle(
                    color = SpColor.Black,
                    fontSize = 14.sp
                ),
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                decorationBox = { innerTextField ->
                    if (textFieldValue.isEmpty()) {
                        Text(
                            text = "입력..",
                            color = SpColor.StrokeGray,
                            fontSize = 14.sp
                        )
                    }
                    innerTextField() // 실제 텍스트 필드
                }
            )
            Text(
                text = "제목",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = SpColor.StrokeGray,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .background(color = SpColor.BoxGray, shape = RoundedCornerShape(12.dp))
                    .border(
                        width = 0.5.dp,
                        color = SpColor.StrokeGray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()
                    .padding(15.dp)
            )
            Row(
                Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "제목 생성하기",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = SpColor.Black,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 15.dp)
                        .background(color = SpColor.BoxGray, shape = RoundedCornerShape(12.dp))
                        .border(
                            width = 0.5.dp,
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFFFFB054),
                                    Color(0xFFFF6EA2)
                                )
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .fillMaxWidth()
                        .padding(9.dp)
                )
                Text(
                    text = "신고",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = SpColor.Black,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .background(color = SpColor.BoxGray, shape = RoundedCornerShape(12.dp))
                        .border(
                            width = 0.5.dp,
                            color = SpColor.StrokeGray,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 25.dp, vertical = 9.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun AddScreenPreview() {
    AddScreen()
}