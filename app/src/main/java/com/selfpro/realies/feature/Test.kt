import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Popup
import com.selfpro.realies.ui.color.SpColor


@Preview
@Composable
fun CursorDropdownMenuPreview() {
    CursorDropdownMenuSample(true)
}

@Composable
fun CursorDropdownMenuSample(expanded: Boolean) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var cursorOffset by remember { mutableStateOf(Offset.Zero) }
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    var textFieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    val items = listOf("Option 1", "Option 2", "Option 3")
    val density = LocalDensity.current

    Column(modifier = Modifier.padding(16.dp)) {
        Box {
            BasicTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = SpColor.StrokeGray)
                    .padding(15.dp)
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                textStyle = TextStyle(fontSize = 12.sp),
                onTextLayout = { layoutResult ->
                    textLayoutResult = layoutResult
                    val cursorPosition = textFieldValue.selection.end
                    cursorOffset = layoutResult.getCursorRect(cursorPosition).topLeft
                }
            )

            // Popup 메뉴 표시
            if (expanded) {
                Popup(
                    offset = with(density) {
                        // textField의 위치와 cursorOffset을 사용하여 Popup 위치 조정
                        IntOffset(
                            0,
                            cursorOffset.y.toInt()
                        )
                    },
//                    onDismissRequest = { expanded = false }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .background(color = Color.Red)
                            .width(with(density) { textFieldSize.width.toDp() })
                    ) {
                        items.forEach { label ->
                            Text(
                                text = label,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        textFieldValue = textFieldValue.copy(text = label)
//                                        expanded = false
                                    }
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
