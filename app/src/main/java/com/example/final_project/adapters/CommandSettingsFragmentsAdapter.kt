package com.example.final_project.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.final_project.fragments.CommandConfigFragment
import com.example.final_project.fragments.CommandSettingsFragment

class CommandSettingsFragmentsAdapter(
    fm: FragmentManager,
    private val scriptId: Int,
    private val commandId: Int
) : FragmentPagerAdapter(
    fm
) {
    override fun getItem(
        position: Int
    ): Fragment {
        return when (position) {
            0 -> CommandSettingsFragment.newInstance(
                scriptId,
                commandId
            )
            else -> CommandConfigFragment.newInstance(
                scriptId,
                commandId
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
            else -> "Configurations"
        }
    }
}