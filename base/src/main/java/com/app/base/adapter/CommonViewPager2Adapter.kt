package com.app.base.adapter

import androidx.fragment.app.*
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * ViewPager2通用Adapter
 */
class CommonViewPager2Adapter : FragmentStateAdapter {

    private var fragmentList: List<Fragment>? = null

    /**
     * [RecyclerView.Recycler.DEFAULT_CACHE_SIZE]
     */
    private var itemViewCacheSize = 0


    constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)

    constructor(fragment: Fragment) : super(fragment)

    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(fragmentManager, lifecycle)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (itemViewCacheSize > 0) {
            // 设置recyclerView itemCacheSize
            recyclerView.setItemViewCacheSize(itemViewCacheSize)
        }
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList?.getOrNull(position) ?: Fragment()
    }

    override fun getItemCount(): Int {
        return fragmentList?.size ?: 0
    }


    fun setFragmentList(fragmentList: List<Fragment>) {
        this.fragmentList = fragmentList
    }

    fun setItemViewCacheSize(size: Int) {
        itemViewCacheSize = size
    }
}