package com.example.final_project.models

import com.example.final_project.models.interfaces.IUserModel
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName(
        "email"
    )
    override val email: String,
    @SerializedName(
        "name"
    )
    override val name: String,
    @SerializedName(
        "surname"
    )
    override val surname: String,
    @SerializedName(
        "userName"
    )
    override val userName: String
) : IUserModel