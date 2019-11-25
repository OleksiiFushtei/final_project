package com.example.final_project.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.final_project.fragments.DeviceSettingsFragment
import com.example.final_project.fragments.DeviceUsersFragment

class DeviceSettingsFragmentsAdapter(
    fm: FragmentManager,
    private val controllerId: Int,
    private val deviceId: Int
) : FragmentPagerAdapter(
    fm
) {
    override fun getItem(
        position: Int
    ): Fragment {
        return when (position) {
            0 -> DeviceSettingsFragment.newInstance(
                controllerId, deviceId
            )
            else -> DeviceUsersFragment.newInstance(
                controllerId, deviceId
            )
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(
        position: Int
    ): CharSequence? {
        return when (position) {
            0 -> "Settings"
            else -> "Users"
        }
    }
}