package com.example.final_project.models

import com.example.final_project.models.interfaces.IErrorModel
import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName(
        "statusCode"
    )
    override val statusCode: Int,
    @SerializedName(
        "statusDescription"
    )
    override val statusDescription: String,
    @SerializedName(
        "message"
    )
    override val message: String
) : IErrorModel
