package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.adapters.ScriptSettingsFragmentsAdapter
import kotlinx.android.synthetic.main.activity_script_settings.*

class ScriptSettingsActivity :
    AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_script_settings
        )
        val title =
            intent.getStringExtra(
                "scriptName"
            )
        if (title != null) {
            toolbar.title =
                title
        }
        setSupportActionBar(
            toolbar
        )
        val fragmentAdapter =
            ScriptSettingsFragmentsAdapter(
                supportFragmentManager,
                intent.getIntExtra(
                    "controllerId",
                    0
                ),
                intent.getIntExtra(
                    "scriptId",
                    0
                )
            )
        viewPager.offscreenPageLimit =
            2
        viewPager.adapter =
            fragmentAdapter
        tabLayout.setupWithViewPager(
            viewPager
        )
    }
}
