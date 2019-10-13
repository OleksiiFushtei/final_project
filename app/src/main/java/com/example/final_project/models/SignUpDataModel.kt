package com.example.final_project.models

class SignUpDataModel(override val username: String,
                      override val email: String,
                      override val password: String,
                      override val name: String,
                      override val surname: String
) : ISignUpDataModel
