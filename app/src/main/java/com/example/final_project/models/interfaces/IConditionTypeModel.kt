package com.example.final_project.models.interfaces

import com.example.final_project.models.ScriptModel

interface IConditionTypeModel {
    val id: Int
    val type: String
    val scripts: ArrayList<ScriptModel>
}