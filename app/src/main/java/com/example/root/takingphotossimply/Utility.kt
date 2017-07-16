package com.example.root.takingphotossimply

import android.Manifest.permission
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.support.v4.app.ActivityCompat
import android.content.DialogInterface
import android.os.Build
import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat


/**
 * Created by root on 7/16/17.
 */
object Utility {
    val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun checkPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    val alertBuilder = AlertDialog.Builder(context)
                    alertBuilder.setCancelable(true)
                    alertBuilder.setTitle("Permission necessary")
                    alertBuilder.setMessage("External storage permission is necessary")
                    alertBuilder.setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener { dialog, which -> ActivityCompat.requestPermissions(context as Activity, arrayOf<String>(android.Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) })
                    val alert = alertBuilder.create()
                    alert.show()

                } else {
                    ActivityCompat.requestPermissions(context as Activity, arrayOf<String>(android.Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }
}