package com.jeanbarrossilva.stoa.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import android.widget.FrameLayout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.jeanbarrossilva.stoa.extensions.context.withStyledAttributes
import com.jeanbarrossilva.stoa.ui.R

class SessionLayout2 @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
): AbstractComposeView(context, attrs, defStyleAttr) {
    private var onActionButtonClickListener = OnClickListener {
    }

    private val contentLayout = FrameLayout(context, attrs, defStyleAttr)

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var actionButtonTitle by mutableStateOf(context.getString(R.string.SessionLayout_actionButtonTitle))
    var spacing by mutableStateOf(25.dp)

    init {
        getAttrs()
    }

    private fun getAttrs() {
        context.withStyledAttributes(attrs, defStyleAttr, R.styleable.SessionLayout) { index ->
            when (index) {
                R.styleable.SessionLayout_title -> title = getString(index).toString()
                R.styleable.SessionLayout_description -> description = getString(index).toString()
                R.styleable.SessionLayout_actionButtonTitle -> actionButtonTitle = getString(index).toString()
                R.styleable.SessionLayout_spacing -> spacing = getFloat(index, spacing.value).dp
            }
        }
    }

    @Composable
    override fun Content() {
        Column(
            Modifier
                .fillMaxWidth(),
            Arrangement.spacedBy(0.dp, Alignment.CenterVertically)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
                    .padding(bottom = spacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .fillMaxWidth(0.5f),
                    Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        title,
                        fontSize = 20.sp,
                        fontFamily = Font(R.font.android_euclid).toFontFamily()
                    )

                    Text(
                        description,
                        Modifier.alpha(ContentAlpha.medium),
                        fontFamily = Font(R.font.android_euclid_medium).toFontFamily()
                    )
                }

                Box(
                    Modifier
                        .fillMaxWidth(),
                    Alignment.CenterEnd
                ) {
                    AndroidView({
                        ComposeView(context)
                    }) { view ->
                        view.setContent {
                            Row(
                                Modifier
                                    .clip(RoundedCornerShape(50.dp))
                                    .clickable {
                                        onActionButtonClickListener.onClick(view)
                                    }
                                    .padding(2.dp),
                                Arrangement.spacedBy(5.dp),
                                Alignment.CenterVertically
                            ) {
                                Text(
                                    actionButtonTitle,
                                    color = MaterialTheme.colors.primary,
                                    fontFamily = Font(R.font.android_euclid_medium).toFontFamily(),
                                    textAlign = TextAlign.End
                                )

                                Icon(
                                    Icons.Rounded.ArrowForwardIos,
                                    contentDescription = null,
                                    Modifier
                                        .size(12.dp),
                                    tint = MaterialTheme.colors.primary
                                )
                            }
                        }
                    }
                }
            }

            AndroidView(
                {
                    contentLayout
                },
                Modifier
                    .fillMaxWidth()
            )
        }
    }

    fun setOnActionButtonClickListener(listener: OnClickListener) {
        onActionButtonClickListener = listener
    }
}

@Preview(showBackground = true)
@Composable
fun SessionLayout2_Preview() {
    SessionLayout2(LocalContext.current).apply {
        title = context.getString(R.string.SessionLayout_popular_title)
        description = context.getString(R.string.SessionLayout_popular_description)
    }.Content()
}