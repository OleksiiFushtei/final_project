package com.example.final_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.R
import com.example.final_project.SensorSettingsActivity
import com.example.final_project.adapters.SensorAdapter
import com.example.final_project.api.helpers.SensorsListHelper
import com.example.final_project.api.interfaces.SensorsListInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.SensorModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_sensor.*
import com.google.android.material.floatingactionbutton.FloatingActionButton



class SensorsFragment :
    Fragment(),
    SensorsListInterface.SensorsListListener {

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
        if (list.isEmpty()) {
            Snackbar.make(
                root_layout,
                "You don't have any available sensors",
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun onGetSensorsListResponseFailure() {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        val button = view?.findViewById(R.id.addSensorButton) as FloatingActionButton
        button.setOnClickListener {
            val newIntent = Intent(this.activity, SensorSettingsActivity::class.java)
            startActivity(newIntent)
        }


//        val b = root_layout.frameLayout.findViewById<FloatingActionButton>(R.id.addSensorButton).setOnClickListener{
//            val intent = Intent(activity, SensorSettingsActivity::class.java)
//            intent.putExtra("controllerId", controllerId)
//            intent.putExtra("sensorId", 0)
//            startActivity(intent)
//        }

//        val b1 = this.addSensorButton
//
//        b1.setOnClickListener{
//            val intent = Intent(activity, SensorSettingsActivity::class.java)
//            intent.putExtra("controllerId", controllerId)
//            intent.putExtra("sensorId", 0)
//            startActivity(intent)
//        }
//
//        button?.setOnClickListener {
//            val addSensorIntent = Intent(activity, SensorSettingsActivity::class.java)
//            addSensorIntent.putExtra("controllerId", controllerId)
//            addSensorIntent.putExtra("id", 0)
//            context?.startActivity(addSensorIntent)
//        }
//
//        addButton?.setOnClickListener {
//            val intent = Intent(activity, SensorSettingsActivity::class.java)
//            intent.putExtra("controllerId", controllerId)
//            intent.putExtra("sensorId", 0)
//            startActivity(intent)
//        }

        return inflater.inflate(
            R.layout.fragment_sensor,
            container,
            false
        )
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
}