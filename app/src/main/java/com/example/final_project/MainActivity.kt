package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.adapters.FragmentsAdapter
import com.example.final_project.fragments.SensorsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_main
        )

        toolbar.title =
            intent.getStringExtra(
                "name"
            )
        setSupportActionBar(
            toolbar
        )

        val fragmentAdapter =
            FragmentsAdapter(
                supportFragmentManager,
                intent.getIntExtra("id", 0)
            )
        viewPager.adapter =
            fragmentAdapter

        tabLayout.setupWithViewPager(
            viewPager
        )
    }

}
