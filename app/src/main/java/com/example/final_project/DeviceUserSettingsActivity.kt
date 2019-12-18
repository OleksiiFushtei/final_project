package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.final_project.api.helpers.DeviceAccessHelper
import com.example.final_project.api.helpers.DeviceHelper
import com.example.final_project.api.interfaces.DeviceAccessInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ErrorModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_device_user_settings.*
import kotlinx.android.synthetic.main.fragment_device_users.*
import kotlinx.android.synthetic.main.fragment_device_users.progressBar
import kotlinx.android.synthetic.main.fragment_device_users.root_layout

class DeviceUserSettingsActivity :
    AppCompatActivity(),
    DeviceAccessInterface.DeleteUserFromListListener {

    override fun onDeleteUserFromListResponseSuccess() {
        finish()
    }

    override fun onDeleteUserFromListResponseFailure(
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

    override fun onDeleteUserFromListCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onDeleteUserFromListFailure() {
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
            R.layout.activity_device_user_settings
        )

        progressBar.visibility =
            View.GONE
        val id =
            intent.getIntExtra(
                "id",
                0
            )
        val username =
            intent.getStringExtra(
                "deviceUserUserName"
            )
        val name =
            intent.getStringExtra(
                "deviceUserName"
            )
        val surname =
            intent.getStringExtra(
                "deviceUserSurname"
            )
        val email =
            intent.getStringExtra(
                "deviceUserEmail"
            )
        userUserNameTextView.text =
            username
        nameEditText.setText(
            name
        )
        nameEditText.keyListener =
            null
        surnameEditText.setText(
            surname
        )
        surnameEditText.keyListener =
            null
        emailEditText.setText(
            email
        )
        emailEditText.keyListener =
            null
        val app =
            application as MainApplication
        val deviceAccessHelper =
            DeviceAccessHelper(
                app.getApi()
            )
        buttonDelete.setOnClickListener {
            progressBar.visibility =
                View.VISIBLE
            deviceAccessHelper.deleteUser(
                id,
                this
            )
        }
    }
}
