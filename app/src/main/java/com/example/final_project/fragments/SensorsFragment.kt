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

        fun newInstance(
            caught: Int
        ): SensorsFragment {
            val args =
                Bundle()
            args.putSerializable(
                ID,
                caught
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
        progressBar.visibility =
            View.GONE
        listOfSensors.layoutManager =
            LinearLayoutManager(
                context
            )
        listOfSensors.adapter =
            SensorAdapter(
                list,
                context,
                controlledId = controllerId
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
        if (list.isEmpty()) {
            Snackbar.make(
                root_layout,
                "You don't have any available sensors",
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun onGetSensorsListResponseFailure(errorModel: ErrorModel) {
        Snackbar.make(
            root_layout,
            "1",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetSensorsListCancelled() {
        Snackbar.make(
            root_layout,
            "2",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetSensorsListFailure() {
        Snackbar.make(
            root_layout,
            "3",
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
        // Inflate the layout for this fragment
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
            val newIntent =
                Intent(
                    this.activity,
                    SensorSettingsActivity::class.java
                )
            newIntent.putExtra(
                "controllerId",
                controllerId
            )
            newIntent.putExtra(
                "sensorId",
                0
            )
            startActivity(
                newIntent
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
        sensorHubHelper.hubStop()
    }
}