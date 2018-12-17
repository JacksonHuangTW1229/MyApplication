package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_nav.navigation

class MainActivity : AppCompatActivity() , HomeFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

        Log.d("testtt" , " uri = $uri")
    }

    private val baseFragments = arrayOfNulls<BaseFragment>(3)
//    private val mList = ArrayList<BaseFragment>()
//    private val mList = arrayOfNulls<BaseFragment>(3)

    private var mCurrentTab = 0
    private var selectedFragmentTag: String? = null

//    private val mOnNavigationItemSelectedListener =
//        BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navigation_home -> {
//                    mCurrentTab = 0
////                    val fm = supportFragmentManager
////                    if (fm != null && fm.backStackEntryCount > 0) {
////                        val back = fm.getBackStackEntryAt(0)
////                    }
////                    startFragment1()
////                    message.setText(R.string.title_home)
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.navigation_dashboard -> {
//                    mCurrentTab = 1
////                    val fm = supportFragmentManager
////                    if (fm != null && fm.backStackEntryCount > 0) {
////                        val back = fm.getBackStackEntryAt(0)
////                    }
////
////                    startFragment2()
////                    message.setText(R.string.title_dashboard)
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.navigation_notifications -> {
////                    message.setText(R.string.title_notifications)
//                    return@OnNavigationItemSelectedListener true
//                }
//            }
//            false
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


        setContentView(R.layout.activity_nav)

        NavigationUI.setupWithNavController(navigation,
            Navigation.findNavController(this, R.id.nav_host_fragment))

//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, HomeFragment.newInstance())
//                .commitNow();
//        }

//        setSupportActionBar(toolbar)
//        setTitle("testtt ")

//        fragment_button.setOnClickListener {
//            startFragment1()
//        }
    }

    fun startFragment1() {
        Log.d(" testtt" , " startFragment1")
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()

        val lastSelectedFragment = fm.findFragmentByTag(selectedFragmentTag)
        if (lastSelectedFragment != null) {
            transaction.hide(lastSelectedFragment)
        }

        var fragment1 = baseFragments[0]
        if (fragment1 == null) {
            fragment1 = MyFragment1()
            baseFragments[0] = fragment1
            transaction.add(R.id.fragment_container, fragment1, "fragment1")
        }

        transaction.show(fragment1)
        transaction.commitAllowingStateLoss()

        selectedFragmentTag = "fragment1"
    }

    fun startFragment2() {
        Log.d(" testtt" , " startFragment2")
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()

        val lastSelectedFragment = fm.findFragmentByTag(selectedFragmentTag)
        if (lastSelectedFragment != null) {
            transaction.hide(lastSelectedFragment)
        }

        var fragment2 = baseFragments[1]
        if (fragment2 == null) {
            fragment2 = MyFragment2()
            baseFragments[1] = fragment2
            transaction.add(R.id.fragment_container, fragment2, "fragment2")
        }
        transaction.show(fragment2)
        transaction.commitAllowingStateLoss()

        selectedFragmentTag = "fragment2"
    }

    override fun onBackPressed() {

        // pop child fragment stack
//        if (baseFragments[mCurrentTab] != null) {
//            val baseFragment = baseFragments[mCurrentTab]
//            if(baseFragment!!.handleBackPressed()) {
//                return
//            }
//        }
//
//        super.onBackPressed()

        val baseFragment = baseFragments[mCurrentTab]
//        if( baseFragment == null || !baseFragment.handleBackPressed(supportFragmentManager)) {
//            super.onBackPressed()
//        }
        if( baseFragment != null && baseFragment.handleBackPressed(supportFragmentManager)) {
            return
        }

        // back to tab 0
//        if (mCurrentTab != 0) {
//            navigation.selectedItemId = R.id.navigation_home
//            return
//        }

        super.onBackPressed()
//        if((baseFragment!!.handleBackPressed(getSupportFragmentManager()))){
//            super.onBackPressed()
//        }
    }

}
