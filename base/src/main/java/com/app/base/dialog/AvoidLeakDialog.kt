package com.app.base.dialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.app.base.dialog.listener.WrappedCancelListener
import com.app.base.dialog.listener.WrappedDismissDialogListener
import com.app.base.dialog.listener.WrappedShowListener

/**
 * [see](https://github.com/TimLin1024/MessageMemoryLeakSample/blob/master/app/src/main/java/com/timlin/messagememoryleaksample/AvoidLeakedDialogFragment.kt)
 */
class AvoidLeakDialog @JvmOverloads constructor(
    context: Context,
    themeResId: Int = 0
) : AppCompatDialog(
    context,
    themeResId
) {
    // 主要是防止弱引用指向的 Listener被清除
    private var mOnDismissListener: DialogInterface.OnDismissListener? = null
    private var mOnCancelListener: DialogInterface.OnCancelListener? = null
    private var mOnShowListener: DialogInterface.OnShowListener? = null

    override fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
        mOnDismissListener = listener
        super.setOnDismissListener(WrappedDismissDialogListener(listener))
    }

    override fun setOnShowListener(listener: DialogInterface.OnShowListener?) {
        mOnShowListener = listener
        super.setOnShowListener(WrappedShowListener(listener))
    }

    override fun setOnCancelListener(listener: DialogInterface.OnCancelListener?) {
        mOnCancelListener = listener
        super.setOnCancelListener(WrappedCancelListener(listener))
    }
}

class AvoidLeakBottomSheetDialog @JvmOverloads constructor(
    context: Context,
    themeResId: Int = 0
) : BottomSheetDialog(
    context,
    themeResId
) {
    // 主要是防止弱引用指向的 Listener被清除
    private var mOnDismissListener: DialogInterface.OnDismissListener? = null
    private var mOnCancelListener: DialogInterface.OnCancelListener? = null
    private var mOnShowListener: DialogInterface.OnShowListener? = null

    override fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
        mOnDismissListener = listener
        super.setOnDismissListener(WrappedDismissDialogListener(listener))
    }

    override fun setOnShowListener(listener: DialogInterface.OnShowListener?) {
        mOnShowListener = listener
        super.setOnShowListener(WrappedShowListener(listener))
    }

    override fun setOnCancelListener(listener: DialogInterface.OnCancelListener?) {
        mOnCancelListener = listener
        super.setOnCancelListener(WrappedCancelListener(listener))
    }
}