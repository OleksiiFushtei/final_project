package com.example.final_project.models

import com.google.gson.annotations.SerializedName

class SignUpDataModel(@SerializedName("userName") override val username: String,
                      @SerializedName("email") override val email: String,
                      @SerializedName("password") override val password: String,
                      @SerializedName("name") override val name: String,
                      @SerializedName("surname") override val surname: String
) : ISignUpDataModel
