package com.example.final_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.SensorSettingsActivity
import com.example.final_project.adapters.SensorAdapter
import com.example.final_project.api.helpers.SensorHubHelper
import com.example.final_project.api.helpers.SensorsListHelper
import com.example.final_project.api.interfaces.SensorsListInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ErrorModel
import com.example.final_project.models.SensorModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_sensor.*
import java.lang.Exception

class SensorsFragment :
    Fragment(),
    SensorsListInterface.SensorsListListener {

    private lateinit var sensorHubHelper: SensorHubHelper

    companion object {
        private const val ID =
            "id"
        private const val ISADMIN =
            "isAdmin"

        fun newInstance(
            caughtId: Int,
            caughtIsAdmin: Boolean
        ): SensorsFragment {
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
                SensorsFragment()
            fragment.arguments =
                args
            return fragment
        }
    }

    override fun onGetSensorsListResponseSuccess(
        list: ArrayList<SensorModel>
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
        progressBar?.visibility =
            View.GONE
        listOfSensors.layoutManager =
            LinearLayoutManager(
                context
            )
        listOfSensors.adapter =
            SensorAdapter(
                list,
                context,
                controlledId = controllerId,
                isAdmin = isAdmin
            )
        sensorsList.addAll(
            list
        )
        sensorHubHelper =
            SensorHubHelper(
                Hawk.get(
                    "token"
                )
            )
        if (!sensorHubHelper.hubCheck()) {
            sensorHubHelper.hubInit()
        }
        sensorHubHelper.updateSensor(
            listOfSensors
        ) { sensorModel: SensorModel, view: RecyclerView ->
            activity?.runOnUiThread {
                val sensorAdapter =
                    view.adapter as SensorAdapter
                try {
                    val item =
                        sensorAdapter.getItems()
                            .first {
                                it.id == sensorModel.id
                            }
                    item.value =
                        sensorModel.value
                    sensorAdapter.notifyDataSetChanged()
                } catch (e: Exception) {

                }
            }
        }
        sensorHubHelper.hubInit()
        when {
            list.isEmpty() -> {
                listOfSensors.visibility =
                    View.GONE
                emptyList.visibility =
                    View.VISIBLE
            }
            else -> {
                listOfSensors.visibility =
                    View.VISIBLE
                emptyList.visibility =
                    View.GONE
            }
        }
    }

    override fun onGetSensorsListResponseFailure(
        errorModel: ErrorModel
    ) {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            errorModel.message,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetSensorsListCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetSensorsListFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }


    private val sensorsList: ArrayList<SensorModel> =
        ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(
                R.layout.fragment_sensor,
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
        val app =
            context?.applicationContext as MainApplication
        val progressBar =
            view.findViewById<ProgressBar>(
                R.id.progressBar
            )
        progressBar?.visibility =
            View.VISIBLE
        val sensorsListHelper =
            SensorsListHelper(
                app.getApi()
            )
        if (controllerId != null) {
            sensorsListHelper.getListOfSensors(
                controllerId,
                this
            )
        }
        val addButton =
            view.findViewById<FloatingActionButton>(
                R.id.addSensorButton
            )
        addButton?.setOnClickListener {
            val addSensor =
                Intent(
                    this.activity,
                    SensorSettingsActivity::class.java
                )
            addSensor.putExtra(
                "controllerId",
                controllerId
            )
            addSensor.putExtra(
                "sensorId",
                0
            )
            startActivity(
                addSensor
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
        val sensorsListHelper =
            SensorsListHelper(
                app.getApi()
            )
        if (controllerId != null) {
            sensorsListHelper.getListOfSensors(
                controllerId,
                this
            )
        }
    }

    override fun onPause() {
        super.onPause()
        sensorHubHelper =
            SensorHubHelper(
                Hawk.get(
                    "token"
                )
            )
        if (sensorHubHelper.hubCheck()) {
            sensorHubHelper.hubStop()
        }
    }
}