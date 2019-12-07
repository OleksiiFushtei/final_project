package com.example.final_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.final_project.R

class CommandConfigFragment :
    Fragment() {

    companion object {
        private const val SCRIPTID =
            "scriptId"
        private const val COMMANDID =
            "commandId"

        fun newInstance(
            caughtScriptId: Int,
            caughtCommandId: Int
        ): CommandConfigFragment {
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
                CommandConfigFragment()
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
                R.layout.fragment_command_config,
                container,
                false
            )
        return view
    }


}
