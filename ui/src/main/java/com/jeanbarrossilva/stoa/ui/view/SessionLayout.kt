package com.jeanbarrossilva.stoa.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import com.google.android.material.button.MaterialButton
import com.jeanbarrossilva.stoa.extensions.context.colorOf
import com.jeanbarrossilva.stoa.extensions.context.withStyledAttributes
import com.jeanbarrossilva.stoa.extensions.number.dp
import com.jeanbarrossilva.stoa.extensions.view.textview.setTypeface
import com.jeanbarrossilva.stoa.ui.R

class SessionLayout @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {
    private var shouldAddChildrenToContentLayout = false

    private val contentIntroducerLayout = LinearLayout(context, attrs, defStyleAttr).apply {
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        gravity = Gravity.CENTER_VERTICAL
        setPadding(25.dp(context))
        updatePadding(bottom = 0)
    }
    private val headlineLayout = ColumnLayout(context, attrs, defStyleAttr).apply {
        layoutParams = LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            weight = 0.7f
        }
        spacing = 2.dp(context)
    }
    private val titleView = TextView(context, attrs, defStyleAttr).apply {
        textSize = 20f
        setTypeface(R.font.android_euclid)
    }
    private val descriptionView = TextView(context, attrs, defStyleAttr).apply {
        setTextColor(context colorOf android.R.attr.textColorSecondary)
    }
    private val actionButton = MaterialButton(context, attrs, defStyleAttr).apply {
        layoutParams = LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            weight = 0.3f
        }
        gravity = Gravity.END or Gravity.CENTER_VERTICAL
        isAllCaps = false
        textAlignment = TEXT_ALIGNMENT_GRAVITY
        letterSpacing = 0f
        background = null
        cornerRadius = 50.dp(context)
        iconSize = 12.dp(context)
        iconGravity = MaterialButton.ICON_GRAVITY_END
        iconTint = ColorStateList.valueOf(context colorOf R.attr.colorPrimary)
        setPadding(0)
        setText(R.string.SessionLayout_actionButtonTitle)
        setTextColor(context colorOf R.attr.colorPrimary)
        setIconResource(R.drawable.ic_round_arrow_forward_ios_24)
    }
    private val contentLayout = FrameLayout(context, attrs, defStyleAttr).apply {
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    var title = ""
        set(title) {
            field = title
            titleView.text = title
        }
    var description = ""
        set(description) {
            field = description
            descriptionView.text = description
        }
    var actionButtonTitle = ""
        set(buttonTitle) {
            field = buttonTitle
            actionButton.text = buttonTitle
        }
    var spacing = 0
        set(spacing) {
            field = spacing
            contentIntroducerLayout.updatePadding(bottom = spacing)
        }

    init {
        config()
        getAttrs()
        addViews()
        setDefaultValues()
        populateForPreview()
    }

    private fun config() {
        orientation = VERTICAL
    }

    private fun addViews() {
        addView(contentIntroducerLayout)
        contentIntroducerLayout.addView(headlineLayout)
        headlineLayout.addView(titleView)
        headlineLayout.addView(descriptionView)
        contentIntroducerLayout.addView(actionButton)
        addView(contentLayout)
        shouldAddChildrenToContentLayout = true
    }

    private fun setDefaultValues() {
        actionButtonTitle = context.getString(R.string.SessionLayout_actionButtonTitle)
        spacing = 25.dp(context)
    }

    private fun getAttrs() {
        context.withStyledAttributes(attrs, defStyleAttr, R.styleable.SessionLayout) { index ->
            when (index) {
                R.styleable.SessionLayout_title -> title = getString(index).toString()
                R.styleable.SessionLayout_description -> description = getString(index).toString()
                R.styleable.SessionLayout_actionButtonTitle -> actionButtonTitle = getString(index).toString()
                R.styleable.SessionLayout_spacing -> spacing = getDimension(index, spacing.toFloat()).toInt()
            }
        }
    }

    private fun populateForPreview() {
        if (isInEditMode) {
            title = context.getString(R.string.SessionLayout_popular_title)
            description = context.getString(R.string.SessionLayout_popular_description)
        }
    }

    fun setOnActionButtonClickListener(listener: OnClickListener) {
        actionButton.setOnClickListener(listener)
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (shouldAddChildrenToContentLayout) contentLayout.addView(child) else super.addView(child, index, params)
    }
}