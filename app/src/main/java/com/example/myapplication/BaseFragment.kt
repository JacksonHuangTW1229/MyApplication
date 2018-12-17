package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

open class BaseFragment: Fragment()  {

    fun handleBackPressed(fm: FragmentManager): Boolean {
        if (fm.getFragments() != null) {
            for (frag in fm.getFragments()) {
                if (frag != null && frag.isVisible() && frag is BaseFragment) {
                    if (frag.onBackPressed()) {
                        return true
                    }
                }
            }
        }
        return false
    }

    protected fun onBackPressed(): Boolean {
        val fm = childFragmentManager
        if (handleBackPressed(fm)) {
            return true
        } else if (userVisibleHint && fm.backStackEntryCount > 0) {
            fm.popBackStack()
            return true
        }
        return false
    }
}