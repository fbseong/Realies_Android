package com.selfpro.realies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.selfpro.realies.R
import com.selfpro.realies.data.model.NewsThumbModel
import com.selfpro.realies.data.network.ktor.ClientKtor
import com.selfpro.realies.databinding.ItemNewsThumbBinding
import com.selfpro.realies.feature.main.realies.RealiesViewModel
import com.selfpro.realies.util.RealiesTitle
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.base.BaseListAdapter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsThumbAdapter(
    val viewLifecycleOwner: LifecycleOwner,
    val summarizeTitle: (MutableSharedFlow<String>, String, String) -> (Unit),
) :
    BaseListAdapter<NewsThumbModel, ItemNewsThumbBinding>(R.layout.item_news_thumb) {
    override fun action(data: NewsThumbModel, binding: ItemNewsThumbBinding, position: Int) {

        val _titleSummaryFLow = MutableSharedFlow<String>()
        val titleSummaryFlow = _titleSummaryFLow.asSharedFlow()

        if (data.provider != null) {

            if (data.images.isNotEmpty()) {
                Glide.with(binding.root)
                    .load(data.images[0])
                    .into(binding.ivThumb)
            }
        }

        if (data.content.isNotEmpty()) summarizeTitle(_titleSummaryFLow, data.title, data.content)

        viewLifecycleOwner.lifecycleScope.launch {
            titleSummaryFlow.collect { summarizeTitle ->
                binding.composeTitle.setContent {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = summarizeTitle,
                        textAlign = TextAlign.Left,
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            ItemNewsThumbBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}