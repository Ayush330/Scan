package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.welcome.*

class welcome : Fragment(R.layout.welcome)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState);
        generate.setOnClickListener{
            val action = welcomeDirections.actionWelcomeToDetails2();
            findNavController().navigate(action);
        }

       help.setOnClickListener{
           requestContactsPermission();
    }

}

    private fun requestContactsPermission() {
        Dexter.withActivity(requireActivity())
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    val action = welcomeDirections.actionWelcomeToBarcode()
                    findNavController().navigate(action);
                }

                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?)
                {
                    token?.continuePermissionRequest()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Log.i("Denied","Permission Denied")
                    val newFragment = dialog()
                    newFragment.show((activity as FragmentActivity).supportFragmentManager, "missiles")
                }
            } )
            .check()
    }

}