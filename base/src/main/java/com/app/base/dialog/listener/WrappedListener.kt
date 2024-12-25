package com.app.base.dialog.listener

import android.content.DialogInterface
import java.lang.ref.WeakReference

class WrappedCancelListener(delegate: DialogInterface.OnCancelListener?) :
    DialogInterface.OnCancelListener {
    private var weakRef = WeakReference(delegate)

    override fun onCancel(dialog: DialogInterface?) {
        weakRef.get()?.onCancel(dialog)
    }
}

class WrappedDismissDialogListener(delegate: DialogInterface.OnDismissListener?) :
    DialogInterface.OnDismissListener {
    private var weakRef = WeakReference(delegate)

    override fun onDismiss(dialog: DialogInterface?) {
        weakRef.get()?.onDismiss(dialog)
        weakRef.clear()
    }
}

class WrappedShowListener(delegate: DialogInterface.OnShowListener?) :
    DialogInterface.OnShowListener {
    private var weakRef = WeakReference(delegate)

    override fun onShow(dialog: DialogInterface?) {
        weakRef.get()?.onShow(dialog)
    }
}