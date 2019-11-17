package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.final_project.api.helpers.SignInHelper
import com.example.final_project.api.interfaces.SignInInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ErrorModel
import com.example.final_project.models.SignInDataModel
import com.example.final_project.models.TokenModel
import com.google.android.material.snackbar.Snackbar
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.buttonFP
import kotlinx.android.synthetic.main.activity_sign_in.buttonSI
import kotlinx.android.synthetic.main.activity_sign_in.buttonSU
import kotlinx.android.synthetic.main.activity_sign_in.passwordEditText
import kotlinx.android.synthetic.main.activity_sign_in.usernameEditText

class SignInActivity :
    AppCompatActivity(),
    SignInInterface.SignInListener {

    override fun onSignInResponseSuccess(
        token: TokenModel
    ) {
        Hawk.put(
            "token",
            token.token
        )
        val mainActivity =
            Intent(
                this@SignInActivity,
                ListOfControllersActivity::class.java
            )
        startActivity(
            mainActivity
        )
        finish()
    }

    override fun onSignInResponseFailure(
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

    override fun onSignInCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Sign in cancelled. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onSignInFailure() {
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
            R.layout.activity_sign_in
        )

        val app: MainApplication =
            application as MainApplication

        val signInHelper =
            SignInHelper(
                app.getApi()
            )

        progressBar.visibility =
            View.GONE

        buttonSI.setOnClickListener {
            if (validateAll()) {
                //send data to server
                progressBar.visibility =
                    View.VISIBLE
                val checkData =
                    SignInDataModel(
                        usernameEditText.text.toString(),
                        passwordEditText.text.toString(),
                        "password"
                    )
                signInHelper.signIn(
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
                usernameTextInput.error =
                    "Username field shouldn't be empty"
                false
            }
            else -> true
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
}

