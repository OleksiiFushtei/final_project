package com.example.final_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.final_project.R
import com.example.final_project.api.interfaces.CommandInterface
import com.example.final_project.models.CommandModel
import com.example.final_project.models.ErrorModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_command_settings.*

class CommandSettingsFragment :
    Fragment(),
    CommandInterface.CommandSaveListener,
    CommandInterface.CommandGetListener,
    CommandInterface.CommandDeleteListener {

    companion object {
        private const val SCRIPTID =
            "scriptId"
        private const val COMMANDID =
            "commandId"

        fun newInstance(
            caughtScriptId: Int,
            caughtCommandId: Int
        ): CommandSettingsFragment {
            val args =
                Bundle()
            args.putSerializable(
                SCRIPTID,
                caughtScriptId
            )
            args.putSerializable(
                COMMANDID,
                caughtCommandId
            )
            val fragment =
                CommandSettingsFragment()
            fragment.arguments =
                args
            return fragment
        }
    }

    override fun onCommandSaveResponseSuccess() {
        activity?.finish()
    }

    override fun onCommandSaveResponseFailure(
        errorModel: ErrorModel?
    ) {

    }

    override fun onCommandSaveCancelled() {

    }

    override fun onCommandSaveFailure() {

    }

    override fun onCommandGetResponseSuccess(
        commandModel: CommandModel
    ) {

    }

    override fun onCommandGetResponseFailure(
        errorModel: ErrorModel?
    ) {
        val message =
            when (errorModel) {
                null -> {
                    "Server error"
                }
                else -> {
                    errorModel.message
                }
            }
        Snackbar.make(
            root_layout,
            message,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCommandGetCancelled() {

    }

    override fun onCommandGetFailure() {

    }

    override fun onCommandDeleteResponseSuccess() {
        activity?.finish()
    }

    override fun onCommandDeleteResponseFailure(
        errorModel: ErrorModel?
    ) {
        val message =
            when (errorModel) {
                null -> {
                    "Server error"
                }
                else -> {
                    errorModel.message
                }
            }
        Snackbar.make(
            root_layout,
            message,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCommandDeleteCancelled() {

    }

    override fun onCommandDeleteFailure() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(
                R.layout.fragment_command_settings,
                container,
                false
            )
        return view
    }

}
