package com.selfpro.realies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.selfpro.realies.R
import com.selfpro.realies.data.model.NewsThumbModel.ThumbSubModel
import com.selfpro.realies.databinding.ItemNewsThumbSubBinding
import com.selfpro.realies.util.base.BaseListAdapter

class NewsThumbSubAdapter : BaseListAdapter<ThumbSubModel, ItemNewsThumbSubBinding>(
    R.layout.item_news_thumb_sub
) {
    override fun action(data: ThumbSubModel, binding: ItemNewsThumbSubBinding, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            ItemNewsThumbSubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}