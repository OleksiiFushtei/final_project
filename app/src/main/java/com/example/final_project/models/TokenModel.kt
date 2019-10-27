package com.example.final_project.models

import com.example.final_project.models.interfaces.ITokenModel
import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName(
        "token"
    ) override val token: String,
    @SerializedName(
        "expiration"
    ) override val exp: Int
) : ITokenModel
