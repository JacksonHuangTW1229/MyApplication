package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_layout2.fragment_button2_2


class MyFragment2: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //set the layout you want to display in First Fragment
        return inflater.inflate(
            R.layout.fragment_layout2,
            container,
            false
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(" testtt" , " onViewCreated " + this)
        fragment_button2_2.setOnClickListener { startFragment2_2() }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(" testtt" , " onDestroy " + this)
    }

//    fun startFragment2_2() {
//        Log.d(" testtt" , " startFragment2_2")
//        val fm = activity?.supportFragmentManager
//        val transaction = fm?.beginTransaction()
//        val fragment2_2 = MyFragment2_2()
//        transaction?.replace(R.id.fragment_container, fragment2_2, "fragment2_2")
//            ?.addToBackStack("fragment2")
//            ?.commitAllowingStateLoss()
//    }

    fun startFragment2_2() {
        Log.d(" testtt" , " startFragment2_2")
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        val fragment2_2 = MyFragment2_2()
        transaction.replace(R.id.fragment_child_container, fragment2_2, "fragment2_2")
            .addToBackStack("fragment2")
            .commitAllowingStateLoss()
    }
}