package com.example.myexperiment.experiments.firebase.screens.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myexperiment.R
import com.example.myexperiment.app.MyExperimentsApp
import com.example.myexperiment.common.LoadingDialog
import com.example.myexperiment.databinding.ActivityFirebaseLoginBinding
import com.example.myexperiment.experiments.firebase.screens.AuthViewModelFactory
import com.example.myexperiment.experiments.firebase.screens.detail.DetailFirebaseActivity
import com.example.myexperiment.experiments.firebase.screens.register.FirebaseRegisterActivity

class FirebaseLoginAct : AppCompatActivity() {
    private lateinit var binding: ActivityFirebaseLoginBinding
    private lateinit var viewModel: FirebaseLoginViewModel
    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_firebase_login)
        viewModel = ViewModelProvider(this, AuthViewModelFactory((application as MyExperimentsApp).authRepository)).get(
            FirebaseLoginViewModel::class.java
        )

        binding.btnLogin.setOnClickListener {
            val email = binding.tvEmail.text.toString().trim()
            val pass = binding.tvPassword.text.toString().trim()
            viewModel.login(email, pass)
        }

        binding.btnGoToSignUp.setOnClickListener {
            val intent = Intent(this@FirebaseLoginAct, FirebaseRegisterActivity::class.java)
            startActivity(intent)
        }

        viewModel.isLoading.observe(this, Observer { it ->
            when (it) {
                true -> loadingDialog.showDialog()
                false -> loadingDialog.hideDialog()
            }
        })

        viewModel.isLoginSuccess.observe(this, Observer {
            val msg = if (it) "login Successfully" else "login failed"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })

        //..initialize our custom loading dialog here with passing this activity context
        loadingDialog = LoadingDialog(this)
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.isLogged()) {
            val intent = Intent(this@FirebaseLoginAct, DetailFirebaseActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}