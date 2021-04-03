package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.details.*

class Details :  Fragment(R.layout.details)
{
    private var Name:String? = null;
    private var Phone1:String? = null;
    private var Phone2:String? = null;
    private var Address:String? = null;
    private var Group:String? = null;
    private var Allergy:String? = null;
    private var Details:String? = null;
    private var Medication:String? = null;

    //private var General:String? = null;


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)





        generate.setOnClickListener{

            var color  = SpannableString("");
            var option = ForegroundColorSpan(Color.GREEN);
            Name = "NAME:  "+name.text.toString();
            color = SpannableString(Name);
            color.setSpan(option,0,4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            //Log.i("NameEEEEEEEEEEEEEEEEEEEEEEEEEEEEE",name.text.toString())
            Phone1 = "PHONE(1):  +91"+phone1.text.toString();
            color = SpannableString(Phone1);
            color.setSpan(option,0,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            Phone2 = "PHONE(2):  +91"+phone2.text.toString();
            color = SpannableString(Phone2);
            color.setSpan(option,0,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            Address = "ADDRESS:  "+address.text.toString();
            color = SpannableString(Address);
            color.setSpan(option,0,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            Group = "BLOOD GROUP:  "+group.text.toString();
            color = SpannableString(Group);
            color.setSpan(option,0,11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            Allergy = "ALLERGY:  "+allergy.text.toString();
            color = SpannableString(Allergy);
            color.setSpan(option,0,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            Medication = "MEDICATION:  "+medication.text.toString();
            color = SpannableString("Medication");
            color.setSpan(option,0,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            Details = "DETAILS:  "+details.text.toString();
            color = SpannableString(Details);
            color.setSpan(option,0,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            var General = Name+"\n"+Phone1+"\n"+Phone2+"\n"+Address+"\n"+Group+"\n"+Allergy+"\n"+Medication+"\n"+Details;

            val action = DetailsDirections.actionDetails2ToGenerate22(General);
            findNavController().navigate(action);
        }

    }
}