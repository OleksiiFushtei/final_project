package com.example.final_project.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.final_project.fragments.ScriptCommandsFragment
import com.example.final_project.fragments.ScriptSettingsFragment

class ScriptSettingsFragmentsAdapter(
    fm: FragmentManager,
    private val controllerId: Int,
    private val scriptId: Int
) : FragmentPagerAdapter(
    fm
) {
    override fun getItem(
        position: Int
    ): Fragment {
        return when (position) {
            0 -> ScriptSettingsFragment.newInstance(
                controllerId,
                scriptId
            )
            else -> ScriptCommandsFragment.newInstance(
                scriptId
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
            else -> "Commands"
        }
    }
}