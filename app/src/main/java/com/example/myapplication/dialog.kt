package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.custom.*

class dialog : DialogFragment()
{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val view:View = inflater.inflate(R.layout.custom, container,false)
        return view
    }

}