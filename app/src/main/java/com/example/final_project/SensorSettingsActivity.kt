package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.final_project.api.helpers.SensorHelper
import com.example.final_project.api.interfaces.SensorInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.SensorModel
import kotlinx.android.synthetic.main.activity_sensor_settings.*
import kotlinx.android.synthetic.main.sensor_row.*

class SensorSettingsActivity :
    AppCompatActivity(),
    SensorInterface.SensorGetListener {
    override fun onSensorGetResponseSuccess(
        sensor: SensorModel
    ) {
        progressBar.visibility =
            View.GONE
        sensorNameEditText.setText(sensor.name)
//        pinEditText.setText(sensor.pin)
        sensorSwitch.isChecked = sensor.status
    }

    override fun onSensorGetResponseFailure() {

    }

    override fun onSensorGetCancelled() {

    }

    override fun onSensorGetFailure() {

    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_sensor_settings
        )

        val app: MainApplication =
            application as MainApplication

        val sensorHelper =
            SensorHelper(
                app.getApi()
            )

        val controllerId =
            intent.getIntExtra(
                "controllerId",
                0
            )
        val sensorId =
            intent.getIntExtra(
                "sensorId",
                0
            )

        if (sensorId == 0) {
            buttonDelete.visibility =
                View.INVISIBLE
            progressBar.visibility =
                View.GONE
        } else {
            sensorHelper.getSensor(
                sensorId,
                this
            )
        }
    }
}
