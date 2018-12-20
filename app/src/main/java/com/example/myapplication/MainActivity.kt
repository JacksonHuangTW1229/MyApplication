package com.example.myapplication

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.HomeTabManager.FragmentTag.TAG_CASCADE
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_nav.activity_home_tabs_bottom

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {


    var mNavController : NavController? = null
    lateinit var tabManager : HomeTabManager

    val list = ArrayList<BaseFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_nav)

        tabManager = HomeTabManager(this)
        setupTabLayout()
    }

    fun setupTabLayout() {

        val selectedTabPosition = tabManager.getTabPosition(TAG_CASCADE)

        for (i in 0 until tabManager.getCount()) {
            val tab = activity_home_tabs_bottom.newTab()
            val homeTabTag = tabManager.getHomeTabTag(i)

            val tabView = HomeTabView(this)
            tabView.setImageResource(tabManager.getTabIconId(homeTabTag))
            tabView.setText(tabManager.getTabTextId(homeTabTag))
            tab.setCustomView(tabView)
                .setContentDescription(getString(tabManager.getTabDescriptionId(homeTabTag)))
            activity_home_tabs_bottom.addTab(tab)
        }

        activity_home_tabs_bottom.addOnTabSelectedListener(this)

        activity_home_tabs_bottom.getTabAt(selectedTabPosition)!!.select()
        onBottomNavItemSelected(selectedTabPosition)
    }

    fun onBottomNavItemSelected(position: Int) {
        tabManager.selectTab(tabManager.getHomeTabTag(position), null)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        // do nothing
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        // do nothing
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        val position = tab.position
        Log.d("testtt", "onTabSelected to $position")
        onBottomNavItemSelected(tab.position)
    }


    override fun onBackPressed() {
        val fm = supportFragmentManager
        val backStackEntryCount = fm.backStackEntryCount
        Log.d("testtt", " backStackEntryCount = $backStackEntryCount")

        val fragment = tabManager.selectedFragment
        if (fragment is BaseFragment) {
            if (fragment.handleBackPressed()) {
                // already handle
                return
            }
        }
        val position = activity_home_tabs_bottom.selectedTabPosition
        if (tabManager.getChildDepth(position) > 0) {
            // try to pop up current tab
            tabManager.popUp(position);
            return
        } else if (position != tabManager.getTabPosition(TAG_CASCADE)) {
            // back to cascade tab
            val selectedTabPosition = tabManager.getTabPosition(TAG_CASCADE)
            activity_home_tabs_bottom.getTabAt(selectedTabPosition)!!.select()
            onBottomNavItemSelected(selectedTabPosition)
            return
        }

        super.onBackPressed()
    }

    public fun startChildFragment(fragmentTag: String) {
        val position = activity_home_tabs_bottom.selectedTabPosition
        tabManager.selectTab(tabManager.getHomeTabTag(position), fragmentTag)
    }
}
