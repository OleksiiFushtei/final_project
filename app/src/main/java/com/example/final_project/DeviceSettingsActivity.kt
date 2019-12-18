package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.adapters.DeviceSettingsFragmentsAdapter
import kotlinx.android.synthetic.main.activity_device_settings.*

class DeviceSettingsActivity :
    AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_device_settings
        )
        val title =
            intent.getStringExtra(
                "deviceName"
            )
        if (title != null) {
            toolbar.title =
                title
        }
        setSupportActionBar(
            toolbar
        )
        val fragmentAdapter =
            DeviceSettingsFragmentsAdapter(
                supportFragmentManager,
                intent.getIntExtra(
                    "controllerId",
                    0
                ),
                intent.getIntExtra(
                    "deviceId",
                    0
                ),
                intent.getBooleanExtra(
                    "isAdmin",
                    false
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
