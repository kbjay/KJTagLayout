package com.kbjay.kjtagview

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children


/**
 * 思路：
 *  测量出子view的尺寸，保存起来之后布局，处理换行
 *
 * @author kbjay
 * create at 2020/9/14 7:47 PM
 **/
class KJTagLayout(context: Context, attributeSet: AttributeSet?) :
    ViewGroup(context, attributeSet) {
    private var mChildrenPos = mutableListOf<Rect>()
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //1: 获取子view
        //2：调用子view的measure
        //3：获取子view的尺寸
        //4：填充mChildrenPos
        //5：保存自己的尺寸
        var width = 0
        var height = 0
        var currentHeightStartPos = 0
        var currentWidthStartPos = 0
        var maxLineHeight = 0
        var widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        children.forEachIndexed { index, childView ->
            var childPos = mChildrenPos.getOrNull(index)
            if (childPos == null) {
                childPos = Rect()
                mChildrenPos.add(index, childPos)
            }

            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0)
            val marginLayoutParams = childView.layoutParams as MarginLayoutParams
            if (widthSpecSize < currentWidthStartPos + childView.measuredWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin) {
                // 换行
                currentHeightStartPos += maxLineHeight
                maxLineHeight = 0
                currentWidthStartPos = 0
            }

            childPos.apply {
                left = currentWidthStartPos + marginLayoutParams.leftMargin
                right =
                    currentWidthStartPos + marginLayoutParams.leftMargin + childView.measuredWidth
                top = currentHeightStartPos + marginLayoutParams.topMargin
                bottom =
                    currentHeightStartPos + marginLayoutParams.topMargin + childView.measuredHeight
            }

            maxLineHeight =
                maxLineHeight.coerceAtLeast(childView.measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin)
            currentWidthStartPos += (childView.measuredWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin)
            width = width.coerceAtLeast(currentWidthStartPos)
            height =
                height.coerceAtLeast(currentHeightStartPos + childView.measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin)
        }

        setMeasuredDimension(
            resolveSize(widthSpecSize, widthMeasureSpec),
            resolveSize(height, heightMeasureSpec)
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        children.forEachIndexed { index, childView ->
            childView.layout(
                mChildrenPos[index].left,
                mChildrenPos[index].top,
                mChildrenPos[index].right,
                mChildrenPos[index].bottom
            )
        }
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}