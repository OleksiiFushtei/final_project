package com.example.final_project.models.interfaces

interface IErrorModel {
    val statusCode: Int
    val statusDescription: String
    val message: String
}