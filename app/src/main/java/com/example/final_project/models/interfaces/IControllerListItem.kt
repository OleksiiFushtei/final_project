package com.example.final_project.models.interfaces

import com.example.final_project.models.ControllerModel

interface IControllerListItem {
    val isAdmin: Boolean
    val controller: ControllerModel
}