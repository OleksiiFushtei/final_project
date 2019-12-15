package com.example.final_project.api.interfaces

import com.example.final_project.models.ErrorModel
import com.example.final_project.models.ScriptModel

interface ScriptsListInterface {
    fun getListOfScripts(
        id: Int,
        scriptsListListener: ScriptsListListener
    )

    interface ScriptsListListener {
        fun onGetScriptsListResponseSuccess(
            list: ArrayList<ScriptModel>
        )

        fun onGetScriptsListResponseFailure(
            errorModel: ErrorModel?
        )

        fun onGetScriptsListCancelled()
        fun onGetScriptsListFailure()
    }
}