package com.example.myexperiment.experiments.firebase.screens.add

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
import com.example.myexperiment.databinding.ActivityAddUserBinding
import com.example.myexperiment.experiments.firebase.screens.FireStoreViewModelFactory

class AddUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddUserBinding
    lateinit var viewModel: AddUserViewModel
    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user)
        //..initialize our custom loading dialog here with passing this activity context
        loadingDialog = LoadingDialog(this)


        viewModel =
            ViewModelProvider(this, FireStoreViewModelFactory((application as MyExperimentsApp).fireStoreRepo)).get(
                AddUserViewModel::class.java
            )

        viewModel.isLoading.observe(this, Observer { it ->
            when (it) {
                true -> loadingDialog.showDialog()
                false -> loadingDialog.hideDialog()
            }
        })

        viewModel.isAddSuccess.observe(this, Observer {
            val msg = if (it) "add Successfully" else "add failed"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })

        binding.btnAddUser.setOnClickListener {
            val email = binding.edtUserEmail.text.toString().trim()
            val fullName = binding.edtFullName.text.toString().trim()
            val clazzYear = binding.edtKhoa.text.toString().trim()
            closeKeyboard(this)
            resetInput()
            viewModel.addUser(email, fullName, clazzYear)
        }
    }

    private fun resetInput(){
        binding.edtFullName.setText("")
        binding.edtUserEmail.setText("")
        binding.edtKhoa.setText("")
    }
}