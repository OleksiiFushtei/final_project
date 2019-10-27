package com.example.final_project.models

import com.example.final_project.models.interfaces.IControllerModel

data class ControllerModel(
    override val id: Int,
    override val name: String,
    override val mac: String,
    override val status: Boolean
) : IControllerModel