package com.kbjay.kjtagview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet

class KJColorTextView(context: Context, attributeSet: AttributeSet?) :
    androidx.appcompat.widget.AppCompatTextView(context, attributeSet) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        mPaint.color = when ((Math.random() * 10).toInt()) {
            1, 2 -> Color.RED
            3, 4 -> Color.BLUE
            5, 6 -> Color.GRAY
            7, 8 -> Color.GREEN
            else -> Color.YELLOW
        }
        canvas?.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), 10f, 10f, mPaint)
        super.onDraw(canvas)
    }
}