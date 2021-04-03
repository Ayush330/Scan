package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.Point
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.measureTimeMillis


class MainActivity : AppCompatActivity()
{
    //var bitmap: Bitmap?=null;
    //lateinit var currentPhotoPath: String


    //generating the qr code
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*button.setOnClickListener {

            var details = edit.text.toString();
            var display = this.getDisplay();
            var point = Point();
            display?.getSize(point);
            var height = point.x;
            var width = point.y;
            var dimen = width;
            dimen = dimen * 3 / 4;
            var encoder = QRGEncoder(details, null, QRGContents.Type.TEXT, dimen);
            bitmap = encoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            image.setImageBitmap(bitmap);

            //save.setVisibility(VISIBLE);


*/
        }

/*
        //saving the generated qr code
        save.setOnClickListener {

          if(bitmap==null)
          {
              val text = "CREATE A QR CODE IMAGE TO SAVE.!"
              val duration = Toast.LENGTH_SHORT

              val toast = Toast.makeText(applicationContext, text, duration)
              toast.show()
          }
          else
            {
                var filepath:File? = this.getExternalFilesDir(null);
                var dir:File? =  File(filepath?.getAbsolutePath()+"/QrCode/")
                var x = dir?.mkdirs()
                Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",x.toString())
                var file:File?= File(dir,System.currentTimeMillis().toString()+".jpg");
                var outputStream = FileOutputStream(file);
                bitmap?.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                outputStream.flush();
                outputStream.close()
                val text = "IMAGE SAVED AT " + (dir?.toString())
                val duration = Toast.LENGTH_LONG

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }

        }

        }*/

}
