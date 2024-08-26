package com.selfpro.realies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import coil.compose.SubcomposeAsyncImage
import com.selfpro.realies.R
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.data.model.NewsThumbModel
import com.selfpro.realies.databinding.ItemNewsThumbBinding
import com.selfpro.realies.util.base.BaseListAdapter
import com.selfpro.realies.util.shimmerEffect.shimmerEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsThumbAdapter(
    val context: Context,
    val viewLifecycleOwner: LifecycleOwner,
    val summarizeTitle: (MutableStateFlow<LoadState>, String, String) -> (Unit),
) :
    BaseListAdapter<NewsThumbModel, ItemNewsThumbBinding>(R.layout.item_news_thumb) {
    override fun action(data: NewsThumbModel, binding: ItemNewsThumbBinding, position: Int) {

        if (data.provider != null) {
            if (data.images.isNotEmpty()) {
                binding.composeIv.setContent {
                    SubcomposeAsyncImage(
                        model = data.images[0],
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth,
                        loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(280.dp)
                                    .background(shimmerEffect())
                            )
                        }
                    )
                }
            }
        }
        val _titleSummaryFLow = MutableStateFlow<LoadState>(LoadState.Loading)
        val titleSummaryFlow = _titleSummaryFLow.asStateFlow()

        summarizeTitle(_titleSummaryFLow, data.title, data.content)

        viewLifecycleOwner.lifecycleScope.launch {
            titleSummaryFlow.collect { state ->
                binding.composeTitle.setContent {
                    Row {
                        ShimmersTextBox(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            data = data,
                            state = titleSummaryFlow
                        )
                        Text(
                            text = " · 0분전",
                            fontSize = 12.sp,
                            color = Color(context.getColor(R.color.index))
                        )
                    }
                }
            }
        }
    }

    private fun removeDots(inputString: String): String {
        val newString = inputString.replace(".", "")
        return newString
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            ItemNewsThumbBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @Composable
    fun ShimmersTextBox(modifier: Modifier, data: NewsThumbModel, state: StateFlow<LoadState>) {
        val textState by state.collectAsState()

        when (textState) {
            is LoadState.Loading -> {
                Box(
                    modifier = modifier
                        .background(
                            shimmerEffect(),
                            shape = RoundedCornerShape(3.dp)
                        )
                ) { Text(text = "제목", fontSize = 12.sp, color = Color.Transparent) }
            }

            is LoadState.Success -> {
                val summarizeTitle = removeDots((textState as LoadState.Success).data as? String ?: "")
                Text(
                    modifier = modifier,
                    text = summarizeTitle,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Left,
                    color = Color(context.getColor(R.color.index))
                )
            }

            is LoadState.Error -> {
                Text(
                    modifier = modifier,
                    text = data.title,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Left,
                    color = Color(context.getColor(R.color.index))
                )
            }
        }
    }
}