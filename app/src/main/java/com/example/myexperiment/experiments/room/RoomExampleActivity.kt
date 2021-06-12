package com.example.myexperiment.experiments.room

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myexperiment.R
import com.example.myexperiment.app.MyExperimentsApp
import com.example.myexperiment.databinding.ActivityRoomExampleBinding
import com.example.myexperiment.experiments.room.db.Word

class RoomExampleActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityRoomExampleBinding
    private val newWordActivityRequestCode = 1
    private lateinit var wordViewModel: RoomExampleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_room_example)
        wordViewModel = ViewModelProvider(this, WordViewModelFactory((application as MyExperimentsApp).repository)).get(
            RoomExampleViewModel::class.java
        )


        val adapter = WordListAdapter()
        dataBinding.recyclerview.adapter = adapter

        wordViewModel.words.observe(this,  { data ->
            adapter.submitList(data)
        })

        dataBinding.fab.setOnClickListener {
            val intent = Intent(this@RoomExampleActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onStart() {
        super.onStart()
        wordViewModel.getAllWords()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
                val word = Word(0,reply)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}