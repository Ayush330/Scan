package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.generate.*;
import java.io.File
import java.io.FileOutputStream

class generate : Fragment(R.layout.generate)
{
    private val args : generateArgs by navArgs();

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)


        // generating the qr code
        var details = args.DETAILS;
        var display = getActivity()?.getDisplay();
        var point = Point();
        display?.getSize(point);
        var height = point.x;
        var width = point.y;
        var dimen = width;
        dimen = dimen * 3 / 4;
        var encoder = QRGEncoder(details, null, QRGContents.Type.TEXT, dimen);
        var bitmap = encoder.encodeAsBitmap();
        // the bitmap is set inside our image
        // view using .setimagebitmap method.
        imageView.setImageBitmap(bitmap);


        //scanning the qr code
        imageView2.setOnClickListener {

            if (bitmap == null) {
                val text = "CREATE A QR CODE IMAGE TO SAVE.!"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(getActivity(), text, duration)
                toast.show()
            } else {
                var filepath: File? = getActivity()?.getExternalFilesDir(null);
                var dir: File? = File(filepath?.getAbsolutePath() + "/QrCode/")
                var x = dir?.mkdirs()
                Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", x.toString())
                var file: File? = File(dir, System.currentTimeMillis().toString() + ".jpg");
                var outputStream = FileOutputStream(file);
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close()
                val text = "IMAGE SAVED AT " + (dir?.toString())
                val duration = Toast.LENGTH_LONG

                val toast = Toast.makeText(getActivity(), text, duration)
                toast.show()
            }
        }

    }
}