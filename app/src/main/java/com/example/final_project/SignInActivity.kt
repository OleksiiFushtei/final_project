package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.final_project.models.SignInDataModel
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (supportActionBar != null) supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        usernameEditText.setOnFocusChangeListener { _, b -> validateUsername(b)}
        passwordEditText.setOnFocusChangeListener { _, b -> validatePassword(b) }

        buttonSI.setOnClickListener {
            val checkData = SignInDataModel(usernameEditText.text.toString(), passwordEditText.text.toString())
            //send it to server
            Toast.makeText(this@SignInActivity, checkData.username + " " + checkData.password, Toast.LENGTH_LONG).show()
        }

        buttonSU.setOnClickListener {
            val signUpIntent = Intent(this@SignInActivity,SignUpActivity::class.java)
            startActivity(signUpIntent)
        }

        buttonFP.setOnClickListener {
            //maybe? idk
        }

    }

    private fun validateUsername(b: Boolean) {
        if (usernameEditText.text.isNullOrEmpty() && !b) {
            Toast.makeText(this@SignInActivity, "Username field shouldn't be empty", Toast.LENGTH_LONG).show()
        } else if (usernameEditText.text.isNotEmpty() && !b && usernameEditText.text.toString().length < 3) {
           Toast.makeText(this@SignInActivity, "Username must be at least 3 characters long", Toast.LENGTH_LONG).show()
        } else {
            //confirm the data is OK somehow
        }
    }

    private fun validatePassword(b: Boolean) {
        if (passwordEditText.text.isNullOrEmpty() && !b) {
            Toast.makeText(this@SignInActivity, "Password field shouldn't be empty", Toast.LENGTH_LONG).show()
        } else if (passwordEditText.text.toString().isNotEmpty() && !b && passwordEditText.text.toString().length < 8) {
            Toast.makeText(this@SignInActivity, "Password must be at least 6 characters long", Toast.LENGTH_LONG).show()
        } else {
            //confirm the data is OK somehow
        }
    }

}
