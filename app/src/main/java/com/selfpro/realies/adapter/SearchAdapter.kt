package com.selfpro.realies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.selfpro.realies.R
import com.selfpro.realies.databinding.ItemSearchBinding
import com.selfpro.realies.util.base.BaseListAdapter

class SearchAdapter(val onClick: (String) -> (Unit)) :
    BaseListAdapter<String, ItemSearchBinding>(R.layout.item_search) {
    override fun action(data: String, binding: ItemSearchBinding, position: Int) {
        binding.root.setOnClickListener { onClick(data) }
        binding.tv.text = data

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}