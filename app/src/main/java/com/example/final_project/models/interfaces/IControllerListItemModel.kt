package com.example.final_project.models.interfaces

import com.example.final_project.models.ControllerModel
import com.example.final_project.models.UserModel

interface IControllerListItemModel {
    val id: Int
    val isAdmin: Boolean
    val controller: ControllerModel
    val user: UserModel
}