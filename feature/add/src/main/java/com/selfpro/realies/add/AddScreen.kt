package com.selfpro.realies.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import com.selfpro.realies.data.model.AlertScreenData
import com.selfpro.realies.data.model.EditCombination
import com.selfpro.realies.data.model.MenuAssets
import com.selfpro.realies.data.model.NewsAddMenuModel
import com.selfpro.realies.data.model.Route
import com.selfpro.realies.shared.ContentCode
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.common.SpLog
import com.selfpro.realies.util.icon.IcMenuAi
import com.selfpro.realies.util.icon.IcMenuImage
import com.selfpro.realies.util.icon.IcMenuTextmin
import com.selfpro.realies.util.icon.SpIcon

@Composable
fun AddScreen(routeData: Route.RouteData,) {

    //뉴스 전체 값
    var globalFieldValueList = remember { mutableStateOf(listOf<MenuAssets>(MenuAssets.Text())) }

    val globalFieldValueText = globalFieldValueList.value
        .filterIsInstance<MenuAssets.Text>()
        .joinToString("\n") { it.data }

    var showDialog by remember { mutableStateOf(false) }//다이얼로그 표시
    var alertData by remember { mutableStateOf(AlertScreenData("", "")) }//팝업 다이얼로그 데이터
    if (showDialog) AlertScreen(alertData)  //다이얼로그 띄우기

    var absoluteCursorPosition = remember { mutableStateOf(IntOffset(0, 0)) }   //커서 절대 좌표
    var expanded = remember { mutableStateOf(false) }   //드롭 메뉴 띄우기

    val focusedMenuIndex = remember { mutableIntStateOf(0) }

    val lazyListState = rememberLazyListState()

    val deleteSlash = remember {
        mutableStateOf<Int?>(null)
    }

    val items = listOf(
        NewsAddMenuModel(
            title = "이미지", description = "파일을 업로드하여 추가하세요", icon = SpIcon.IcMenuImage
        ) {
            globalFieldValueList.value = globalFieldValueList.value.toMutableList().apply {
                add(MenuAssets.Image(ContentCode.Image))
                add(MenuAssets.Text(ContentCode.Text))
            }
            expanded.value = false

            deleteSlash.value = focusedMenuIndex.intValue
        },
        NewsAddMenuModel(
            title = "AI 자동 생성",
            description = "AI를 통해 뉴스를 완성하세요",
            icon = SpIcon.IcMenuAi
        ) {},
        NewsAddMenuModel(
            title = "소제목",
            description = "섹션 제목 설정",
            icon = SpIcon.IcMenuTextmin
        ) {
            globalFieldValueList.value = globalFieldValueList.value.toMutableList().apply {
                add(MenuAssets.MinTitle(ContentCode.TItle))
                add(MenuAssets.Text(ContentCode.Text))
            }
            expanded.value = false

            deleteSlash.value = focusedMenuIndex.intValue
        })

    val editCombination = remember { mutableStateOf<EditCombination?>(null) }

    Column(
        modifier = Modifier
            .background(SpColor.White)
            .fillMaxSize()
    ) {
        val offset = absoluteCursorPosition.value

        if (expanded.value) {
            Popup(
                offset = offset,
                onDismissRequest = {
                    expanded.value = false
                }) {
                DropMenuLazyRow(items)
            }
        }

        LazyColumn(state = lazyListState) {
            item {
                Column {
                    var titleFieldValue by rememberSaveable { mutableStateOf("") }
                    var isVisible by remember { mutableStateOf(false) }
                    var isLoading by remember { mutableStateOf(false) }
                    var isTitleInit by remember { mutableStateOf(false) }   //제목 초기화 여부
                    var isSend by remember { mutableStateOf(false) }        //전송 여부

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                    ) {
                        CloseBox {
                            alertData = AlertScreenData(
                                title = "작성중인 뉴스가 있습니다.",
                                message = "저장되지 않고 나갈 경우 지금까지 작성한 내용이\n사라집니다.",
                                onConfirm = {},
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
                            titleFieldValue = titleFieldValue, isLoading = isLoading
                        )
                    }
                    Row(Modifier.padding(top = 5.dp)) {
                        GenerateTitleButton(
                            modifier = Modifier.weight(1f),
                            isLoading = isLoading,
                            isVisible = isVisible
                        ) {
                            if (globalFieldValueText.length < 30) {
                                alertData = AlertScreenData(title = "뉴스 제목을 생성할 수 없습니다.",
                                    message = "30자 이하는 뉴스 제목을 생성할 수 없습니다.",
                                    confirmText = "확인",
                                    onConfirm = { showDialog = false })
                                showDialog = true
                            } else {
                                isVisible = true
                                isLoading = true
                                isTitleInit = true
                            }
                        }
                        AnimatedVisibility(visible = isVisible) {
                            TitleReport {}
                        }
                    }
                }


            }



            items(count = globalFieldValueList.value.size) { index ->
                if (globalFieldValueList.value[index] is MenuAssets.Text) {
                    MenuTextField(
                        globalFieldValueList = globalFieldValueList,
                        globalIndex = index,
                        onValueChange = { expanded.value = it.text.lastOrNull() == '/' },
                        absoluteCursorPosition = absoluteCursorPosition,
                        lazyListState = lazyListState,
                        deleteSlash = deleteSlash,
                        focused = { focusedMenuIndex.value = it },
                        combination = editCombination
                    )
                } else if (globalFieldValueList.value[index] is MenuAssets.Image) {
                    MenuImageList(
                        globalFieldValueList = globalFieldValueList,
                        globalIndex = index

                    )
                } else if (globalFieldValueList.value[index] is MenuAssets.MinTitle) {
                    MenuMinTitle(
                        globalFieldValueList = globalFieldValueList,
                        globalIndex = index,
                        lazyListState = lazyListState
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(300.dp))
            }
        }
    }
}