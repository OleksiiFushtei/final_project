package com.example.final_project.api.interfaces

import com.example.final_project.models.CommandModel
import com.example.final_project.models.ErrorModel

interface CommandsListInterface {
    fun getListOfCommands(
        id: Int,
        commandsListListener: CommandsListListener
    )

    interface CommandsListListener {
        fun onGetCommandsListResponseSuccess(
            list: ArrayList<CommandModel>
        )

        fun onGetCommandsListResponseFailure(
            errorModel: ErrorModel?
        )

        fun onGetCommandsListCancelled()
        fun onGetCommandsListFailure()
    }
}