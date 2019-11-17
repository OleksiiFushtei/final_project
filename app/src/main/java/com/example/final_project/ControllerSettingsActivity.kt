package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.final_project.api.helpers.ControllerHelper
import com.example.final_project.api.interfaces.ControllerInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ControllerModel
import com.example.final_project.models.ErrorModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_controller_settings.*

class ControllerSettingsActivity :
    AppCompatActivity(),
    ControllerInterface.ControllerGetListener,
    ControllerInterface.ControllerSaveListener,
    ControllerInterface.ControllerDeleteListener {

    override fun onControllerSaveResponseSuccess() {
        finish()
    }

    override fun onControllerSaveResponseFailure(errorModel: ErrorModel) {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            errorModel.message,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onControllerSaveCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Saving cancelled. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onControllerSaveFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the Internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onDeleteControllerResponseSuccess() {
        finish()
    }

    override fun onDeleteControllerResponseFailure(errorModel: ErrorModel) {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            errorModel.message,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onDeleteControllerCancelled() {
        progressBar.visibility =
        View.GONE
        Snackbar.make(
            root_layout,
            "Deleting cancelled. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()}

    override fun onDeleteControllerFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the Internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetControllerResponseSuccess(
        controller: ControllerModel
    ) {
        progressBar.visibility =
            View.GONE
        controllerNameEditText.setText(
            controller.name
        )
        MACAddressEditText.setText(
            controller.mac
        )
        controllerSwitch.isChecked =
            controller.status
    }

    override fun onGetControllerResponseFailure(errorModel: ErrorModel) {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            errorModel.message,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetControllerCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetControllerFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the Internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_controller_settings
        )

        val app: MainApplication =
            application as MainApplication

        val controllerHelper =
            ControllerHelper(
                app.getApi()
            )

        val id =
            intent.getIntExtra(
                "id",
                0
            )

        if (id == 0) {
            buttonDelete.visibility =
                View.INVISIBLE
            progressBar.visibility =
                View.GONE
        } else {
            controllerHelper.getController(
                id,
                this
            )
        }

        buttonSave.setOnClickListener {
            if (validateAll()) {
                //send data to server
                val controllerData =
                    ControllerModel(
                        id,
                        controllerNameEditText.text.toString(),
                        MACAddressEditText.text.toString(),
                        controllerSwitch.isChecked
                    )
                if (id == 0) {
                    controllerHelper.addController(
                        controllerData,
                        this
                    )
                } else {
                    controllerHelper.editController(
                        controllerData,
                        this
                    )
                }
            }
        }

        buttonBack.setOnClickListener {
            finish()
        }

        buttonDelete.setOnClickListener {
            controllerHelper.deleteController(
                id,
                this
            )
        }
    }

    private fun validateAll(): Boolean =
        validateName() && validateMAC()

    private fun validateName(): Boolean =
        when {
            controllerNameEditText.text.toString().isEmpty() -> {
                controllerNameTextInput.error =
                    "Name field shouldn't be empty"
                false
            }
            else -> true
        }

    private fun validateMAC(): Boolean =
        when {
            MACAddressEditText.text.toString().isEmpty() -> {
                MACAddressTextInput.error =
                    "MAC Address field shouldn't be empty"
                false
            }
            MACAddressEditText.text.toString().trim().length != 12 -> {
                MACAddressTextInput.error =
                    "MAC Address should be 12 or 16 characters long"
                false
            }
            else -> true
        }
}
