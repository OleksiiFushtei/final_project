package com.example.final_project.models

import com.example.final_project.models.interfaces.ISignUpDataModel
import com.google.gson.annotations.SerializedName

data class SignUpDataModel(
    @SerializedName(
        "userName"
    ) override val username: String,
    @SerializedName(
        "email"
    ) override val email: String,
    @SerializedName(
        "password"
    ) override val password: String,
    @SerializedName(
        "name"
    ) override val name: String,
    @SerializedName(
        "surname"
    ) override val surname: String
) : ISignUpDataModel
