package com.selfpro.navigation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.navigation.fragment.findNavController

class BottomNavigationBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    /**
     * Color of the [BottomNavigationBar]'s Top Line stroke.
     */
    private var barTopLineColor: Int

    /**
     * Color of the [BottomNavigationBar]'s Background.
     */
    private var barBackgroundColor: Int

    /**
     * Set the start destination state of the [navigationRoute]
     *
     * **Why is it -1?** If it's set to -1, the first destination is automatically selected
     * @param navigationStart The start destination, in fragment navigation id
     */
    @IdRes
    var navigationStart: Int = -1

    /**
     * Set the navigation data
     * @param navigationRoute The Navigation Data, in [NavigationRoute]
     */
    var navigationRoute: NavigationRoute? = null
        set(value) {
            field = value

            value?.apply {
                val fragmentContainer = fragmentContainer
                val navDestinations = navDestinations
                val activity = appCompatActivity

                val navHost = activity.supportFragmentManager.findFragmentById(fragmentContainer)

                //Check NavData is Available
                for (navData in navDestinations) {
                    if (navigationStart != -1 && navData.navDestination == navigationStart) {
                        navHost?.findNavController()?.navigate(navigationStart)
                        navData.navItem.changeCheckState(true)
                        break
                    }
                }

                //Set NavItem ClickEvent
                navDestinations.map { navData ->
                    navData.apply {
                        navItem.setOnClickListener {

                            //Check NavData is Disposable
                            if (!disposable) {

                                //Handle NavItem Checkable State (Features Like a Radio)
                                navDestinations.map { it.navItem.changeCheckState(false) }
                                navItem.changeCheckState(true)
                            }

                            //Navigate to Destination with [NavOptions] and [ClickEvent]
                            navHost?.findNavController()?.navigate(navDestination, null, navOptions)
                            clickEvent()
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