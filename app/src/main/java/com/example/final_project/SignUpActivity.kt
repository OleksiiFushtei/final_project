package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns.*
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.final_project.api.helpers.SignUpHelper
import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.SignUpInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.SignUpDataModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    override fun onSignUpResponseFailure() {
        progressBar.visibility = View.GONE
        Toast.makeText(
            this@SignUpActivity,
            "Sign Up Response Failure",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun onSignUpCancelled() {
        progressBar.visibility = View.GONE
        Toast.makeText(
            this@SignUpActivity,
            "Sign Up Cancelled",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun onSignUpFailure() {
        progressBar.visibility = View.GONE
        Toast.makeText(
            this@SignUpActivity,
            "Check your confection to the Internet",
            Toast.LENGTH_SHORT
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

    private fun validateAll(): Boolean =
        validateUserName() && validateEmail() && validatePassword() && validateConfPassword() && validateName() && validateSurName()

    private fun validateUserName(): Boolean =
        when {
            usernameEditText.text.toString().isEmpty() -> {
                usernameTextInput.error =
                    "Username field shouldn't be empty"
                false
            }
            !usernameEditText.text.toString().matches(
                Regex(
                    "^[a-z0-9_-]{3,50}\$"
                )
            ) -> {
                usernameTextInput.error =
                    "Username should be 3 to 50 characters long"
                false
            }
            else -> true
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
            ).matches() -> true
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
            else -> true
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
            else -> true
        }

    private fun validateName(): Boolean =
        when {
            nameEditText.text.toString().isEmpty() -> {
                nameTextInput.error =
                    "Name field shouldn't be empty"
                false
            }
            else -> true
        }

    private fun validateSurName(): Boolean =
        when {
            surnameEditText.text.toString().isEmpty() -> {
                surnameTextInput.error =
                    "Surname field shouldn't be empty"
                false
            }
            else -> true
        }
}
