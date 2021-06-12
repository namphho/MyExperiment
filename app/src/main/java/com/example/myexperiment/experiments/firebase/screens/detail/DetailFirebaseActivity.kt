package com.example.myexperiment.experiments.firebase.screens.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myexperiment.R
import com.example.myexperiment.app.MyExperimentsApp
import com.example.myexperiment.databinding.ActivityDetailFirebaseBinding
import com.example.myexperiment.experiments.firebase.screens.FireStoreViewModelFactory
import com.example.myexperiment.experiments.firebase.screens.add.AddUserActivity

class DetailFirebaseActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailFirebaseBinding
    private lateinit var viewModel: DetailFirebaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_firebase)

        viewModel =
            ViewModelProvider(this, FireStoreViewModelFactory((application as MyExperimentsApp).fireStoreRepo)).get(
                DetailFirebaseViewModel::class.java
            )

        binding.fab.setOnClickListener {
            val i = Intent(this@DetailFirebaseActivity, AddUserActivity::class.java)
            startActivity(i)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadStudents()
    }
}