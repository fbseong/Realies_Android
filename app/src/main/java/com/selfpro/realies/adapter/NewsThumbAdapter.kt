package com.selfpro.realies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.selfpro.realies.R
import com.selfpro.realies.data.model.NewsThumbModel
import com.selfpro.realies.databinding.ItemNewsThumbBinding
import com.selfpro.realies.util.base.BaseListAdapter

class NewsThumbAdapter :
    BaseListAdapter<NewsThumbModel, ItemNewsThumbBinding>(R.layout.item_news_thumb) {
    override fun action(data: NewsThumbModel, binding: ItemNewsThumbBinding, position: Int) {
        binding.tvTitle.text = data.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            ItemNewsThumbBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}