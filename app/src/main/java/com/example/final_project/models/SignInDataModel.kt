package com.example.final_project.models

import com.google.gson.annotations.SerializedName

class SignInDataModel(
    @SerializedName(
        "username"
    ) override val username: String,
    @SerializedName(
        "password"
    ) override val password: String,
    @SerializedName(
        "grant_type"
    )
    override val grant_type: String
) : ISignInDataModel