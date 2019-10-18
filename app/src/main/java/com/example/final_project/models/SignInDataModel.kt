package com.example.final_project.models

import com.google.gson.annotations.SerializedName

class SignInDataModel(@SerializedName("userName") override val username: String,
                      @SerializedName("password") override val password: String
) : ISignInDataModel