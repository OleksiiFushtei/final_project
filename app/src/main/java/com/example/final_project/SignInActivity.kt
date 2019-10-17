package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.final_project.models.SignInDataModel
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

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

        buttonSI.setOnClickListener {
            if (validateAll()) {
                //send data to server
                val checkData = SignInDataModel(usernameEditText.text.toString(), passwordEditText.text.toString())
                Toast.makeText(this@SignInActivity, checkData.username + " " + checkData.password, Toast.LENGTH_LONG).show()
            } else {
                //refuse and show errors
                Toast.makeText(this@SignInActivity,"Something is not OK", Toast.LENGTH_LONG).show()
            }
        }

        buttonSU.setOnClickListener {
            val signUpIntent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }

        buttonFP.setOnClickListener {
            //maybe? idk
        }

    }

    private fun validateAll(): Boolean = validateUserName() && validatePassword()

    private fun validateUserName(): Boolean =
        if (usernameEditText.text.toString().isEmpty()) {
            usernameEditText.error =
                "Username field shouldn't be empty"
            false
        } else {
            true
        }

    private fun validatePassword(): Boolean =
        if (passwordEditText.text.toString().isEmpty()) {
            passwordEditText.error = "Password field shouldn't be empty"
            false
        } else {
            if (passwordEditText.text.toString().length < 6) {
                passwordEditText.error = "Password must be at least 6 characters long"
                false
            } else {
                true
            }
        }
}
