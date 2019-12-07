package com.example.final_project.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TimePicker
import com.example.final_project.R
import com.example.final_project.api.helpers.ScriptHelper
import com.example.final_project.api.interfaces.ScriptInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ErrorModel
import com.example.final_project.models.ScriptModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_script_settings.*
import java.util.*

class ScriptSettingsFragment :
    Fragment(),
    ScriptInterface.ScriptSaveListener,
    ScriptInterface.ScriptGetListener,
    ScriptInterface.ScriptDeleteListener {

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

    override fun onScriptSaveResponseSuccess() {
        activity?.finish()
    }

    override fun onScriptSaveResponseFailure(
        errorModel: ErrorModel
    ) {

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

    }

    override fun onScriptGetResponseFailure(
        errorModel: ErrorModel
    ) {

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
        val dateSetListener: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val m =
                    month + 1
                val date =
                    "$dayOfMonth/$m/$year"
                dateToEditText.setText(
                    date
                )
            }
        dateToEditText.setOnClickListener {
            val calendar: Calendar =
                Calendar.getInstance()
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
            }
        timeToEditText.setOnClickListener {
            val calendar =
                Calendar.getInstance()
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
        cbTo.isChecked =
            false
        cbRepeat.isChecked =
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
                val checkData =
                    ScriptModel(
                        scriptId!!,
                        scriptNameEditText.text.toString(),
                        0.0,
                        1,
                        controllerId!!,
                        Date(),
                        1,
                        Date(),
                        Date(),
                        1.0,
                        1,
                        completed = false,
                        visible = true,
                        priority = 1,
                        commands = null
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
    }

    private fun validateAll(): Boolean {
        return true
    }
}
