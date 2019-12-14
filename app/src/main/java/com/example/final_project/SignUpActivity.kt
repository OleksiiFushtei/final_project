package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns.*
import android.view.View
import com.example.final_project.api.helpers.SignUpHelper
import com.example.final_project.api.interfaces.SignUpInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ErrorModel
import com.example.final_project.models.SignUpDataModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity :
    AppCompatActivity(),
    SignUpInterface.SignUpListener {
    override fun onSignUpResponseSuccess() {
        val signInIntent =
            Intent(
                this@SignUpActivity,
                SignInActivity::class.java
            )
        startActivity(
            signInIntent
        )
        finish()
    }

    override fun onSignUpResponseFailure(
        errorModel: ErrorModel
    ) {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            errorModel.message,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onSignUpCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Sign up cancelled. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onSignUpFailure() {
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
        if (supportActionBar != null) supportActionBar?.hide()
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_sign_up
        )
        val app: MainApplication =
            application as MainApplication
        val signUpHelper =
            SignUpHelper(
                app.getApi()
            )
        progressBar.visibility =
            View.GONE
        buttonSU.setOnClickListener {
            //do final check
            if (validateAll()) {
                //send to server
                val checkData =
                    SignUpDataModel(
                        usernameEditText.text.toString(),
                        emailEditText.text.toString(),
                        passwordEditText.text.toString(),
                        nameEditText.text.toString(),
                        surnameEditText.text.toString()
                    )

                progressBar.visibility =
                    View.VISIBLE

                signUpHelper.signUp(
                    checkData,
                    this
                )
            }
        }
        buttonBack.setOnClickListener {
            val signInIntent =
                Intent(
                    this@SignUpActivity,
                    SignInActivity::class.java
                )
            startActivity(
                signInIntent
            )
            finish()
        }
    }

    private fun validateAll(): Boolean {
        usernameTextInput.error =
            null
        emailTextInput.error =
            null
        passwordTextInput.error =
            null
        confPasswordTextInput.error =
            null
        nameTextInput.error =
            null
        surnameTextInput.error =
            null
        return validateUserName() && validateEmail() && validatePassword() && validateConfPassword() && validateName() && validateSurName()
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

    private fun validateEmail(): Boolean =
        when {
            emailEditText.text.toString().isEmpty() -> {
                emailTextInput.error =
                    "Email field shouldn't be empty"
                false
            }
            EMAIL_ADDRESS.matcher(
                emailEditText.text.toString().trim()
            ).matches() -> {
                emailTextInput.error =
                    null
                true
            }
            else -> {
                emailTextInput.error =
                    "Invalid email address"
                false
            }
        }

    private fun validatePassword(): Boolean =
        when {
            passwordEditText.text.toString().isEmpty() -> {
                passwordTextInput.error =
                    "Password field shouldn't be empty"
                false
            }
            passwordEditText.text.toString().length < 6 -> {
                passwordTextInput.error =
                    "Password must be at least 6 characters long"
                false
            }
            else -> {
                passwordTextInput.error =
                    null
                true
            }
        }


    private fun validateConfPassword(): Boolean =
        when {
            confPasswordEditText.text.toString().isEmpty() -> {
                confPasswordTextInput.error =
                    "Confirm password field shouldn't be empty"
                false
            }
            passwordEditText.text.toString() != confPasswordEditText.text.toString() -> {
                confPasswordTextInput.error =
                    "Passwords should match"
                false
            }
            else -> {
                confPasswordTextInput.error =
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

    private fun validateSurName(): Boolean =
        when {
            surnameEditText.text.toString().isEmpty() -> {
                surnameTextInput.error =
                    "Surname field shouldn't be empty"
                false
            }
            else -> {
                surnameTextInput.error =
                    null
                true
            }
        }
}
