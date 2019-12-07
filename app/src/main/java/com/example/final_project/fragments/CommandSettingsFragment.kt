package com.example.final_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.final_project.R

class CommandSettingsFragment :
    Fragment() {

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
