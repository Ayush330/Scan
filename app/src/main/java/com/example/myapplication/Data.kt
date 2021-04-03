package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.data.*;

class Data : Fragment(R.layout.data)
{
    private val args : DataArgs by navArgs();
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState);
        var temp = args.inferred;
        text.text = temp;
    }
}