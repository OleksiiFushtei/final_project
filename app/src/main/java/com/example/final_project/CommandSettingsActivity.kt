package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.final_project.api.helpers.CommandHelper
import com.example.final_project.api.helpers.DeviceConfigurationHelper
import com.example.final_project.api.helpers.DevicesListHelper
import com.example.final_project.api.helpers.MeasuresListHelper
import com.example.final_project.api.interfaces.CommandInterface
import com.example.final_project.api.interfaces.DeviceConfigurationInterface
import com.example.final_project.api.interfaces.DevicesListInterface
import com.example.final_project.api.interfaces.MeasuresListInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_command_settings.*

class CommandSettingsActivity :
    AppCompatActivity(),
    CommandInterface.CommandSaveListener,
    CommandInterface.CommandGetListener,
    CommandInterface.CommandDeleteListener,
    DevicesListInterface.DevicesListListener {

    override fun onGetDevicesListResponseSuccess(
        list: ArrayList<DeviceModel>
    ) {
        devices.clear()
        devices.addAll(
            list
        )
        val app: MainApplication =
            application as MainApplication
        val measureListHelper =
            MeasuresListHelper(
                app.getApi()
            )
        devicesList.clear()
        for (item in list) {
            devicesList.add(
                item.name
            )
        }
        val adapterForDevices =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                devicesList
            )
        adapterForDevices.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        deviceId.adapter =
            adapterForDevices
        deviceId.onItemSelectedListener =
            object :
                AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedDevice =
                        devices[position]
                    selectedDeviceId =
                        devices[position].id
                    deviceTypeId =
                        devices[position].deviceTypeId
                    measureListHelper.getListOfMeasures(
                        deviceTypeId,
                        object :
                            MeasuresListInterface.MeasuresListListener {
                            override fun onGetMeasuresListResponseSuccess(
                                list: ArrayList<MeasureModel>
                            ) {
                                measures.clear()
                                measures.addAll(
                                    list
                                )
                                measuresList.clear()
                                for (item in measures) {
                                    measuresList.add(
                                        item.measureName
                                    )
                                }
                                val adapterForMeasures =
                                    ArrayAdapter<String>(
                                        applicationContext,
                                        android.R.layout.simple_list_item_1,
                                        measuresList
                                    )
                                adapterForMeasures.setDropDownViewResource(
                                    android.R.layout.simple_spinner_dropdown_item
                                )
                                adapterForMeasures.notifyDataSetChanged()
                                measureId.adapter =
                                    adapterForMeasures
                                measureId.onItemSelectedListener =
                                    object :
                                        AdapterView.OnItemSelectedListener {

                                        override fun onNothingSelected(
                                            parent: AdapterView<*>?
                                        ) {

                                        }

                                        override fun onItemSelected(
                                            parent: AdapterView<*>?,
                                            view: View?,
                                            position: Int,
                                            id: Long
                                        ) {
                                            selectedMeasure =
                                                list[position]
                                            selectedMeasureId =
                                                list[position].id
                                        }

                                    }
                            }

                            override fun onGetMeasuresListResponseFailure(
                                errorModel: ErrorModel?
                            ) {
                                val errorMessage =
                                    when (errorModel) {
                                        null -> "Server error"
                                        else -> errorModel.message
                                    }
                                Snackbar.make(
                                    root_layout,
                                    errorMessage,
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                            }

                            override fun onGetMeasuresListCancelled() {
                                progressBar.visibility =
                                    View.GONE
                                Snackbar.make(
                                    root_layout,
                                    "Something went wrong. Try again",
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                            }

                            override fun onGetMeasuresListFailure() {
                                progressBar.visibility =
                                    View.GONE
                                Snackbar.make(
                                    root_layout,
                                    "Check your connection to the internet",
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    )
                }

            }
    }

    override fun onGetDevicesListResponseFailure(
        errorModel: ErrorModel
    ) {
        val message: String =
            when {
                errorModel.message.isEmpty() -> "An error occupied. Try again"
                else -> errorModel.message
            }
        Snackbar.make(
            root_layout,
            message,
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

    override fun onCommandSaveResponseSuccess() {
        finish()
    }

    override fun onCommandSaveResponseFailure(
        errorModel: ErrorModel?
    ) {
        val errorMessage =
            when (errorModel) {
                null -> "Server error"
                else -> errorModel.message
            }
        Snackbar.make(
            root_layout,
            errorMessage,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCommandSaveCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCommandSaveFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCommandGetResponseSuccess(
        commandModel: CommandModel
    ) {
        progressBar.visibility =
            View.GONE
        command =
            commandModel
        deviceId.setSelection(
            devicesList.indexOf(
                commandModel.deviceConfiguration.device.name
            )
        )
        measureId.setSelection(
            measuresList.indexOf(
                commandModel.deviceConfiguration.measure.measureName
            )
        )
        valueEditText.setText(
            commandModel.deviceConfiguration.value.toString()
        )
        nameEditText.setText(
            commandModel.name
        )
        val minutes =
            commandModel.timeSpan.substring(
                0,
                2
            ).toInt() * 60
        val seconds =
            commandModel.timeSpan.substring(
                3,
                5
            )
                .toInt()
        val timeSpan =
            minutes + seconds
        timeSpanEditText.setText(
            "$timeSpan"
        )
        cbEnd.isChecked =
            commandModel.end
    }

    override fun onCommandGetResponseFailure(
        errorModel: ErrorModel?
    ) {
        val errorMessage =
            when (errorModel) {
                null -> "Server error"
                else -> errorModel.message
            }
        Snackbar.make(
            root_layout,
            errorMessage,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCommandGetCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCommandGetFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCommandDeleteResponseSuccess() {
        finish()
    }

    override fun onCommandDeleteResponseFailure(
        errorModel: ErrorModel?
    ) {
        val errorMessage =
            when (errorModel) {
                null -> "Server error"
                else -> errorModel.message
            }
        Snackbar.make(
            root_layout,
            errorMessage,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCommandDeleteCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCommandDeleteFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    private var devices: ArrayList<DeviceModel> =
        ArrayList()
    private var measures: ArrayList<MeasureModel> =
        ArrayList()
    private val devicesList: ArrayList<String> =
        ArrayList()
    private val measuresList: ArrayList<String> =
        ArrayList()
    lateinit var selectedDevice: DeviceModel
    lateinit var selectedMeasure: MeasureModel
    private var selectedDeviceId =
        0
    private var selectedMeasureId =
        0
    private var deviceTypeId =
        0
    private var deviceConfigId =
        0
    private lateinit var command: CommandModel


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_command_settings
        )
        val controllerId =
            intent.getIntExtra(
                "controllerId",
                0
            )
        val scriptId =
            intent.getIntExtra(
                "scriptId",
                0
            )
        val commandId =
            intent.getIntExtra(
                "commandId",
                0
            )
        val app: MainApplication =
            application as MainApplication
        val deviceListHelper =
            DevicesListHelper(
                app.getApi()
            )
        deviceListHelper.getListOfDevices(
            controllerId,
            this
        )
        val commandHelper =
            CommandHelper(
                app.getApi()
            )
        if (commandId != 0) {
            commandHelper.getCommand(
                commandId,
                this
            )
        }
        val deviceConfigurationHelper =
            DeviceConfigurationHelper(
                app.getApi()
            )
        progressBar.visibility =
            View.GONE
        buttonSave.setOnClickListener {
            if (validateAll()) {
                deviceConfigId =
                    when (commandId) {
                        0 -> 0
                        else -> command.deviceConfigurationId
                    }
                val minutes =
                    timeSpanEditText.text.toString()
                        .toInt() / 60
                val seconds =
                    timeSpanEditText.text.toString()
                        .toInt() % 60
                val timeSpan: String
                timeSpan =
                    when {
                        minutes < 10 -> when {
                            seconds < 10 -> "0$minutes:0$seconds:000"
                            else -> "0$minutes:$seconds:000"
                        }
                        else -> when {
                            seconds < 10 -> "$minutes:0$seconds:000"
                            else -> "$minutes:$seconds:000"
                        }
                    }
                val deviceConfiguration =
                    DeviceConfigurationModel(
                        id = deviceConfigId,
                        value = valueEditText.text.toString().toInt(),
                        deviceId = selectedDeviceId,
                        measureId = selectedMeasureId,
                        device = selectedDevice,
                        measure = selectedMeasure
                    )

                fun save(
                    configId: Int
                ) {
                    val checkData =
                        CommandModel(
                            id = commandId,
                            scriptId = scriptId,
                            timeSpan = timeSpan,
                            deviceConfigurationId = configId,
                            name = nameEditText.text.toString(),
                            end = cbEnd.isChecked,
                            deviceConfiguration = deviceConfiguration
                        )
                    if (commandId == 0) {
                        commandHelper.addCommand(
                            checkData,
                            this
                        )
                    } else {
                        commandHelper.editCommand(
                            checkData,
                            this
                        )
                    }
                }
                if (deviceConfigId == 0) {
                    deviceConfigurationHelper.addDeviceConfiguration(
                        deviceConfiguration,
                        object :
                            DeviceConfigurationInterface.DeviceConfigurationSaveListener {
                            override fun onDeviceConfigurationSaveResponseSuccess(
                                config: DeviceConfigurationModel
                            ) {
                                save(
                                    config.id
                                )
                            }

                            override fun onDeviceConfigurationSaveResponseFailure(
                                errorModel: ErrorModel?
                            ) {
                                val errorMessage =
                                    when (errorModel) {
                                        null -> "Server error"
                                        else -> errorModel.message
                                    }
                                Snackbar.make(
                                    root_layout,
                                    errorMessage,
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                            }

                            override fun onDeviceConfigurationSaveCancelled() {
                                progressBar.visibility =
                                    View.GONE
                                Snackbar.make(
                                    root_layout,
                                    "Something went wrong. Try again",
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                            }

                            override fun onDeviceConfigurationSaveFailure() {
                                progressBar.visibility =
                                    View.GONE
                                Snackbar.make(
                                    root_layout,
                                    "Check your connection to the internet",
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    )
                } else {
                    deviceConfigurationHelper.editDeviceConfiguration(
                        deviceConfiguration,
                        object :
                            DeviceConfigurationInterface.DeviceConfigurationSaveListener {
                            override fun onDeviceConfigurationSaveResponseSuccess(
                                config: DeviceConfigurationModel
                            ) {
                                save(
                                    config.id
                                )
                            }

                            override fun onDeviceConfigurationSaveResponseFailure(
                                errorModel: ErrorModel?
                            ) {
                                val errorMessage =
                                    when (errorModel) {
                                        null -> "Server error"
                                        else -> errorModel.message
                                    }
                                Snackbar.make(
                                    root_layout,
                                    errorMessage,
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                            }

                            override fun onDeviceConfigurationSaveCancelled() {
                                progressBar.visibility =
                                    View.GONE
                                Snackbar.make(
                                    root_layout,
                                    "Something went wrong. Try again",
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                            }

                            override fun onDeviceConfigurationSaveFailure() {
                                progressBar.visibility =
                                    View.GONE
                                Snackbar.make(
                                    root_layout,
                                    "Check your connection to the internet",
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    )
                }
            }
        }
        if (commandId == 0) {
            buttonDelete.visibility =
                View.GONE
        }
        buttonDelete.setOnClickListener {
            commandHelper.deleteCommand(
                commandId,
                this
            )
        }
        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun validateAll(): Boolean {
        valueTextInput.error =
            null
        nameTextInput.error =
            null
        timeSpanTextInput.error =
            null
        return validateValue() && validateName() && validateTimeSpan()
    }

    private fun validateValue(): Boolean =
        when {
            valueEditText.text.toString().isEmpty() -> {
                valueTextInput.error =
                    "Value field shouldn't be empty"
                false
            }
            valueEditText.text.toString().toInt() < 0 -> {
                valueTextInput.error =
                    "Value shouldn't be less than 0"
                false
            }
            valueEditText.text.toString().toInt() > 255 -> {
                valueTextInput.error =
                    "Value shouldn't be greater than 255"
                false
            }
            else -> {
                valueTextInput.error =
                    null
                true
            }
        }

    private fun validateName(): Boolean =
        when {
            nameEditText.text.toString().isEmpty() -> {
                nameTextInput.error =
                    "Name field shouldn't be empty"
                false
            }
            else -> {
                nameTextInput.error =
                    null
                true
            }
        }

    private fun validateTimeSpan(): Boolean =
        when {
            timeSpanEditText.text.toString().isEmpty() -> {
                timeSpanTextInput.error =
                    "Time span field shouldn't be empty"
                false
            }
            timeSpanEditText.text.toString().toInt() > 600 -> {
                timeSpanTextInput.error =
                    "Time span shouldn't be greater than 600"
                false
            }
            else -> {
                timeSpanTextInput.error =
                    null
                true
            }
        }
}
