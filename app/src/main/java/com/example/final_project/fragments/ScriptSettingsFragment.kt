package com.example.final_project.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.final_project.R
import com.example.final_project.api.helpers.ConditionTypeListHelper
import com.example.final_project.api.helpers.ScriptHelper
import com.example.final_project.api.helpers.SensorsListHelper
import com.example.final_project.api.interfaces.ConditionTypeListInterface
import com.example.final_project.api.interfaces.ScriptInterface
import com.example.final_project.api.interfaces.SensorsListInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ConditionTypeModel
import com.example.final_project.models.ErrorModel
import com.example.final_project.models.ScriptModel
import com.example.final_project.models.SensorModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_script_settings.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScriptSettingsFragment :
    Fragment(),
    ScriptInterface.ScriptSaveListener,
    ScriptInterface.ScriptGetListener,
    ScriptInterface.ScriptDeleteListener,
    SensorsListInterface.SensorsListListener,
    ConditionTypeListInterface.ConditionTypeListListener {

    companion object {
        private const val CONTROLLERID =
            "controllerId"
        private const val SCRIPTID =
            "scriptId"

        fun newInstance(
            caughtId: Int,
            caughtScriptId: Int
        ): ScriptSettingsFragment {
            val args =
                Bundle()
            args.putSerializable(
                CONTROLLERID,
                caughtId
            )
            args.putSerializable(
                SCRIPTID,
                caughtScriptId
            )
            val fragment =
                ScriptSettingsFragment()
            fragment.arguments =
                args
            return fragment
        }
    }

    override fun onResponseSuccess(
        list: ArrayList<ConditionTypeModel>
    ) {
        for (item in list) {
            condTypesList.add(
                item.type
            )
        }
        val adapter =
            ArrayAdapter<String>(
                this.context!!,
                android.R.layout.simple_list_item_1,
                condTypesList
            )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        conditionType.adapter =
            adapter
        conditionType.onItemSelectedListener =
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
                    selectedCondType =
                        list[position].id
                    selectedConditionType =
                        list[position]
                }

            }
    }

    override fun onResponseFailure(
        errorModel: ErrorModel?
    ) {
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

    override fun onCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetSensorsListResponseSuccess(
        list: ArrayList<SensorModel>
    ) {
        for (item in list) {
            sensorsList.add(
                item.name
            )
        }
        val adapter =
            ArrayAdapter<String>(
                this.context!!,
                android.R.layout.simple_list_item_1,
                sensorsList
            )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        sensorId.adapter =
            adapter
        sensorId.onItemSelectedListener =
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
                    selectedSensorId =
                        list[position].id
                    selectedSensor =
                        list[position]
                }

            }

    }

    override fun onGetSensorsListResponseFailure(
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

    override fun onScriptSaveResponseSuccess() {
        activity?.finish()
    }

    override fun onScriptSaveResponseFailure(
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

    override fun onScriptSaveCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onScriptSaveFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onScriptGetResponseSuccess(
        scriptModel: ScriptModel
    ) {
        progressBar.visibility =
            View.GONE
        script =
            scriptModel
        scriptNameEditText.setText(
            scriptModel.name
        )
        if (scriptModel.conditionValue != null) {
            cbSensor.isChecked =
                true
            sensorId.setSelection(
                sensorsList.indexOf(
                    scriptModel.sensor.name
                )
            )
            conditionType.setSelection(
                condTypesList.indexOf(
                    scriptModel.conditionType.type
                )
            )
            scriptConditionValueEditText.setText(
                scriptModel.conditionValue.toDouble()
                    .toString()
            )
        }
        if (scriptModel.timeTo != null) {
            cbTimeTo.isChecked =
                true
            scriptDateToInput.isEnabled =
                true
            scriptDateToEditText.isEnabled =
                true
            val format =
                SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss",
                    Locale.UK
                )
            val dateTo: Date? =
                format.parse(
                    scriptModel.timeTo
                )
            val formatD =
                SimpleDateFormat(
                    "yyyy/MM/dd",
                    Locale.UK
                )
            val dateD =
                formatD.format(
                    dateTo!!
                )
            val formatT =
                SimpleDateFormat(
                    "HH:mm",
                    Locale.UK
                )
            val dateT =
                formatT.format(
                    dateTo
                )
            scriptDateToEditText.setText(
                dateD
            )
            scriptTimeToInput.isEnabled =
                true
            scriptTimeToEditText.isEnabled =
                true
            scriptTimeToEditText.setText(
                dateT
            )
        }
        if (scriptModel.repeatTimes != -1) {
            cbRepeat.isChecked =
                true
            scriptRepeatInput.isEnabled =
                true
            scriptRepeatEditText.isEnabled =
                true
            scriptRepeatEditText.setText(
                scriptModel.repeatTimes.toString()
            )
        }
        scriptPrioritySlider.value =
            scriptModel.priority.toFloat()
        scriptPrioritySlider.value =
            scriptModel.priority.toDouble()
                .toFloat()
        scriptSwitch.isChecked =
            scriptModel.status
    }

    override fun onScriptGetResponseFailure(
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

    override fun onScriptGetCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onScriptGetFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onScriptDeleteResponseSuccess() {
        activity?.finish()
    }

    override fun onScriptDeleteResponseFailure(
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

    override fun onScriptDeleteCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onScriptDeleteFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    private lateinit var script: ScriptModel
    private lateinit var selectedSensor: SensorModel
    private lateinit var selectedConditionType: ConditionTypeModel
    private var sensorsList: ArrayList<String> =
        ArrayList()
    private var condTypesList: ArrayList<String> =
        ArrayList()
    var selectedSensorId =
        0
    var selectedCondType =
        0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(
                R.layout.fragment_script_settings,
                container,
                false
            )
        val bundle =
            this.arguments
        val controllerId =
            bundle?.getInt(
                "controllerId",
                0
            )
        val scriptId =
            bundle?.getInt(
                "scriptId",
                0
            )
        val app =
            context?.applicationContext as MainApplication
        val scriptHelper =
            ScriptHelper(
                app.getApi()
            )
        val progressBar =
            view.findViewById<ProgressBar>(
                R.id.progressBar
            )
        progressBar.visibility =
            View.GONE
        val prioritySlider =
            view.findViewById<Slider>(
                R.id.scriptPrioritySlider
            )
        prioritySlider.isFloatingLabel =
            false
        prioritySlider.setLabelFormatter { value ->
            value.toInt()
                .toString()
        }
        val cbSensor =
            view.findViewById<MaterialCheckBox>(
                R.id.cbSensor
            )
        val condValueInput =
            view.findViewById<TextInputLayout>(
                R.id.scriptConditionValueInput
            )
        val condValueEditText =
            view.findViewById<TextInputEditText>(
                R.id.scriptConditionValueEditText
            )
        val cbTo =
            view.findViewById<MaterialCheckBox>(
                R.id.cbTimeTo
            )
        val cbRepeat =
            view.findViewById<MaterialCheckBox>(
                R.id.cbRepeat
            )
        val dateToInput =
            view.findViewById<TextInputLayout>(
                R.id.scriptDateToInput
            )
        val dateToEditText =
            view.findViewById<TextInputEditText>(
                R.id.scriptDateToEditText
            )
        val timeToInput =
            view.findViewById<TextInputLayout>(
                R.id.scriptTimeToInput
            )
        val timeToEditText =
            view.findViewById<TextInputEditText>(
                R.id.scriptTimeToEditText
            )
        val repeatInput =
            view.findViewById<TextInputLayout>(
                R.id.scriptRepeatInput
            )
        val repeatEditText =
            view.findViewById<TextInputEditText>(
                R.id.scriptRepeatEditText
            )
        val calendar: Calendar =
            Calendar.getInstance()
        val dateSetListener: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val m =
                    month + 1
                val date =
                    "$year/$m/$dayOfMonth"
                dateToEditText.setText(
                    date
                )
                calendar.set(
                    Calendar.YEAR,
                    year
                )
                calendar.set(
                    Calendar.MONTH,
                    month
                )
                calendar.set(
                    Calendar.DAY_OF_MONTH,
                    dayOfMonth
                )
            }
        dateToEditText.setOnClickListener {
            val year =
                calendar.get(
                    Calendar.YEAR
                )
            val month =
                calendar.get(
                    Calendar.MONTH
                )
            val day =
                calendar.get(
                    Calendar.DAY_OF_MONTH
                )
            val datePicker =
                DatePickerDialog(
                    this.context!!,
                    R.style.ThemeOverlay_MaterialComponents_Dialog,
                    dateSetListener,
                    year,
                    month,
                    day
                )
            datePicker.show()
        }
        val timeSetListener: TimePickerDialog.OnTimeSetListener =
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                val time =
                    "$hourOfDay:$minute"
                timeToEditText.setText(
                    time
                )
                calendar.set(
                    Calendar.HOUR_OF_DAY,
                    hourOfDay
                )
                calendar.set(
                    Calendar.MINUTE,
                    minute
                )
            }
        timeToEditText.setOnClickListener {
            val hour =
                calendar.get(
                    Calendar.HOUR_OF_DAY
                )
            val minute =
                calendar.get(
                    Calendar.MINUTE
                )
            val timePicker =
                TimePickerDialog(
                    this.context,
                    R.style.ThemeOverlay_MaterialComponents_Dialog,
                    timeSetListener,
                    hour,
                    minute,
                    true
                )
            timePicker.show()
        }
        cbSensor.isChecked =
            false
        cbTo.isChecked =
            false
        cbRepeat.isChecked =
            false
        condValueInput.isEnabled =
            false
        condValueEditText.isEnabled =
            false
        dateToInput.isEnabled =
            false
        dateToEditText.isEnabled =
            false
        timeToInput.isEnabled =
            false
        timeToEditText.isEnabled =
            false
        repeatInput.isEnabled =
            false
        repeatEditText.isEnabled =
            false
        if (scriptId != null) {
            if (scriptId != 0) {
                progressBar.visibility =
                    View.VISIBLE
                scriptHelper.getScript(
                    scriptId,
                    this
                )
            }
        }
        cbSensor.setOnCheckedChangeListener { _, isChecked ->
            val sensorListHelper =
                SensorsListHelper(
                    app.getApi()
                )
            sensorListHelper.getListOfSensors(
                controllerId!!,
                this
            )
            val conditionListHelper =
                ConditionTypeListHelper(
                    app.getApi()
                )
            conditionListHelper.getTypes(
                this
            )
            if (isChecked) {
                condValueInput.isEnabled =
                    true
                condValueEditText.isEnabled =
                    true
            } else {
                condValueInput.isEnabled =
                    false
                condValueEditText.isEnabled =
                    false
            }
        }
        cbTo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                dateToInput.isEnabled =
                    true
                dateToEditText.isEnabled =
                    true
                timeToInput.isEnabled =
                    true
                timeToEditText.isEnabled =
                    true
            } else {
                dateToInput.isEnabled =
                    false
                dateToEditText.isEnabled =
                    false
                timeToInput.isEnabled =
                    false
                timeToEditText.isEnabled =
                    false
            }
        }
        cbRepeat.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                repeatInput.isEnabled =
                    true
                repeatEditText.isEnabled =
                    true
            } else {
                repeatInput.isEnabled =
                    false
                repeatEditText.isEnabled =
                    false
            }
        }
        val buttonSave =
            view.findViewById<MaterialButton>(
                R.id.buttonSave
            )
        buttonSave.setOnClickListener {
            if (validateAll()) {
                val id: Int =
                    if (scriptId == 0) 0 else script.id
                val timeFrom: String =
                    if (scriptId == 0) {
                        SimpleDateFormat(
                            "yyyy-MM-dd'T'HH:mm:ss:",
                            Locale.UK
                        ).format(
                            Date()
                        )
                    } else script.timeFrom
                val timeTo: String? =
                    if (cbTo.isChecked) {
                        SimpleDateFormat(
                            "yyyy-MM-dd'T'HH:mm:ss",
                            Locale.UK
                        ).format(
                            calendar.time
                        )
                    } else null
                val repeatTimes: Int =
                    if (cbRepeat.isChecked) {
                        scriptRepeatEditText.text.toString()
                            .toInt()
                    } else -1
                val condValue: Double? =
                    if (cbSensor.isChecked)
                        scriptConditionValueEditText.text.toString().toDouble()
                    else null
                val finalSensorId: Int? =
                    if (cbSensor.isChecked)
                        selectedSensorId
                    else null
                val finalContType: Int? =
                    if (cbSensor.isChecked)
                        selectedCondType
                    else null
                val checkData =
                    ScriptModel(
                        id = id,
                        name = scriptNameEditText.text.toString(),
                        conditionValue = condValue,
                        conditionTypeId = finalContType,
                        controllerId = controllerId!!,
                        sensorId = finalSensorId,
                        timeFrom = timeFrom,
                        timeTo = timeTo,
                        delta = 0.5,
                        repeatTimes = repeatTimes,
                        status = scriptSwitch.isChecked,
                        priority = prioritySlider.value.toInt(),
                        sensor = selectedSensor,
                        conditionType = selectedConditionType

                    )
                if (scriptId == 0) {
                    scriptHelper.addScript(
                        checkData,
                        this
                    )
                } else {
                    scriptHelper.editScript(
                        checkData,
                        this
                    )
                }
            }
        }
        val buttonBack =
            view.findViewById<MaterialButton>(
                R.id.buttonBack
            )
        buttonBack.setOnClickListener {
            activity?.finish()
        }
        val buttonDelete =
            view.findViewById<MaterialButton>(
                R.id.buttonDelete
            )
        if (scriptId == 0) {
            buttonDelete.visibility =
                View.GONE
        } else {
            buttonDelete.visibility =
                View.VISIBLE
            buttonDelete.setOnClickListener {
                scriptHelper.deleteScript(
                    scriptId!!,
                    this
                )
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        val bundle =
            this.arguments
        val scriptId =
            bundle?.getInt(
                "scriptId",
                0
            )
        val app =
            context?.applicationContext as MainApplication
        val scriptHelper =
            ScriptHelper(
                app.getApi()
            )
        val progressBar =
            view?.findViewById<ProgressBar>(
                R.id.progressBar
            )
        progressBar?.visibility =
            View.GONE
        if (scriptId != null) {
            if (scriptId != 0) {
                progressBar?.visibility =
                    View.VISIBLE
                scriptHelper.getScript(
                    scriptId,
                    this
                )
            }
        }
    }

    private fun validateAll(): Boolean =
        validateName() && validateConditionValue() && validateTimeTo()

    private fun validateName(): Boolean =
        when {
            scriptNameEditText.text.toString().isEmpty() -> {
                scriptNameTextInput.error =
                    "Name field shouldn't be empty"
                false
            }
            else -> {
                scriptNameTextInput.error =
                    null
                true
            }
        }

    private fun validateConditionValue(): Boolean =
        when {
            cbSensor.isChecked -> when {
                scriptConditionValueEditText.text.toString().isEmpty() -> {
                    scriptConditionValueInput.error =
                        "Condition value field shouldn't be empty"
                    false
                }
                else -> {
                    scriptConditionValueInput.error =
                        null
                    true
                }
            }
            else -> {
                true
            }
        }

    private fun validateTimeTo(): Boolean =
        when {
            cbTimeTo.isChecked -> when {
                scriptDateToEditText.text.toString().isEmpty() && scriptTimeToEditText.text.toString().isEmpty() -> {
                    scriptDateToInput.error =
                        "Date to field shouldn't be empty"
                    scriptTimeToInput.error =
                        "Time to field shouldn't be empty"
                    false
                }
                scriptDateToEditText.text.toString().isNotEmpty() && scriptTimeToEditText.text.toString().isEmpty() -> {
                    scriptDateToInput.error =
                        null
                    false
                }
                scriptDateToEditText.text.toString().isEmpty() && scriptTimeToEditText.text.toString().isNotEmpty() -> {
                    scriptTimeToInput.error =
                        null
                    false
                }
                else -> {
                    scriptDateToInput.error =
                        null
                    scriptTimeToInput.error =
                        null
                    true
                }
            }
            else -> true
        }
}