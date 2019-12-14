package com.example.final_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.DeviceSettingsActivity
import com.example.final_project.R
import com.example.final_project.adapters.DeviceAdapter
import com.example.final_project.api.helpers.DevicesListHelper
import com.example.final_project.api.interfaces.DevicesListInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.DeviceModel
import com.example.final_project.models.ErrorModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_device.*

class DevicesFragment :
    Fragment(),
    DevicesListInterface.DevicesListListener {

    companion object {
        private const val ID =
            "id"
        private const val ISADMIN =
            "isAdmin"

        fun newInstance(
            caughtId: Int,
            caughtIsAdmin: Boolean
        ): DevicesFragment {
            val args =
                Bundle()
            args.putSerializable(
                ID,
                caughtId
            )
            args.putSerializable(
                ISADMIN,
                caughtIsAdmin
            )
            val fragment =
                DevicesFragment()
            fragment.arguments =
                args
            return fragment
        }
    }

    override fun onGetDevicesListResponseSuccess(
        list: ArrayList<DeviceModel>
    ) {
        val bundle =
            this.arguments
        val controllerId =
            bundle?.getInt(
                "id",
                0
            )
        val isAdmin =
            bundle?.getBoolean(
                "isAdmin",
                false
            )
        progressBar.visibility =
            View.GONE
        listOfDevices.layoutManager =
            LinearLayoutManager(
                context
            )
        listOfDevices.adapter =
            DeviceAdapter(
                list,
                context,
                controllerId = controllerId,
                isAdmin = isAdmin
            )
        devicesList.addAll(
            list
        )
        when {
            list.isEmpty() -> {
                listOfDevices.visibility =
                    View.GONE
                emptyList.visibility =
                    View.VISIBLE
            }
            else -> {
                listOfDevices.visibility =
                    View.VISIBLE
                emptyList.visibility =
                    View.GONE
            }
        }
    }

    override fun onGetDevicesListResponseFailure(
        errorModel: ErrorModel
    ) {
        progressBar.visibility =
            View.GONE
        var errorMessage =
            errorModel.message
        when {
            errorMessage.isEmpty() -> errorMessage =
                "Response failure. Try again"
        }
        Snackbar.make(
            root_layout,
            errorMessage,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetDevicesListCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetDevicesListFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    private val devicesList: ArrayList<DeviceModel> =
        ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =
            inflater.inflate(
                R.layout.fragment_device,
                container,
                false
            )
        val bundle =
            this.arguments
        val controllerId =
            bundle?.getInt(
                "id",
                0
            )
        val progressBar =
            view.findViewById<ProgressBar>(
                R.id.progressBar
            )
        progressBar?.visibility =
            View.VISIBLE
        val addButton =
            view.findViewById<FloatingActionButton>(
                R.id.addDeviceButton
            )
        addButton?.setOnClickListener {
            val addDevice =
                Intent(
                    this.activity,
                    DeviceSettingsActivity::class.java
                )
            addDevice.putExtra(
                "controllerId",
                controllerId
            )
            addDevice.putExtra(
                "deviceId",
                0
            )
            startActivity(
                addDevice
            )
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        val bundle =
            this.arguments
        val controllerId =
            bundle?.getInt(
                "id",
                0
            )
        val app =
            context?.applicationContext as MainApplication
        progressBar?.visibility =
            View.VISIBLE
        val devicesListHelper =
            DevicesListHelper(
                app.getApi()
            )
        if (controllerId != null) {
            devicesListHelper.getListOfDevices(
                controllerId,
                this
            )
        }
    }
}
