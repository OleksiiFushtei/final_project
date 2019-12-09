package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.final_project.api.helpers.ControllerAccessAddUserHelper
import com.example.final_project.api.interfaces.ControllerAccessAddUserInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ControllerAccessModel
import com.example.final_project.models.ErrorModel
import kotlinx.android.synthetic.main.activity_user_give_access.*

class UserGiveAccessActivity :
    AppCompatActivity(),
    ControllerAccessAddUserInterface.AddUserToListListener {

    override fun onAddUserToListResponseSuccess() {
        progressBar.visibility =
            View.GONE
        finish()
    }

    override fun onAddUserToListResponseFailure(
        errorModel: ErrorModel
    ) {
        progressBar.visibility =
            View.GONE

    }

    override fun onAddUserToListCancelled() {
        progressBar.visibility =
            View.GONE

    }

    override fun onAddUserToListFailure() {
        progressBar.visibility =
            View.GONE

    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_user_give_access
        )

        val controllerId =
            intent.getIntExtra(
                "id",
                0
            )

        val app =
            application as MainApplication
        val controllerAccessAddUserHelper =
            ControllerAccessAddUserHelper(
                app.getApi()
            )
        progressBar.visibility =
            View.GONE

        buttonSave.setOnClickListener {
            if (validateUserName()) {
                progressBar.visibility =
                    View.VISIBLE
                val checkData =
                    ControllerAccessModel(
                        controllerId,
                        usernameEditText.text?.trim().toString()
                    )
                controllerAccessAddUserHelper.addUser(
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
                usernameTextInput.error = null
                true
            }
        }
}