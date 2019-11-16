package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.final_project.api.helpers.SensorHelper
import com.example.final_project.api.helpers.SensorTypeHelper
import com.example.final_project.api.interfaces.SensorInterface
import com.example.final_project.api.interfaces.SensorTypeInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.fragments.SensorsFragment
import com.example.final_project.models.ErrorModel
import com.example.final_project.models.SensorModel
import com.example.final_project.models.SensorTypeModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sensor_settings.*
import kotlinx.android.synthetic.main.sensor_row.*

class SensorSettingsActivity :
    AppCompatActivity(),
    SensorInterface.SensorGetListener,
    SensorInterface.SensorSaveListener,
    SensorInterface.SensorDeleteListener,
    SensorTypeInterface.SensorTypesListener {

    override fun onSensorTypesGetResponseSuccess(
        list: ArrayList<SensorTypeModel>
    ) {
        sensorTypes.addAll(
            list
        )
        for (line in list) {
            sensorTypesList.add(
                line.typeName.capitalize()
            )
        }
    }

    override fun onSensorTypesGetResponseFailure() {}

    override fun onSensorTypesGetCancelled() {}

    override fun onSensorTypesGetFailure() {}

    override fun onSensorSaveResponseSuccess() {
        finish()
    }

    override fun onSensorSaveResponseFailure(
        errorModel: ErrorModel
    ) {
        Toast.makeText(
            this,
            errorModel.message,
            Toast.LENGTH_SHORT
        )
            .show()

    }

    override fun onSensorSaveCancelled() {
        Toast.makeText(
            this,
            "3",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun onSensorSaveFailure() {
        Toast.makeText(
            this,
            "4",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun onSensorDeleteResponseSuccess() {
        finish()
    }

    override fun onSensorDeleteResponseFailure() {}

    override fun onSensorDeleteCancelled() {}

    override fun onSensorDeleteFailure() {}

    override fun onSensorGetResponseSuccess(
        sensor: SensorModel
    ) {
        progressBar.visibility =
            View.GONE
        sensorNameEditText.setText(
            sensor.name
        )
        sensorPinEditText.setText(
            sensor.pin.toString()
        )
        sensorTypeSpinner.setSelection(
            sensor.sensorTypeId
        )
        sensorSwitch.isChecked =
            sensor.status
    }

    override fun onSensorGetResponseFailure() {}

    override fun onSensorGetCancelled() {}

    override fun onSensorGetFailure() {}

    private var sensorTypesList: ArrayList<String> =
        ArrayList()

    private var sensorTypes: ArrayList<SensorTypeModel> =
        ArrayList()

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

        val sensorTypeHelper =
            SensorTypeHelper(
                app.getApi()
            )

        sensorTypeHelper.getSensorTypes(
            this
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

        var sensorTypeId: Int =
            1

        val typeSpinner =
            this.findViewById<Spinner>(
                R.id.sensorTypeSpinner
            )

        val typesAdapter =
            ArrayAdapter(
                this.applicationContext,
                R.layout.sensor_type_row,
                sensorTypesList
            )

        typesAdapter.setDropDownViewResource(
            R.layout.sensor_type_row
        )

        sensorTypeSpinner.adapter =
            typesAdapter

        sensorTypeSpinner.onItemSelectedListener =
            object :
                AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(
                    p0: AdapterView<*>?
                ) {
                    Toast.makeText(
                        this@SensorSettingsActivity,
                        "xd",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long
                ) {
                    sensorTypeId =
                        p2
                    Toast.makeText(
                        this@SensorSettingsActivity,
                        p2,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

            }

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

        buttonSave.setOnClickListener {
            if (validateAll()) {
                val sensorData =
                    SensorModel(
                        sensorId,
                        sensorNameEditText.text.toString(),
                        sensorPinEditText.text.toString().toInt(),
                        sensorSwitch.isChecked,
                        0,
                        sensorTypeId,
                        controllerId,
                        null
                    )
                if (sensorId == 0) {
                    sensorHelper.addSensor(
                        sensorData,
                        this
                    )
                } else {
                    sensorHelper.editSensor(
                        sensorData,
                        this
                    )
                }
            }
        }

        buttonBack.setOnClickListener {
            val backIntent =
                Intent(
                    this@SensorSettingsActivity,
                    MainActivity::class.java
                )
            backIntent.putExtra("id", controllerId)
            startActivity(
                backIntent
            )
            onBackPressed()
        }

        buttonDelete.setOnClickListener {
            sensorHelper.deleteSensor(
                sensorId,
                this
            )
        }

    }

    private fun validateAll(): Boolean =
        validateName()

    private fun validateName(): Boolean =
        when {
            sensorNameEditText.text.toString().isEmpty() -> {
                sensorNameTextInput.error =
                    "Name field shouldn't be empty"
                false
            }
            else -> true
        }

}
