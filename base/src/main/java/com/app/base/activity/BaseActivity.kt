package com.app.base.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    lateinit var mActivity: AppCompatActivity
    lateinit var mBinding: B

    private var mWindowInsetsControllerCompat: WindowInsetsControllerCompat? = null
    val windowInsetsControllerCompat get() = mWindowInsetsControllerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        mActivity = this
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.lifecycleOwner = this

        initStatusBar()
        initView()
        initViewClick()
        initData()
    }

    /**
     * 获取activity布局id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化状态栏
     */
    open fun initStatusBar() {
        setLightStatusBars()
        setWindowInsetsListener(mBinding.root)
        setNavigationBarColorColor(Color.WHITE)
    }

    /**
     * 初始化view点击事件
     */
    open fun initView() {}

    /**
     * 初始化view点击事件
     */
    open fun initViewClick() {}

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * @param bar true：文本颜色为黑色（浅色模式）
     *            false：文本颜色为白色（深色模式）
     */
    open fun setLightStatusBars(bar: Boolean = true) {
        mWindowInsetsControllerCompat = WindowCompat.getInsetsController(window, findViewById<FrameLayout>(android.R.id.content))
        // 状态栏字体的颜色
        mWindowInsetsControllerCompat?.isAppearanceLightStatusBars = bar
        // 导航栏字体颜色
        mWindowInsetsControllerCompat?.isAppearanceLightNavigationBars = bar
    }

    open fun setWindowInsetsListener(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    open fun setNavigationBarColorColor(@ColorInt int: Int) {
        window?.navigationBarColor = int
    }
}