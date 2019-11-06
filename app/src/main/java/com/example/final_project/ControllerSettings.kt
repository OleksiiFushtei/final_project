package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.final_project.api.helpers.ControllerGetHelper
import com.example.final_project.api.interfaces.ControllerGetInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ControllerModel
import kotlinx.android.synthetic.main.activity_controller_settings.*
import kotlinx.android.synthetic.main.controller_row.*

class ControllerSettings :
    AppCompatActivity(),
    ControllerGetInterface.ControllerGetListener {
    override fun onGetControllerResponseSuccess(
        controller: ControllerModel
    ) {
        controllerNameEditText.setText(
            controller.name
        )
        MACAddressEditText.setText(
            controller.mac
        )
        controllerSwitch.isChecked =
            controller.status
    }

    override fun onGetControllerResponseFailure() {
    }

    override fun onGetControllerCancelled() {
    }

    override fun onGetControllerFailure() {
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

        val controllersGetHelper =
            ControllerGetHelper(
                app.getApi()
            )

        val id =
            intent.getIntExtra(
                "id",
                -1
            )

        if (id == -1) {
            buttonDelete.visibility =
                View.INVISIBLE
        } else {
            controllersGetHelper.getController(
                id,
                this
            )
        }

        buttonSave.setOnClickListener {
            if (validateAll()) {
                //send data to server
                val controllerData = ControllerModel(
                    id, controllerNameEditText.text.toString(), MACAddressEditText.text.toString(), controllerSwitch.isChecked
                )
                if (id == -1) {
                    //TODO implement with listener
                    app.getApi().addController(controllerData)
                } else {
                    //TODO implement with listener
                    app.getApi().saveController(controllerData)
                }
                finish()
            }
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
            MACAddressEditText.text.toString().trim().length != 12 && MACAddressEditText.text.toString().trim().length != 16 -> {
                MACAddressTextInput.error = "MAC Address should be 12 or 16 characters long"
                false
            }
            else -> true
        }
}
