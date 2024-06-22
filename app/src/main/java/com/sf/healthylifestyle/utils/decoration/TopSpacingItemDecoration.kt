package ed.maevski.minideviantart.view.decoration

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TopSpacingItemDecoration(
    private val topPaddingInDp: Int,
    private val bottomPaddingInDp: Int,
    private val rightPaddingInDp: Int,
    private val leftPaddingInDp: Int,
) : RecyclerView.ItemDecoration() {
    private val Int.convertPx: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = topPaddingInDp.convertPx
        outRect.bottom = bottomPaddingInDp.convertPx
        outRect.right = rightPaddingInDp.convertPx
        outRect.left = leftPaddingInDp.convertPx
    }
}