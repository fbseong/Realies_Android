package com.selfpro.navigation

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class BottomNavigationBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private var barTopLineColor: Int
    private var barBackgroundColor: Int


    var navigationRoute: NavigationRoute? = null
        set(value) {
            field = value

            value?.apply {
                val fragmentContainer = fragmentContainer
                val navDestinations = navDestinations
                val activity = appCompatActivity

                val navHost = activity.supportFragmentManager.findFragmentById(fragmentContainer)

                navDestinations.map { navData ->
                    navData.apply {
                        navItem.setOnClickListener {
                            navDestinations.map { it.navItem.changeCheckState(false) }
                            navItem.changeCheckState(true)
                            navHost?.findNavController()?.navigate(navDestination)
                        }
                    }
                }
            }
        }

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.BottomNavigationBar, defStyle, 0)
        barTopLineColor =
            typedArray.getColor(R.styleable.BottomNavigationBar_barTopLineColor, Color.BLACK)
        barBackgroundColor =
            typedArray.getColor(R.styleable.BottomNavigationBar_barBackground, Color.WHITE)
        typedArray.recycle()
    }

    private val barTopLinePaint = Paint().apply {
        color = barTopLineColor
        strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics
        )
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.apply {
            drawColor(barBackgroundColor)
            drawLine(0f, 0f, width.toFloat(), 0f, barTopLinePaint)
        }
        super.dispatchDraw(canvas)

    }
}