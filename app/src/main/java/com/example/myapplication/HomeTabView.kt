package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.view_home_tab.view.home_tab_image
import kotlinx.android.synthetic.main.view_home_tab.view.home_tab_text

class HomeTabView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    init {
        gravity = Gravity.CENTER_HORIZONTAL
        orientation = LinearLayout.VERTICAL
        View.inflate(context, R.layout.view_home_tab, this)
    }

    fun setText(@StringRes textId: Int) {
        home_tab_text!!.setText(textId)
    }

    fun setImageResource(@DrawableRes resId: Int) {
        home_tab_image!!.setImageResource(resId)
    }
}
