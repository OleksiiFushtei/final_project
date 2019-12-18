package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.final_project.api.helpers.DeviceAccessAddUserHelper
import com.example.final_project.api.interfaces.DeviceAccessAddUserInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.DeviceAccessModel
import com.example.final_project.models.ErrorModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_device_user_give_access.*
import kotlinx.android.synthetic.main.activity_device_user_give_access.buttonBack
import kotlinx.android.synthetic.main.activity_device_user_give_access.progressBar

class DeviceUserGiveAccessActivity :
    AppCompatActivity(),
    DeviceAccessAddUserInterface.AddUserToListListener {

    override fun onAddUserToListResponseSuccess() {
        finish()
    }

    override fun onAddUserToListResponseFailure(
        errorModel: ErrorModel?
    ) {
        progressBar.visibility =
            View.GONE
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

    override fun onAddUserToListCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onAddUserToListFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
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
            R.layout.activity_device_user_give_access
        )

        val deviceId =
            intent.getIntExtra(
                "deviceId",
                0
            )
        val app =
            application as MainApplication
        val deviceAccessAddUserHelper =
            DeviceAccessAddUserHelper(
                app.getApi()
            )
        progressBar.visibility =
            View.GONE
        buttonSave.setOnClickListener {
            if (validateUserName()) {
                progressBar.visibility =
                    View.VISIBLE
                val checkData =
                    DeviceAccessModel(
                        deviceId,
                        usernameEditText.text?.trim().toString()
                    )
                deviceAccessAddUserHelper.addUser(
                    checkData,
                    this
                )
            }
        }
        buttonBack.setOnClickListener { finish() }
    }

    private fun validateUserName(): Boolean =
        when {
            usernameEditText.text.toString().isEmpty() -> {
                usernameTextInput.error =
                    "Username field shouldn't be empty"
                false
            }
            !usernameEditText.text.toString().matches(
                Regex(
                    "^[a-zA-Z0-9_-]{3,50}\$"
                )
            ) -> {
                usernameTextInput.error =
                    "Username should be 3 to 50 characters long"
                false
            }
            else -> {
                usernameTextInput.error =
                    null
                true
            }
        }
}
