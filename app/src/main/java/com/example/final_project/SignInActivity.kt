package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.final_project.api.helpers.LoginHelper
import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.LoginInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.SignInDataModel
import com.example.final_project.models.TokenModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignInActivity :
    AppCompatActivity(),
    LoginInterface.LoginListener {
    override fun onLoginResponseSuccess(
        token: TokenModel
    ) {
        Hawk.put(
            "token",
            token.token
        )
        val mainActivity =
            Intent(
                this@SignInActivity,
                MainActivity::class.java
            )
        startActivity(
            mainActivity
        )
    }

    override fun onLoginResponseFailure() {
        Toast.makeText(
            this@SignInActivity,
            "Username or password is not correct",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun onLoginCancelled() {
        Toast.makeText(
            this@SignInActivity,
            "Login Cancelled",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun onLoginFailure() {
        Toast.makeText(
            this@SignInActivity,
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
            R.layout.activity_sign_in
        )

        val app: MainApplication =
            application as MainApplication

        val loginHelper =
            LoginHelper(
                app.getApi()
            )

        buttonSI.setOnClickListener {
            if (validateAll()) {
                //send data to server
                val checkData =
                    SignInDataModel(
                        usernameEditText.text.toString(),
                        passwordEditText.text.toString(),
                        "password"
                    )
                loginHelper.login(
                    checkData,
                    this
                )
            }
        }

        buttonSU.setOnClickListener {
            val signUpIntent =
                Intent(
                    this@SignInActivity,
                    SignUpActivity::class.java
                )
            startActivity(
                signUpIntent
            )
        }

        buttonFP.setOnClickListener {
            //maybe? idk
        }


    }

    private fun validateAll(): Boolean =
        validateUserName() && validatePassword()

    private fun validateUserName(): Boolean =
        when {
            usernameEditText.text.toString().isEmpty() -> {
                usernameEditText.error =
                    "Username field shouldn't be empty"
                false
            }
            else -> true
        }

    private fun validatePassword(): Boolean =
        when {
            passwordEditText.text.toString().isEmpty() -> {
                passwordEditText.error =
                    "Password field shouldn't be empty"
                false
            }
            passwordEditText.text.toString().length < 6 -> {
                passwordEditText.error =
                    "Password must be at least 6 characters long"
                false
            }
            else -> true
        }
}

