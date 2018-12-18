package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MyFragment2_2: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //set the layout you want to display in First Fragment
        return inflater.inflate(
            R.layout.fragment_layout2_2,
            container,
            false
        )

    }
}