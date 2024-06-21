package com.selfpro.realies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.selfpro.realies.R
import com.selfpro.realies.data.model.NewsThumbModel
import com.selfpro.realies.databinding.ItemNewsThumbBinding
import com.selfpro.realies.feature.main.realies.RealiesViewModel
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.base.BaseListAdapter
import kotlinx.coroutines.flow.collect

class NewsThumbAdapter(val getRealiesTitle: (String) -> (String)) :
    BaseListAdapter<NewsThumbModel, ItemNewsThumbBinding>(R.layout.item_news_thumb) {
    override fun action(data: NewsThumbModel, binding: ItemNewsThumbBinding, position: Int) {
        binding.tvTitle.text = data.title

        if (data.provider != null) {

            if (data.images.isNotEmpty()) {
                Glide.with(binding.root)
                    .load(data.images[0])
                    .centerCrop()
                    .into(binding.ivThumb)
            }
        }

        if (data.content.isNotEmpty()) {
            binding.tvTitle.text = getRealiesTitle(data.content)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            ItemNewsThumbBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}