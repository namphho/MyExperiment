package com.example.myexperiment.experiments.firebase.screens.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myexperiment.R
import com.example.myexperiment.app.MyExperimentsApp
import com.example.myexperiment.common.LoadingDialog
import com.example.myexperiment.common.closeKeyboard
import com.example.myexperiment.databinding.ActivityFirebaseRegisterBinding
import com.example.myexperiment.experiments.firebase.screens.AuthViewModelFactory

class FirebaseRegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirebaseRegisterBinding
    lateinit var viewModel: FirebaseRegisterViewModel
    lateinit var loadingDialog: LoadingDialog

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_firebase_register)
        viewModel = ViewModelProvider(this, AuthViewModelFactory((application as MyExperimentsApp).authRepository)).get(
            FirebaseRegisterViewModel::class.java
        )
        binding.btnSignUp.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val pass = binding.edtPassword.text.toString().trim()

            closeKeyboard(this)
            resetUI()
            viewModel.signUp(email, pass)
        }

        viewModel.isSignUpSuccess.observe(this, Observer { isSignUpSuccess ->
            val msg = if (isSignUpSuccess) "Register Successfully" else "Register failed"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            when (isLoading) {
                true -> loadingDialog.showDialog()
                false -> loadingDialog.hideDialog()
            }
        })

        //..initialize our custom loading dialog here with passing this activity context
        loadingDialog = LoadingDialog(this)
    }

    private fun resetUI() {
        binding.edtEmail.text.clear()
        binding.edtPassword.text.clear()
    }
}