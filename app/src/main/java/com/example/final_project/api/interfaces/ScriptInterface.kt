package com.example.final_project.api.interfaces

import com.example.final_project.models.ErrorModel
import com.example.final_project.models.ScriptModel

interface ScriptInterface {

    interface ScriptAddInterface {
        fun addScript(
            scriptModel: ScriptModel,
            scriptSaveListener: ScriptSaveListener
        )
    }

    interface ScriptEditInterface {
        fun editScript(
            scriptModel: ScriptModel,
            scriptSaveListener: ScriptSaveListener
        )
    }

    interface ScriptGetInterface {
        fun getScript(
            id: Int,
            scriptGetListener: ScriptGetListener
        )
    }

    interface ScriptDeleteInterface {
        fun deleteScript(
            id: Int,
            scriptDeleteListener: ScriptDeleteListener
        )
    }

    interface ScriptSaveListener {
        fun onScriptSaveResponseSuccess()
        fun onScriptSaveResponseFailure(
            errorModel: ErrorModel
        )

        fun onScriptSaveCancelled()
        fun onScriptSaveFailure()
    }

    interface ScriptGetListener {
        fun onScriptGetResponseSuccess(
            scriptModel: ScriptModel
        )

        fun onScriptGetResponseFailure(
            errorModel: ErrorModel
        )

        fun onScriptGetCancelled()
        fun onScriptGetFailure()
    }

    interface ScriptDeleteListener {
        fun onScriptDeleteResponseSuccess()
        fun onScriptDeleteResponseFailure(
            errorModel: ErrorModel
        )

        fun onScriptDeleteCancelled()
        fun onScriptDeleteFailure()
    }

}