package com.example.final_project.models.interfaces

import com.example.final_project.models.ControllerModel

interface IControllerListItemModel {
    val isAdmin: Boolean
    val controller: ControllerModel
}