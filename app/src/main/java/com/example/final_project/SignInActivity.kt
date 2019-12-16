package com.example.final_project

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.example.final_project.api.helpers.FirebaseHelper
import com.example.final_project.api.helpers.SignInHelper
import com.example.final_project.api.interfaces.FirebaseInteface
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
    SignInInterface.SignInListener,
    FirebaseInteface.PostTokenListener {

    override fun onSignInResponseSuccess(
        token: TokenModel
    ) {
        Hawk.put(
            "token",
            token.token
        )
        val app: MainApplication =
            application as MainApplication
        val firebaseHelper =
            FirebaseHelper(
                app.getApi()
            )
        val mainActivity =
            Intent(
                this@SignInActivity,
                ListOfControllersActivity::class.java
            )
        firebaseHelper.postToken(
            Hawk.get(
                "firebaseToken"
            ),
            this
        )
        startActivity(
            mainActivity
        )
        finish()
    }

    override fun onPostTokenResponseSuccess(
        token: String
    ) {

    }

    override fun onPostTokenResponseFailure(
        errorModel: ErrorModel?
    ) {

    }

    override fun onPostTokenCancelled() {

    }

    override fun onPostTokenFailure() {

    }

    override fun onSignInResponseFailure(
    ) {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Username or password is not correct",
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
        channelSettings()
        if (Hawk.contains(
                "token"
            )
        ) {
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

    private fun channelSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager
            if (notificationManager.notificationChannels.isEmpty()) {
                val channelId =
                    getString(
                        R.string.default_notification_channel_id
                    )
                val channelName: CharSequence =
                    "My channel"
                val importance =
                    NotificationManager.IMPORTANCE_HIGH
                val notificationChannel =
                    NotificationChannel(
                        channelId,
                        channelName,
                        importance
                    )
                notificationChannel.enableLights(
                    true
                )
                notificationChannel.lightColor =
                    Color.RED
                notificationChannel.enableVibration(
                    true
                )
                notificationChannel.vibrationPattern =
                    longArrayOf(
                        100,
                        200,
                        300,
                        400,
                        500,
                        400,
                        300,
                        200,
                        400
                    )
                notificationChannel.lockscreenVisibility =
                    View.VISIBLE
                notificationManager.createNotificationChannel(
                    notificationChannel
                )

                val intent =
                    Intent(
                        Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS
                    )
                intent.putExtra(
                    Settings.EXTRA_CHANNEL_ID,
                    notificationChannel.id
                )
                intent.putExtra(
                    Settings.EXTRA_APP_PACKAGE,
                    packageName
                )
                startActivity(
                    intent
                )
            }
        }
    }

    private fun validateAll(): Boolean {
        usernameTextInput.error =
            null
        passwordTextInput.error =
            null
        return validateUserName() && validatePassword()
    }

    private fun validateUserName(): Boolean =
        when {
            usernameEditText.text.toString().isEmpty() -> {
                usernameTextInput.error =
                    "Username field shouldn't be empty"
                false
            }
            else -> {
                usernameTextInput.error =
                    null
                true
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
}