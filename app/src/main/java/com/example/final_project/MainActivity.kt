package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity :
    AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        if (supportActionBar != null) supportActionBar?.hide()
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_main
        )
    }
}
