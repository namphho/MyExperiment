package com.example.myexperiment.experiments.permission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.myexperiment.R
import com.example.myexperiment.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {
    companion object {
        private val TAG = PermissionActivity::class.java.simpleName
    }
    lateinit var activityPermissionBinding: ActivityPermissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPermissionBinding = DataBindingUtil.setContentView(this, R.layout.activity_permission)
        activityPermissionBinding.btnRequestCameraPermission.setOnClickListener {
            runRequestPermission()
        }

        activityPermissionBinding.btnRequestMultiPermission.setOnClickListener {
            requestMultiplePermission()
        }
    }



    private fun runRequestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED -> {

                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    val dialog = AlertDialog.Builder(this)
                    dialog.apply {
                        title = "Camera Permission"
                        val rationaleMessage = "You need to granted this permission for using this future"
                        setMessage(rationaleMessage)
                        setPositiveButton("Accept") { dialog, _ ->
                            dialog.dismiss()
                            requestPermissionLauncher.launch(
                                Manifest.permission.CAMERA)
                        }
                        setNegativeButton("Cancel") {dialog, _ ->
                            dialog.dismiss()
                            Toast.makeText(this@PermissionActivity, "permission is deny", Toast.LENGTH_SHORT).show()
                        }
                        show()
                    }
                } else {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissionLauncher.launch(
                        Manifest.permission.CAMERA)
                }
            }
            else -> {
                Toast.makeText(this, "Run this function", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "permission is accepted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "permission is deny", Toast.LENGTH_SHORT).show();
            }
        }

    private fun requestMultiplePermission(){
        val hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        val hasWriteStorage = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val hasReadStorage = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        if (hasCameraPermission && hasWriteStorage && hasReadStorage) {
            Toast.makeText(this, "Run this function", Toast.LENGTH_SHORT).show()
        } else {
            multiplePermissions.launch(arrayOf(
                Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE))
        }
    }

    private val multiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        map ->
        map.forEach { entry -> Log.e(TAG, "${entry.key} -- ${entry.value}") }
    }
}