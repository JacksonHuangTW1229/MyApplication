package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_layout1.fragment_button1_2


class MyFragment1: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //set the layout you want to display in First Fragment
        return inflater.inflate(
            R.layout.fragment_layout1,
            container,
            false
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(" testtt" , " onViewCreated " + this)
        fragment_button1_2.setOnClickListener { startFragment1_2() }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(" testtt" , " onDestroy " + this)
    }

//    fun startFragment1_2() {
//        Log.d(" testtt" , " startFragment1_2")
//        val fm = activity?.supportFragmentManager
//        val transaction = fm?.beginTransaction()
//        val fragment1_2 = MyFragment1_2()
//        transaction?.replace(R.id.fragment_container, fragment1_2, "fragment1_2")
//            ?.addToBackStack("fragment1")
//            ?.commitAllowingStateLoss()
//    }

    fun startFragment1_2() {
        Log.d(" testtt" , " startFragment1_2")
//        childFragmentManager
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        val fragment1_2 = MyFragment1_2()
        transaction.replace(R.id.fragment_child_container, fragment1_2, "fragment1_2")
            .addToBackStack("fragment1")
            .commitAllowingStateLoss()
    }
}