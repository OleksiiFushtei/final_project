package com.example.final_project.api.interfaces

import com.example.final_project.models.CommandModel
import com.example.final_project.models.ErrorModel

interface CommandInterface {

    interface CommandAddInterface {
        fun addCommand(
            commandModel: CommandModel,
            commandSaveListener: CommandSaveListener
        )
    }

    interface CommandEditInterface {
        fun editCommand(
            commandModel: CommandModel,
            commandSaveListener: CommandSaveListener
        )
    }

    interface CommandGetInterface {
        fun getCommand(
            id: Int,
            commandGetListener: CommandGetListener
        )
    }

    interface CommandDeleteInterface {
        fun deleteCommand(
            id: Int,
            commandDeleteListener: CommandDeleteListener
        )
    }

    interface CommandSaveListener {
        fun onCommandSaveResponseSuccess()
        fun onCommandSaveResponseFailure(
            errorModel: ErrorModel?
        )

        fun onCommandSaveCancelled()
        fun onCommandSaveFailure()
    }

    interface CommandGetListener {
        fun onCommandGetResponseSuccess(
            commandModel: CommandModel
        )

        fun onCommandGetResponseFailure(
            errorModel: ErrorModel?
        )

        fun onCommandGetCancelled()
        fun onCommandGetFailure()
    }

    interface CommandDeleteListener {
        fun onCommandDeleteResponseSuccess()
        fun onCommandDeleteResponseFailure(
            errorModel: ErrorModel?
        )

        fun onCommandDeleteCancelled()
        fun onCommandDeleteFailure()
    }
}