package com.example.final_project.models.interfaces

interface ISensorModel {
    val id: Int
    val name: String
    val pin: Int
    val status: Boolean
    var value: Double
    val sensorTypeId: Int
    val controllerId: Int
}