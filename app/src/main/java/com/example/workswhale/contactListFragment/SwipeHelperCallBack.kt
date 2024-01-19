package com.example.workswhale.contactListFragment

import android.annotation.SuppressLint
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.example.workswhale.ConstValues
import com.example.workswhale.R
import kotlin.math.max

// 롱터치 후 드래그, 스와이프 동작 제어
class SwipeHelperCallback(private val recyclerViewAdapter: ContactAdapter) :
    ItemTouchHelper.Callback() {

    private val background = ColorDrawable()
    private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }

    // swipe_view 를 swipe 했을 때 <삭제> 화면이 보이도록 고정하기 위한 변수들
    private var currentPosition: Int? = null    // 현재 선택된 recycler view의 position
    private var previousPosition: Int? = null   // 이전에 선택했던 recycler view의 position
    private var currentDx = 0f                  // 현재 x 값
    private var clamp = 0f                      // 고정시킬 크기

    // 이동 방향 결정하기
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        // 드래그 방향 : 위, 아래 인식
        // 스와이프 방향 : 왼쪽, 오른쪽 인식
        // 설정 안 하고 싶으면 0
        return makeMovementFlags(0, RIGHT)
    }


    // 드래그 일어날 때 동작 (롱터치 후 드래그)
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        Log.d("ASDF", "실행 : onMove")

        // 리사이클러뷰에서 현재 선택된 데이터와 드래그한 위치에 있는 데이터를 교환
//        val fromPos: Int = viewHolder.adapterPosition
//        val toPos: Int = target.adapterPosition
//        recyclerViewAdapter.swapData(fromPos, toPos)
        return true
    }

    // 스와이프 일어날 때 동작
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Log.d("ASDF", "실행 : onSwiped")

        // 스와와이프 끝까지 하면 해당 데이터 삭제하기 -> 스와이프 후 <삭제> 버튼 눌러야 삭제 되도록 변경
        if (viewHolder.itemViewType == ConstValues.VIEW_TYPE_TITLE) return
        recyclerViewAdapter.removeData(viewHolder.layoutPosition)
    }

    @SuppressLint("ResourceAsColor")
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        Log.d("ASDF", "실행 : onChildDraw")
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top
        val isCanceled = dX == 0f && !isCurrentlyActive

        if (isCanceled) {
            clearCanvas(
                c,
                itemView.left + dX,
                itemView.top.toFloat(),
                itemView.left.toFloat(),
                itemView.bottom.toFloat()
            )
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        if (actionState == ACTION_STATE_SWIPE) {
            if (viewHolder.itemViewType == ConstValues.VIEW_TYPE_TITLE) return
        }

        // Draw the red delete background
        background.color = Color.parseColor("#FF0000")
        background.setBounds(
            itemView.left + dX.toInt(),
            itemView.top,
            itemView.left,
            itemView.bottom
        )
        background.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
        c?.drawRect(left, top, right, bottom, clearPaint)
    }

    private fun getView(viewHolder: RecyclerView.ViewHolder): View =
        viewHolder.itemView.findViewById(
            R.id.item
        )

}
