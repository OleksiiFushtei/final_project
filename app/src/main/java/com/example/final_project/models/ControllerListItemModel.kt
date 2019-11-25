package com.example.final_project.models

import com.example.final_project.models.interfaces.IControllerListItemModel
import com.google.gson.annotations.SerializedName

data class ControllerListItemModel(
    @SerializedName(
        "id"
    )
    override val id: Int,
    @SerializedName(
        "isAdmin"
    )
    override val isAdmin: Boolean,
    @SerializedName(
        "controller"
    )
    override val controller: ControllerModel,
    @SerializedName(
        "user"
    )
    override val user: UserModel

) : IControllerListItemModel