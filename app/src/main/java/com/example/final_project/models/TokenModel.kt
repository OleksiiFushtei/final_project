package com.example.final_project.models

import com.google.gson.annotations.SerializedName

class TokenModel(
    @SerializedName(
        "token"
    ) override val token: String,
    @SerializedName(
        "expiration"
    ) override val exp: Int
) : ITokenModel
