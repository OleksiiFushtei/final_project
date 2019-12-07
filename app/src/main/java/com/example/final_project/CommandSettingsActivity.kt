package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.adapters.CommandSettingsFragmentsAdapter
import kotlinx.android.synthetic.main.activity_command_settings.*

class CommandSettingsActivity :
    AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_command_settings
        )
        val title =
            intent.getStringExtra(
                "commandName"
            )
        if (title != null) {
            toolbar.title =
                title
        }
        setSupportActionBar(
            toolbar
        )
        val fragmentsAdapter =
            CommandSettingsFragmentsAdapter(
                supportFragmentManager,
                intent.getIntExtra(
                    "scriptId",
                    0
                ),
                intent.getIntExtra(
                    "commandId",
                    0
                )
            )
        viewPager.offscreenPageLimit =
            2
        viewPager.adapter =
            fragmentsAdapter
        tabLayout.setupWithViewPager(
            viewPager
        )
    }
}
