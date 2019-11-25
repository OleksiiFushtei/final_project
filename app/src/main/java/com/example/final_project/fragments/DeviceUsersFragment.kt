package com.example.final_project.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.final_project.R

/**
 * A simple [Fragment] subclass.
 */
class DeviceUsersFragment :
    Fragment() {

    companion object {
        private const val CONTROLLERID =
            "controllerId"
        private const val DEVICEID =
            "deviceId"

        fun newInstance(
            caughtControllerId: Int,
            caughtDeviceId: Int
        ): DeviceUsersFragment {
            val args =
                Bundle()
            args.putSerializable(
                CONTROLLERID,
                caughtControllerId
            )
            args.putSerializable(
                DEVICEID,
                caughtDeviceId
            )
            val fragment =
                DeviceUsersFragment()
            fragment.arguments =
                args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_device_users,
            container,
            false
        )
    }


}
