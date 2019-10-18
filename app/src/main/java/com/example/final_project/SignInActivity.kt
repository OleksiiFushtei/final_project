package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.models.SignInDataModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        val retrofit = Retrofit.Builder()
            .baseUrl("localhost:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiInterface = retrofit.create(ApiInterface::class.java)

        buttonSI.setOnClickListener {
            if (validateAll()) {
                //send data to server
                val checkData = SignInDataModel(usernameEditText.text.toString(), passwordEditText.text.toString())
                //Retrofit part
                //TODO implement login
                val call = apiInterface.login(checkData)
                call.enqueue(object: Callback<SignInDataModel> {
                    override fun onFailure(
                        call: Call<SignInDataModel>,
                        t: Throwable
                    ) {
                        TODO(
                            "not implemented"
                        ) //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(
                        call: Call<SignInDataModel>,
                        response: Response<SignInDataModel>
                    ) {
                        TODO(
                            "not implemented"
                        ) //To change body of created functions use File | Settings | File Templates.
                    }
                })

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
