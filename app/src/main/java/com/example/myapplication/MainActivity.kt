package com.example.myapplication

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_nav.activity_home_tabs_bottom

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {


    var mNavController : NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_nav)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        mNavController = host.navController

        mNavController?.addOnDestinationChangedListener { navController, destination, arguments ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }

//            Toast.makeText(this@MainActivity, "Navigated to $dest",
//                Toast.LENGTH_SHORT).show()
            Log.d("testtt", "Navigated to $dest")

//            toolbar.title = destination.id.toString()
        }

//        NavigationUI.setupWithNavController(bottom_nav, mNavController!!)
//            Navigation.findNavController(this, R.id.nav_host_fragment))

        setupTabLayout()
    }

    fun setupTabLayout() {
        for (i in 0 until 3) {
            val tab = activity_home_tabs_bottom.newTab()
            val tabView = TextView(this)
            tabView.text = "test$i"
            tabView.gravity = Gravity.CENTER_HORIZONTAL
            tabView.setTextColor(resources.getColor(android.R.color.black))

            tab.setCustomView(tabView)
            activity_home_tabs_bottom.addTab(tab)
        }
        activity_home_tabs_bottom.addOnTabSelectedListener(this)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
//        Log.d("testtt", "onTabReselected to $tab")
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
//        Log.d("testtt", "onTabUnselected to $tab")
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        val position = tab.position

        Log.d("testtt", "onTabSelected to $position")

        when (position) {
            0 -> mNavController?.navigate(R.id.myFragment1)
            1 -> mNavController?.navigate(R.id.myFragment2)
            2 -> mNavController?.navigate(R.id.myFragment3)
        }
    }

//    fun createTaskStackIntent() {
//        val detailsIntent = Intent(this, MainActivity::class.java)
//
//        val pendingIntent: PendingIntent? = TaskStackBuilder.create(this)
//            // add all of DetailsActivity's parents to the stack,
//            // followed by DetailsActivity itself
//            .addNextIntentWithParentStack(detailsIntent)
//            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val builder = NotificationCompat.Builder(this)
//            .setContentIntent(pendingIntent)
//    }

}
