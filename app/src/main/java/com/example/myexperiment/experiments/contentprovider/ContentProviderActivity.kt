package com.example.myexperiment.experiments.contentprovider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myexperiment.R
import com.example.myexperiment.databinding.ActivityContentProviderBinding

class ContentProviderActivity : AppCompatActivity() {
    lateinit var providerModel: ContentProviderModel
    lateinit var contentProviderBinding: ActivityContentProviderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentProviderBinding = DataBindingUtil.setContentView(this, R.layout.activity_content_provider)
        providerModel= ViewModelProvider(this).get(ContentProviderModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        providerModel.getData(contentResolver, "Anh")
    }
}