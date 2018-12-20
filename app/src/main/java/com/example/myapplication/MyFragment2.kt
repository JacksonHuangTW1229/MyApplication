package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
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
        fragment_button2_2.setOnClickListener {
            startActivityFragment2_2()
//            startFragment2_2()
//            startChildFragment2_2()
//            Navigation.findNavController(it).navigate(R.id.action_myFragment2_to_myFragment2_2)
        }
    }

    fun startActivityFragment2_2() {
        val act = activity
        if (act is MainActivity) {
            act.startChildFragment(HomeTabManager.FragmentTag.MyFragment2_2)
        }
    }

    fun startFragment2_2() {
        Log.d(" testtt" , " startFragment2_2")
        val fm = activity?.supportFragmentManager
        val transaction = fm?.beginTransaction()
        val fragment2_2 = MyFragment2_2()
        transaction?.replace(R.id.fragment_container, fragment2_2, "fragment2_2")
            ?.addToBackStack("fragment2")
            ?.commitAllowingStateLoss()
    }

    fun startChildFragment2_2() {
        Log.d(" testtt" , " startChildFragment2_2")
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        val fragment2_2 = MyFragment2_2()
        transaction.replace(R.id.fragment_child_container, fragment2_2, "fragment2_2")
            .addToBackStack("fragment2")
            .commitAllowingStateLoss()
    }
}