package com.selfpro.realies.feature.main.add

import CursorDropdownMenuSample
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.selfpro.realies.R
import com.selfpro.realies.ui.color.SpColor
import com.selfpro.realies.util.AlertScreenData
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.shimmerLoading

@Composable
fun AddScreen(popBackStack: () -> Unit) {
    Box(
        modifier = Modifier
            .background(SpColor.White)
            .fillMaxSize()
    ) {
        //스크롤 관리
        val scrollState = rememberScrollState()
        //뉴스 본문값
        var contentFieldValue = remember { mutableStateOf(TextFieldValue("")) }
        //제목 초기화 여부
        var isTitleInit by remember { mutableStateOf(false) }
        //전송 여부
        var isSend by remember { mutableStateOf(false) }
        //다이얼로그 표시
        var showDialog by remember { mutableStateOf(false) }
        //팝업 다이얼로그 데이터
        var alertData by remember { mutableStateOf(AlertScreenData("", "")) }
        //다이얼로그 띄우기
        if (showDialog) {
            AlertScreen(alertData)
        }

        var expanded = remember { mutableStateOf(false) }


        Column(modifier = Modifier.verticalScroll(scrollState)) {
            var titleFieldValue by rememberSaveable { mutableStateOf("") }
            var isVisible by remember { mutableStateOf(false) }
            var isLoading by remember { mutableStateOf(false) }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                CloseBox {
                    alertData = AlertScreenData(
                        title = "작성중인 뉴스가 있습니다.",
                        message = "저장되지 않고 나갈 경우 지금까지 작성한 내용이\n사라집니다.",
                        onConfirm = { popBackStack() },
                        onDismiss = { showDialog = false },
                        confirmText = "삭제하기",
                        dismissText = "취소"
                    )
                    showDialog = true
                }

                Spacer(modifier = Modifier.weight(1f))

                SendNewsCheckBox(isSend, isTitleInit) { isSend = true }
            }
            AnimatedVisibility(visible = isVisible) {
                GeneratedTitleText(
                    titleFieldValue = titleFieldValue,
                    isLoading = isLoading
                )
            }
            Row(Modifier.padding(top = 8.dp)) {
                GenerateTitleButton(
                    modifier = Modifier.weight(1f),
                    isLoading = isLoading,
                    isVisible = isVisible
                ) {
                    if (contentFieldValue.value.text.isEmpty()) {
                        alertData = AlertScreenData(
                            title = "뉴스 제목을 생성할 수 없습니다.",
                            message = "30자 이하는 뉴스 제목을 생성할 수 없습니다.",
                            confirmText = "확인",
                            onConfirm = { showDialog = false }
                        )
                        showDialog = true
                    } else {
                        isVisible = !isVisible
                        isLoading = true
                        isTitleInit = true
                    }
                }
                AnimatedVisibility(visible = isVisible) {
                    TitleReport {}
                }
            }
            var lastWordStartsWithSlash by remember { mutableStateOf(false) }
            var lastWord = remember { mutableStateOf("")}

            NewsContentTextField(
                lastWord = lastWord,
                contentFieldValue = contentFieldValue,
                onStartValueChange = {
                    contentFieldValue.value = it

                    val words = it.text.lines().flatMap { it.split(" ") }
                    lastWord.value = words.lastOrNull() ?: ""
                    lastWordStartsWithSlash = lastWord.value.startsWith("/")

                    if (lastWordStartsWithSlash) expanded.value = true
                    else expanded.value = false
                },
                onValueChange = {
                    val words = it.text.lines().flatMap { it.split(" ") }
                    lastWord.value = words.lastOrNull() ?: ""
                    lastWordStartsWithSlash = lastWord.value.startsWith("/")

                    if (lastWordStartsWithSlash) expanded.value = true
                    else expanded.value = false
                },
                expanded = expanded
            )
        }
    }
}

@Preview
@Composable
fun AddScreenPreview() {
    AddScreen {}
}

@Preview
@Composable
fun CustomWarningDialogPreview() {
    AlertScreen(
        AlertScreenData("", "")
    )
}

