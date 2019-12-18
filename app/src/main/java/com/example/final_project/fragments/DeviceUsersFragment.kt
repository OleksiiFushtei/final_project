package com.example.final_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.DeviceUserGiveAccessActivity

import com.example.final_project.R
import com.example.final_project.adapters.DeviceUserAdapter
import com.example.final_project.api.helpers.DeviceAccessHelper
import com.example.final_project.api.interfaces.DeviceAccessInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.DeviceListItemModel
import com.example.final_project.models.ErrorModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_device_users.*

class DeviceUsersFragment :
    Fragment(),
    DeviceAccessInterface.GetUsersForDevicesListener {

    companion object {
        private const val CONTROLLERID =
            "controllerId"
        private const val DEVICEID =
            "deviceId"
        private const val ISADMIN =
            "isAdmin"

        fun newInstance(
            caughtControllerId: Int,
            caughtDeviceId: Int,
            caughtIsAdmin: Boolean
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
            args.putSerializable(
                ISADMIN,
                caughtIsAdmin
            )
            val fragment =
                DeviceUsersFragment()
            fragment.arguments =
                args
            return fragment
        }
    }

    override fun onGetUsersFroDevicesResponseSuccess(
        list: ArrayList<DeviceListItemModel>
    ) {
        progressBar.visibility =
            View.GONE
        val llm =
            LinearLayoutManager(
                this.context
            )
        llm.orientation =
            LinearLayoutManager.VERTICAL
        listOfDeviceUsers.layoutManager =
            llm
        val deviceUserAdapter =
            DeviceUserAdapter(
                list,
                context
            )
        listOfDeviceUsers.adapter =
            deviceUserAdapter
        when {
            list.isEmpty() -> {
                listOfDeviceUsers.visibility =
                    View.GONE
                emptyList.visibility =
                    View.VISIBLE
            }
            else -> {
                listOfDeviceUsers.visibility =
                    View.VISIBLE
                emptyList.visibility =
                    View.GONE
            }
        }
    }

    override fun onGetUsersForDevicesResponseFailure(
        errorModel: ErrorModel?
    ) {
        progressBar.visibility =
            View.GONE
        val errorMessage =
            when (errorModel) {
                null -> "You don't have access to this list"
                else -> errorModel.message
            }
        Snackbar.make(
            root_layout,
            errorMessage,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetUsersForDevicesCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetUsersForDevicesFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(
                R.layout.fragment_device_users,
                container,
                false
            )

        val bundle =
            this.arguments
        val deviceId =
            bundle?.getInt(
                "deviceId",
                0
            )
        val isAdmin =
            bundle?.getBoolean(
                "isAdmin"
            )
        val buttonAdd =
            view.findViewById<FloatingActionButton>(
                R.id.addUserButton
            )
        val progressBar =
            view.findViewById<ProgressBar>(
                R.id.progressBar
            )
        progressBar.visibility =
            View.GONE
        buttonAdd.setOnClickListener {
            val addUser =
                Intent(
                    this.activity,
                    DeviceUserGiveAccessActivity::class.java
                )
            addUser.putExtra(
                "deviceId",
                deviceId
            )
            startActivity(
                addUser
            )
        }
        val listOfDeviceUsers =
            view.findViewById<RecyclerView>(
                R.id.listOfDeviceUsers
            )
        val emptyList =
            view.findViewById<TextView>(
                R.id.emptyList
            )
        val rootLayout: CoordinatorLayout =
            view.findViewById(
                R.id.root_layout
            )
        val snackbarCommands =
            Snackbar.make(
                rootLayout,
                "Please, save your device first",
                Snackbar.LENGTH_INDEFINITE
            )
        snackbarCommands.setAction(
            "OK, got it!"
        ) { snackbarCommands.dismiss() }
        if (deviceId == 0) {
            progressBar?.visibility =
                View.GONE
            listOfDeviceUsers.visibility =
                View.GONE
            emptyList.visibility =
                View.VISIBLE
            buttonAdd.visibility =
                View.GONE
            snackbarCommands.show()
        }
        if (!isAdmin!!) {
            buttonAdd.visibility =
                View.GONE
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        val bundle =
            this.arguments
        val deviceId =
            bundle?.getInt(
                "deviceId",
                0
            )
        val app =
            context?.applicationContext as MainApplication
        val deviceAccessHelper =
            DeviceAccessHelper(
                app.getApi()
            )
        if (deviceId != null) {
            deviceAccessHelper.getListOfUsers(
                deviceId,
                this
            )
        }
    }
}
