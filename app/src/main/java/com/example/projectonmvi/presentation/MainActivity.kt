package com.example.projectonmvi.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.projectonmvi.R

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val dataEditView = findViewById<EditText>(R.id.dataEditText)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val receiveButton = findViewById<Button>(R.id.receiveButton)

        Log.e("AAA", "Activity created")

        vm = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]

        vm.stateLive.observe(this) { state ->
            dataTextView.text = "${state.result} ${state.firstName} ${state.lastName}"
        }

        sendButton.setOnClickListener {
            val text = dataEditView.text.toString()
            vm.send(SaveEvent(text))
        }

        receiveButton.setOnClickListener {
            vm.send(LoadEvent())
        }
    }
}