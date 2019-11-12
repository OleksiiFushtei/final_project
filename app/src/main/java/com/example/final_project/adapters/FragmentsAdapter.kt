package com.example.final_project.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.final_project.fragments.DevicesFragment
import com.example.final_project.fragments.ScriptsFragment
import com.example.final_project.fragments.SensorsFragment
import com.example.final_project.fragments.UsersFragment

class FragmentsAdapter (fm: FragmentManager, private val controllerId: Int) : FragmentPagerAdapter(fm) {
    override fun getItem(
        position: Int
    ): Fragment {
        return when (position) {
            0 -> SensorsFragment.newInstance(controllerId)
            1 -> DevicesFragment()
            2 -> ScriptsFragment()
            else -> UsersFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(
        position: Int
    ): CharSequence? {
        return when (position) {
            0 -> "Sensors"
            1 -> "Devices"
            2 -> "Scripts"
            else -> "Users"
        }
    }
}