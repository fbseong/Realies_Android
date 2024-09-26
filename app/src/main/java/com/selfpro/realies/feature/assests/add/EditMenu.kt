package com.selfpro.realies.feature.assests.add

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.selfpro.realies.R
import com.selfpro.realies.data.model.EditCombination
import com.selfpro.realies.data.model.MenuAssets
import com.selfpro.realies.ui.color.SpColor
import com.selfpro.realies.util.SpLog
import kotlinx.coroutines.launch
import kotlin.jvm.Throws

@Composable
fun MenuTextField(
    globalFieldValueList: MutableState<List<MenuAssets>>,
    globalIndex: Int,
    onValueChange: (TextFieldValue) -> Unit,
    absoluteCursorPosition: MutableState<IntOffset>,
    lazyListState: LazyListState,

    deleteSlash: MutableState<Int?>,
    focused: (Int) -> Unit,
    combination: MutableState<EditCombination?>

) {

    var fieldPosition by remember { mutableStateOf(Offset.Zero) }
    var cursorPosition by remember { mutableStateOf(Offset.Zero) }

    var isFocused by remember { mutableStateOf(false) }
    var text =
        remember { mutableStateOf(TextFieldValue((globalFieldValueList.value[globalIndex] as MenuAssets.Text).data)) }

//    val a = TextFieldValue((globalFieldValueList.value[globalIndex] as MenuAssets.Text).data)

    if (combination.value != null && combination.value!!.index == globalIndex) {
        text.value = TextFieldValue(combination.value!!.value)
        combination.value = null
    }

    val coroutineScope = rememberCoroutineScope()

    if (deleteSlash.value == globalIndex) {

        val cursor = text.value.selection.start

        // 커서 바로 앞의 슬래시를 지우는 로직
        if (cursor > 0 && text.value.text[cursor - 1] == '/') {


            text.value = TextFieldValue(text.value.text.removeRange(cursor - 1, cursor))

            deleteSlash.value = null

            text.value = TextFieldValue(
                text = text.value.text,
                selection = TextRange(cursor - 1)
            )
        }
    }

    BasicTextField(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .padding(vertical = 5.dp)
            .background(SpColor.BoxGray, shape = RoundedCornerShape(12.dp))
            .padding(15.dp)
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                focused(globalIndex)
                isFocused = focusState.isFocused
            }
            .onGloballyPositioned { coordinates ->
                // 각 TextField의 위치를 저장 (스크롤 위치 포함)
                if (isFocused) fieldPosition = coordinates.localToWindow(Offset.Zero)

            }
            .onKeyEvent { event: KeyEvent ->
                // Backspace 키가 눌렸고, 텍스트 길이가 0일 때
                if (event.key == Key.Backspace &&
                    text.value.selection.start == 0 &&
                    globalIndex != 0 &&
                    globalFieldValueList.value[globalIndex - 1] is MenuAssets.Image
                ) {
                    globalFieldValueList.value = globalFieldValueList.value
                        .toMutableList()
                        .apply {
                            //앞에 text랑 함치기
                            if (text.value.text.isNotEmpty()) {
                                val closeTextFieldIndex =
                                    globalFieldValueList.value
                                        .take(globalIndex) // 앞쪽 항목들 가져오기
                                        .mapIndexedNotNull { index, item ->
                                            if (item is MenuAssets.Text) index else null
                                        }
                                        .lastOrNull()
                                // 결과 출력
                                if (closeTextFieldIndex != null) {

                                    val closeText =
                                        globalFieldValueList.value[closeTextFieldIndex] as MenuAssets.Text
                                    set(
                                        closeTextFieldIndex,
                                        MenuAssets.Text(closeText.data + "\n" + text.value.text)
                                    )
                                    combination.value = EditCombination(
                                        closeTextFieldIndex,
                                        closeText.data + "\n" + text.value.text
                                    )


                                }
                            }
                            SpLog.d(globalIndex)
//                            removeAt(globalIndex)
                            removeAt(globalIndex - 1)
                            removeAt(globalIndex - 1)
//                            removeAt(globalIndex-2)
                        }
                    true // 이벤트 처리 완료
                } else {
                    false // 이벤트 처리 안 함
                }
            },
        onTextLayout = { textLayoutResult: TextLayoutResult ->
            // 커서의 상대적인 위치 가져오기
            if (isFocused) {
                val cursorRect = textLayoutResult.getCursorRect(text.value.text.length)
                cursorPosition = cursorRect.topLeft
            }
        },

        value = text.value,
        onValueChange = {
            text.value = it

            globalFieldValueList.value = globalFieldValueList.value.toMutableList().apply {
                try {
                    this[globalIndex] = MenuAssets.Text(it.text)
                } catch (e: IndexOutOfBoundsException) {
                    SpLog.e(
                        "IdexOutOfBoundsException",
                        "List: $globalFieldValueList Index: $globalIndex"
                    )
                }

            }

            onValueChange(text.value)

            absoluteCursorPosition.value = IntOffset(
                x = 0,
                y = (fieldPosition.y + cursorPosition.y).toInt()
            )

            coroutineScope.launch {
                lazyListState.animateScrollToItem(globalIndex)
            }
        },
        textStyle = TextStyle(
            color = SpColor.Black,
            fontSize = 14.sp
        ),
        decorationBox = { innerTextField ->
            if (text.value.text.isEmpty() && globalIndex == 0) {
                Text(
                    text = "기사 입력..",
                    color = SpColor.StrokeGray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 100.dp)
                )
            }
            innerTextField()
        },
    )

}

@Composable
fun MenuImageList(
    globalFieldValueList: MutableState<List<MenuAssets>>,
    globalIndex: Int
) {
//    val menuImageList = remember { mutableStateOf(listOf<Uri?>(null)) }
    LazyRow(
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        try {
            if (globalFieldValueList.value[globalIndex] is MenuAssets.Image) {
                items((globalFieldValueList.value[globalIndex] as MenuAssets.Image).imageList.size) { i ->
                    MenuImage(globalFieldValueList, globalIndex, i)
                }
            }
        }catch (e: IndexOutOfBoundsException){
            SpLog.e(
                "IdexOutOfBoundsException",
                "List: $globalFieldValueList Index: $globalIndex"
            )
        }
    }
}

@Composable
fun MenuImage(
    globalFieldValueList: MutableState<List<MenuAssets>>,
    globalIndex: Int,
    index: Int
) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            globalFieldValueList.value = globalFieldValueList.value.toMutableList().apply {
                (this[globalIndex] as MenuAssets.Image).let { imageAssets ->
                    this[globalIndex] = imageAssets.copy(
                        imageList = imageAssets.imageList.toMutableList().apply {
                            if (imageAssets.imageList[index] == null) {
                                add(null)
                            }
                            set(index, uri)
                        }
                    )
                }
            }
        }

    val interactionSource = remember { MutableInteractionSource() }

    Box {
        val imageUriList =
            (globalFieldValueList.value[globalIndex] as MenuAssets.Image).imageList
        val imageUri = imageUriList[index]

        if (imageUri == null) {
            Box(modifier = Modifier
                .background(color = SpColor.BoxGray, shape = RoundedCornerShape(12.dp))
                .width(170.dp)
                .height(170.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    launcher.launch("image/*")
                }) {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(18.dp)
                        .width(18.dp),
                    painter = painterResource(id = R.drawable.ic_menu_image_add),
                    contentDescription = null
                )
            }
        }
        imageUri?.let {
            SubcomposeAsyncImage(
                model = it,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(170.dp)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(15.dp)),
            )
        }
    }
}

@Composable
fun MenuMinTitle(
    globalFieldValueList: MutableState<List<MenuAssets>>,
    globalIndex: Int,
    lazyListState: LazyListState
) {
    var text = remember { mutableStateOf(TextFieldValue("")) }

    val coroutineScope = rememberCoroutineScope()

    BasicTextField(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .padding(vertical = 5.dp)
            .background(SpColor.BoxGray, shape = RoundedCornerShape(12.dp))
            .padding(15.dp)
            .fillMaxWidth(),
        value = text.value,
        onValueChange = {
            text.value = it

            globalFieldValueList.value = globalFieldValueList.value.toMutableList().apply {
                this[globalIndex] = MenuAssets.MinTitle(it.text)
            }
            coroutineScope.launch {
                lazyListState.animateScrollToItem(globalIndex)
            }
        },
        textStyle = TextStyle(
            color = SpColor.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        ),
        decorationBox = { innerTextField ->
            if (text.value.text.isEmpty()) {
                Text(
                    text = "소제목 입력..",
                    color = SpColor.StrokeGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            innerTextField()
        },
    )
}

fun editTextValue(
    globalFieldValueList: MutableState<List<MenuAssets>>,
    globalIndex: Int,
    text: String
) {
    globalFieldValueList.value = globalFieldValueList.value.toMutableList().apply {
        this[globalIndex] = MenuAssets.Text(text)
    }
}