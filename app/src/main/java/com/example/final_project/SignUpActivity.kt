package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns.*
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.models.SignUpDataModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity :
    AppCompatActivity() {

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

        val retrofit =
            Retrofit.Builder()
                .baseUrl(
                    "localhost:5000"
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .build()

        val apiInterface =
            retrofit.create(
                ApiInterface::class.java
            )

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
                //Retrofit part here
                //TODO implement @PUT method 'registerUser'
                val call =
                    apiInterface.registerUser(
                        checkData
                    )
                call.enqueue(
                    object :
                        Callback<SignUpDataModel> {
                        override fun onFailure(
                            call: Call<SignUpDataModel>,
                            t: Throwable
                        ) {
                            if (call.isCanceled) {

                            } else {

                            }
                        }

                        override fun onResponse(
                            call: Call<SignUpDataModel>,
                            response: Response<SignUpDataModel>
                        ) {
                            if (response.isSuccessful) {

                            } else {

                            }
                        }
                    })
                Toast.makeText(
                    this@SignUpActivity,
                    checkData.username + " " + checkData.email + " " + checkData.password + " " + checkData.name + " " + checkData.surname,
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {
                //refuse and show errors
                Toast.makeText(
                    this@SignUpActivity,
                    "Something is not OK",
                    Toast.LENGTH_SHORT
                )
                    .show()
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
        }
    }

    private fun validateAll(): Boolean =
        validateUserName() && validateEmail() && validatePassword() && validateConfPassword() && validateName() && validateSurName()

    private fun validateUserName(): Boolean =
        if (usernameEditText.text.toString().isEmpty()) {
            usernameEditText.error =
                "Username field shouldn't be empty"
            false
        } else {
            true
        }

    private fun validateEmail(): Boolean =
        if (emailEditText.text.toString().isEmpty()) {
            emailEditText.error =
                "Email field shouldn't be empty"
            false
        } else {
            if (EMAIL_ADDRESS.matcher(
                    emailEditText.text.toString().trim()
                ).matches()
            ) {
                true
            } else {
                emailEditText.error =
                    "Invalid email address"
                false
            }
        }

    private fun validatePassword(): Boolean =
        if (passwordEditText.text.toString().isEmpty()) {
            passwordEditText.error =
                "Password field shouldn't be empty"
            false
        } else {
            if (passwordEditText.text.toString().length < 6) {
                passwordEditText.error =
                    "Password must be at least 6 characters long"
                false
            } else {
                true
            }
        }

    private fun validateConfPassword(): Boolean =
        when {
            confPasswordEditText.text.toString().isEmpty() -> {
                confPasswordEditText.error =
                    "Confirm password field shouldn't be empty"
                false
            }
            passwordEditText.text.toString() != confPasswordEditText.text.toString() -> {
                confPasswordEditText.error =
                    "Passwords should match"
                false
            }
            else -> true
        }

    private fun validateName(): Boolean =
        if (nameEditText.text.toString().isEmpty()) {
            nameEditText.error =
                "Name field shouldn't be empty"
            false
        } else {
            true
        }

    private fun validateSurName(): Boolean =
        if (surnameEditText.text.toString().isEmpty()) {
            surnameEditText.error =
                "Surname field shouldn't be empty"
            false
        } else {
            true
        }
}
