package com.jeanbarrossilva.stoa.ui.core.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import com.jeanbarrossilva.stoa.extensions.context.colorOf
import com.jeanbarrossilva.stoa.extensions.context.withStyledAttributes
import com.jeanbarrossilva.stoa.extensions.number.dp
import com.jeanbarrossilva.stoa.extensions.view.viewgroup.constraintlayout.constrain
import com.jeanbarrossilva.stoa.ui.core.R
import com.jeanbarrossilva.stoa.ui.core.activity.StoaActivity
import com.jeanbarrossilva.stoa.ui.core.view.StoaToolbar.StoaAndroidToolbar

/**
 * Default toolbar of the app. Contains an equivalent Android [Toolbar] ([androidToolbar]), and changes applied to both are reflected
 * in both if it is not an exclusive feature of [StoaToolbar] or [Toolbar] (to see what features are interoperable, check what methods
 * are overridden in [StoaAndroidToolbar]). **Should be used in a [StoaActivity].**
 **/
class StoaToolbar: CardView {
    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr)
    }

    /**
     * A [Toolbar] that has its changes reflected in this [StoaToolbar]. All of this [StoaToolbar]'s changes are applied to it as well.
     */
    inner class StoaAndroidToolbar internal constructor(context: Context) : Toolbar(context) {
        init {
            navigationIcon = getDefaultNavigationIcon(context)
        }

        override fun setTitle(title: CharSequence?) {
            super.setTitle(title)
            this@StoaToolbar.titleView.text = title
        }

        override fun setNavigationIcon(icon: Drawable?) {
            super.setNavigationIcon(icon)
            this@StoaToolbar.navigationIconButton.setImageDrawable(icon)
        }

        override fun setNavigationOnClickListener(listener: OnClickListener?) {
            super.setNavigationOnClickListener(listener)
            this@StoaToolbar.navigationIconButton.setOnClickListener(listener)
        }
    }

    private lateinit var layout: ConstraintLayout
    private lateinit var navigationIconButton: ImageButton
    private lateinit var titleView: TextView

    /**
     * Android's [Toolbar] equivalent that is attached to this [StoaToolbar]. Should be used in
     * [AppCompatActivity.setSupportActionBar], since its changes will be passed to this [StoaToolbar].
     *
     * @see [StoaAndroidToolbar]
     **/
    lateinit var androidToolbar: StoaAndroidToolbar
        private set

    private fun config() {
        radius = 10.dp(context).toFloat()
        setContentPadding(getDefaultPadding(context), 0, getDefaultPadding(context), 0)
    }

    private fun initViews(attrs: AttributeSet?, defStyleAttr: Int) {
        layout = ConstraintLayout(context, attrs, defStyleAttr)
        navigationIconButton = ImageButton(context, attrs, defStyleAttr).apply {
            imageTintList = ColorStateList.valueOf(context colorOf android.R.attr.textColorSecondary)
        }
        titleView = TextView(context, attrs, defStyleAttr).apply {
            textAlignment = TEXT_ALIGNMENT_CENTER
            textSize = 15f
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(context colorOf android.R.attr.textColorSecondary)
        }
        androidToolbar = StoaAndroidToolbar(context)
    }

    private fun addViews() {
        addView(layout)
        layout.addView(navigationIconButton)
        layout.addView(titleView)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        context.withStyledAttributes(attrs, defStyleAttr, R.styleable.StoaToolbar) { index ->
            when (index) {
                R.styleable.StoaToolbar_navigationIcon -> androidToolbar.navigationIcon = getDrawable(index)
                R.styleable.StoaToolbar_title -> androidToolbar.title = getString(index).toString()
            }
        }
    }

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int = 0) {
        config()
        initViews(attrs, defStyleAttr)
        getAttrs(attrs, defStyleAttr)
    }

    private fun configLayoutParams() {
        this.layoutParams?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = getDefaultHeightWithoutMargin(context)
            (this as? MarginLayoutParams)?.setMargins(getDefaultMargin(context))
        }
        layout.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        navigationIconButton.layoutParams = ConstraintLayout.LayoutParams(24.dp(context), 24.dp(context))
    }

    private fun constrain() {
        layout.constrain(navigationIconButton) { id ->
            connect(id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            connect(id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        }
        layout.constrain(titleView) { id ->
            connect(id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            connect(id, ConstraintSet.START, navigationIconButton.id, ConstraintSet.END)
            connect(id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            connect(id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        }
    }

    internal fun performEntrance() {
        post {
            val (titleDefaultY, titleInitialY) = titleView.y to (this.height + titleView.height).toFloat()
            val (titleDefaultAlpha, titleInitialAlpha) = titleView.alpha to 0f

            titleView.y = titleInitialY
            titleView.alpha = titleInitialAlpha
            titleView.animate()
                .y(titleDefaultY)
                .alpha(titleDefaultAlpha)
                .start()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addViews()
        configLayoutParams()
        constrain()
    }

    companion object {
        private fun getDefaultMargin(context: Context): Int {
            return 10.dp(context)
        }

        private fun getDefaultHeightWithoutMargin(context: Context): Int {
            return 60.dp(context)
        }

        private fun getDefaultPadding(context: Context): Int {
            return 15.dp(context)
        }

        fun getDefaultHeight(context: Context): Int {
            return getDefaultMargin(context) + getDefaultHeightWithoutMargin(context)
        }

        fun getDefaultNavigationIcon(context: Context): Drawable {
            return ContextCompat.getDrawable(context, R.drawable.ic_round_menu_24)!!
        }
    }
}