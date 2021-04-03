package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.View
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import kotlinx.android.synthetic.main.barcode.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import androidx.navigation.fragment.findNavController
import kotlin.system.exitProcess

class Barcode : Fragment(R.layout.barcode)
{
    private lateinit var cameraSelector: CameraSelector;
    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>

    override fun onStart()
    {
        super.onStart()

        MyCameraXApplication();
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireActivity())

        cameraProviderFuture.addListener(Runnable {
            try{
                val cameraProvider = cameraProviderFuture.get()
                bindPreview(cameraProvider)}
            catch ( e: InterruptedException)
            {
                var text = "Please provide Camera access to this app.";
                var duration = Toast.LENGTH_LONG;
                var toast = Toast.makeText(activity,text,duration);
                toast.show();
            }
        }, ContextCompat.getMainExecutor(activity))

    }

    @SuppressLint("UnsafeExperimentalUsageError")
    fun bindPreview(cameraProvider: ProcessCameraProvider)
    {
        var element : String = "";
        var preview : Preview = Preview.Builder()
            .build()

        cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        preview.setSurfaceProvider(previewView.surfaceProvider)

        var camera = cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview)

        //Image Analysis

        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(1280, 720))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()

        cameraProvider.bindToLifecycle(
            this as LifecycleOwner,
            cameraSelector,
            imageAnalysis,
            preview
        )

        val executor: Executor;
        imageAnalysis.setAnalyzer(Executors.newFixedThreadPool(1), ImageAnalysis.Analyzer { imagee ->
            val rotationDegrees = imagee.imageInfo.rotationDegrees;
            val options = BarcodeScannerOptions.Builder()
                .setBarcodeFormats(
                    Barcode.FORMAT_QR_CODE
                )
                .build()

            var boolean = false;
            val mediaImage = imagee.image;
            val image = InputImage.fromMediaImage(mediaImage, imagee.imageInfo.rotationDegrees)
            if (mediaImage != null) {
                val image = InputImage.fromMediaImage(mediaImage, imagee.imageInfo.rotationDegrees)
                val scanner = BarcodeScanning.getClient(options)
                val result = scanner.process(image)
                    .addOnSuccessListener { barcodes ->
                        Log.i("Ayush", "Successful")
                        //t = 1;
                        val text = "Hello toast!"
                        val duration = Toast.LENGTH_SHORT

                        if (barcodes.size == 1) {
                            boolean = true;
                        }
                        for (barcode in barcodes) {
                            val bounds = barcode.boundingBox
                            val corners = barcode.cornerPoints

                            val rawValue = barcode.rawValue
                            //arraylist.add(rawValue);
                            Log.i(
                                "Ayush",
                                rawValue
                            )
                            element=rawValue;
                            //val text = "Hello toast!"
                            val duration = Toast.LENGTH_SHORT

                            val toast = Toast.makeText(getActivity(), rawValue, duration)
                            toast.show()
                            val valueType = barcode.valueType
                            // See API reference for complete list of supported types
                            when (valueType) {
                                Barcode.TYPE_WIFI -> {
                                    val ssid = barcode.wifi!!.ssid
                                    val password = barcode.wifi!!.password
                                    val type = barcode.wifi!!.encryptionType
                                }
                                Barcode.TYPE_URL -> {
                                    val title = barcode.url!!.title
                                    val url = barcode.url!!.url
                                }
                            }
                        }
                        imagee.close()

                        if (boolean)
                        {
                            imageAnalysis.clearAnalyzer()
                            val action = BarcodeDirections.actionBarcodeToData(element);

                            cameraProvider.unbindAll()
                            findNavController().navigate(action);
                            //stopping analysis
                            //exitProcess(0)
                            imageAnalysis.clearAnalyzer()

                        }



                    }
                    .addOnFailureListener {
                        Log.i("Ayush", "UnSuccessful")
                    }

            }
        })

    }

    override fun onPause() {
        super.onPause()
        Log.i("Ayush","OnPause called.")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Ayush","OnStop called.")
    }
}
