package com.udacity

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

private enum class ButtonStatus {
    NOT_CLICKED,
    CLICKED
}

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Button size
    private var widthSize = 0
    private var heightSize = 0

    private val buttonPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = context.getColor(R.color.colorPrimary)
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.NORMAL)
        color = Color.WHITE
    }

    private val click = ButtonStatus.NOT_CLICKED

    private val valueAnimator = ValueAnimator()

    private var buttonState: ButtonState by Delegates.observable(ButtonState.Completed) { p, old, new ->

    }


    init {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    /**
     * Render the view on the screen
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Set the color depending on if the button is clicked or not
        buttonPaint.color = if (click == ButtonStatus.NOT_CLICKED) {
            context.getColor(R.color.colorPrimary)
        } else {
            context.getColor(R.color.colorPrimaryDark)
        }

        canvas?.drawRect(0.0f, heightSize.toFloat(), widthSize.toFloat(), 0.0f, buttonPaint)

        val label = resources.getString(R.string.button_download_text)
        canvas?.drawText(
            label,
            (widthSize / 2).toFloat(),
            (heightSize / 2 - (textPaint.descent() + textPaint.ascent() / 2)),
            textPaint
        )


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val width: Int = resolveSizeAndState(minWidth, widthMeasureSpec, 1)
        val height: Int = resolveSizeAndState(
            View.MeasureSpec.getSize(width),
            heightMeasureSpec,
            0
        )
        widthSize = width
        heightSize = height
        setMeasuredDimension(width, height)
    }
}