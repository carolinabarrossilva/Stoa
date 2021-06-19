package com.jeanbarrossilva.stoa.ui.core.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import com.jeanbarrossilva.stoa.extensions.view.viewgroup.cardview.setContentPadding
import com.jeanbarrossilva.stoa.extensions.view.viewgroup.constraintlayout.constrain
import com.jeanbarrossilva.stoa.ui.core.R

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
     * A [Toolbar] that has its changes reflected in this [StoaToolbar].
     */
    inner class StoaAndroidToolbar internal constructor(context: Context) : Toolbar(context) {
        init {
            title = this@StoaToolbar.title
            navigationIcon = this@StoaToolbar.navigationIcon
        }

        override fun setTitle(title: CharSequence?) {
            super.setTitle(title)
            this@StoaToolbar.title = title.toString()
        }

        override fun setNavigationIcon(icon: Drawable?) {
            super.setNavigationIcon(icon)
            icon?.let {
                this@StoaToolbar.navigationIcon = icon
            }
        }

        override fun setNavigationOnClickListener(listener: OnClickListener?) {
            super.setNavigationOnClickListener(listener)
            listener?.let(this@StoaToolbar::setNavigationOnClickListener)
        }
    }

    private var navigationOnClickListener = OnClickListener {
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

    var navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_round_menu_24)
        set(icon) {
            field = icon
            navigationIconButton.setImageDrawable(icon)
        }
    var title = ""
        set(title) {
            field = title
            titleView.text = title
        }

    private fun config() {
        radius = 10.dp(context).toFloat()
        setContentPadding(20.dp(context))
    }

    private fun initViews(attrs: AttributeSet?, defStyleAttr: Int) {
        layout = ConstraintLayout(context, attrs, defStyleAttr)
        navigationIconButton = ImageButton(context, attrs, defStyleAttr).apply {
            imageTintList = ColorStateList.valueOf(context colorOf android.R.attr.textColorSecondary)
            setImageDrawable(navigationIcon)
            setOnClickListener(navigationOnClickListener)
        }
        titleView = TextView(context, attrs, defStyleAttr).apply {
            text = title
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
                R.styleable.StoaToolbar_navigationIcon -> getDrawable(index)?.let {
                    navigationIcon = it
                }
                R.styleable.StoaToolbar_title -> title = getString(index).toString()
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

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addViews()
        configLayoutParams()
        constrain()
    }

    fun setNavigationIcon(@DrawableRes iconRes: Int) {
        navigationIcon = ContextCompat.getDrawable(context, iconRes)
    }

    fun setNavigationOnClickListener(listener: OnClickListener) {
        navigationOnClickListener = listener
        navigationIconButton.setOnClickListener(listener)
    }

    fun setTitle(@StringRes titleRes: Int) {
        title = context.getString(titleRes)
    }

    companion object {
        private fun getDefaultMargin(context: Context): Int {
            return 10.dp(context)
        }

        private fun getDefaultHeightWithoutMargin(context: Context): Int {
            return 60.dp(context)
        }

        fun getDefaultHeight(context: Context): Int {
            return getDefaultMargin(context) + getDefaultHeightWithoutMargin(context)
        }
    }
}