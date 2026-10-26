package com.example.employeeonboardingmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(
            this,
            "App Started Successfully",
            Toast.LENGTH_LONG
        ).show()
    }
}


