package com.lzm.wanandroidwithlittleblackboxstyle.view.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(private val verticalSpaceHeight: Int, private val horizontalSpaceWidth: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = verticalSpaceHeight
        outRect.bottom = verticalSpaceHeight
        outRect.left = horizontalSpaceWidth
        outRect.right = horizontalSpaceWidth
    }
}