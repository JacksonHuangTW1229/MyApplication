package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

open class BaseFragment: Fragment()  {


    fun handleBackPressed(): Boolean {
        val fm = this.requireFragmentManager()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(" testtt" , " base onViewCreated " + this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(" testtt" , " base onDestroy " + this)
    }
}