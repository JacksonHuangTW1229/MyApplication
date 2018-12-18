package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
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
        fragment_button1_2.setOnClickListener {
            startChildFragment1_2()
//            Navigation.findNavController(it).navigate(R.id.action_myFragment1_to_myFragment1_2)
        }
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

    fun startChildFragment1_2() {
        Log.d(" testtt" , " startChildFragment1_2")
//        childFragmentManager
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        val fragment1_2 = MyFragment1_2()
        transaction.replace(R.id.fragment_child_container, fragment1_2, "fragment1_2")
            .addToBackStack("fragment1")
            .commitAllowingStateLoss()

        Log.d(" testtt" , " startChildFragment1_2 finish")
    }

}