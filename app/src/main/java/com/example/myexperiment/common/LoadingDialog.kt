package com.example.myexperiment.common

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.example.myexperiment.R


/**
 * Created by nampham on 6/2/21.
 */
class LoadingDialog(private val activity: Activity?) {
    var dialog: Dialog? = null


    fun showDialog() {
        dialog = Dialog(activity as Context)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //...set cancelable false so that it's never get hidden
        dialog!!.setCancelable(false)
        //...that's the layout i told you will inflate later
        dialog!!.setContentView(R.layout.custom_loading_layout)

        //...initialize the imageView form infalted layout
        val gifImageView: ImageView = dialog!!.findViewById(R.id.custom_loading_imageView)

        /*
        it was never easy to load gif into an ImageView before Glide or Others library
        and for doing this we need DrawableImageViewTarget to that ImageView
        */
        val imageViewTarget = DrawableImageViewTarget(gifImageView)

        //...now load that gif which we put inside the drawble folder here with the help of Glide
        Glide.with(activity!!)
            .load(R.drawable.loading)
            .placeholder(R.drawable.loading)
            .centerCrop()
            .into(imageViewTarget)

        //...finaly show it
        dialog!!.show()
    }

    //..also create a method which will hide the dialog when some work is done
    fun hideDialog() {
        dialog!!.dismiss()
    }
}