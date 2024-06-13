package com.selfpro.navigation

import com.selfpro.navigation.R
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class BottomNavigationItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private var itemTextView: TextView
    private var itemIconView: ImageView

    private val src: Int
    private val itemText: String
    private var isClicked: Boolean

    private val itemTextTint: Int
    private val itemIconTint: Int

    private val itemTextTintClicked: Int
    private val itemIconTintClicked: Int

    init {
        val layout =
            LayoutInflater.from(context).inflate(R.layout.bottom_navigation_item, this, true)
        itemTextView = layout.findViewById(R.id.bottom_navigation_item_text)
        itemIconView = layout.findViewById(R.id.bottom_navigation_item_icon)

        context.obtainStyledAttributes(attrs, R.styleable.BottomNavigationItem, defStyle, 0).apply {
            src = getResourceId(R.styleable.BottomNavigationItem_itemIcon, -1)
            itemText = getString(R.styleable.BottomNavigationItem_itemText).orEmpty()
            isClicked = getBoolean(R.styleable.BottomNavigationItem_isClicked, false)

            itemTextTint = getColor(R.styleable.BottomNavigationItem_itemTextTint, Color.BLACK)
            itemIconTint = getColor(R.styleable.BottomNavigationItem_itemIconTint, -1)

            itemTextTintClicked = getColor(R.styleable.BottomNavigationItem_itemTextTintClicked, Color.BLACK)
            itemIconTintClicked = getColor(R.styleable.BottomNavigationItem_itemIconTintClicked, -1)

            recycle()
        }

        itemTextView.text = itemText
        changeCheckState(isClicked)

        if (src != -1) itemIconView.setImageResource(src)
    }

    internal fun changeCheckState(mIsClicked: Boolean) {
        isClicked = mIsClicked
        itemIconView.isActivated = mIsClicked
        if (mIsClicked) {
            itemTextView.setTextColor(itemTextTintClicked)

            if (itemIconTintClicked != -1)
                itemIconView.imageTintList = ColorStateList.valueOf(itemIconTintClicked)

        } else {
            itemTextView.setTextColor(itemTextTint)

            if (itemIconTint != -1)
                itemIconView.imageTintList = ColorStateList.valueOf(itemIconTint)
        }
    }
}