package com.example.final_project.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.CommandSettingsActivity
import com.example.final_project.R
import com.example.final_project.adapters.CommandAdapter
import com.example.final_project.api.helpers.CommandsListHelper
import com.example.final_project.api.interfaces.CommandsListInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.CommandModel
import com.example.final_project.models.ErrorModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_script.progressBar
import kotlinx.android.synthetic.main.fragment_script_commands.*

class ScriptCommandsFragment :
    Fragment(),
    CommandsListInterface.CommandListListener {

    companion object {
        private const val SCRIPTID =
            "scriptId"

        fun newInstance(
            caughtScriptId: Int
        ): ScriptCommandsFragment {
            val args =
                Bundle()
            args.putSerializable(
                SCRIPTID,
                caughtScriptId
            )
            val fragment =
                ScriptCommandsFragment()
            fragment.arguments =
                args
            return fragment
        }
    }

    override fun onGetCommandsListResponseSuccess(
        list: ArrayList<CommandModel>
    ) {
        val bundle =
            this.arguments
        val scriptId =
            bundle?.getInt(
                "scriptId",
                0
            )
        progressBar?.visibility =
            View.GONE
        listOfCommands?.layoutManager =
            LinearLayoutManager(
                context
            )
        listOfCommands?.adapter =
            CommandAdapter(
                list,
                context,
                scriptId = scriptId
            )
        commandsList.addAll(
            list
        )
        when {
            list.isEmpty() -> {
                listOfCommands?.visibility =
                    View.GONE
                emptyListSC?.visibility =
                    View.VISIBLE
            }
            else -> {
                listOfCommands?.visibility =
                    View.VISIBLE
                emptyListSC?.visibility =
                    View.GONE
            }
        }
    }

    override fun onGetCommandsListResponseFailure(
        errorModel: ErrorModel
    ) {

    }

    override fun onGetCommandsListCancelled() {

    }

    override fun onGetCommandsListFailure() {

    }

    private val commandsList: ArrayList<CommandModel> =
        ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(
                R.layout.fragment_script_commands,
                container,
                false
            )
        val bundle =
            this.arguments
        val scriptId =
            bundle?.getInt(
                "scriptId",
                0
            )
        val app =
            context?.applicationContext as MainApplication
        val progressBar =
            view.findViewById<ProgressBar>(
                R.id.progressBar
            )
        val listOfCommands =
            view.findViewById<RecyclerView>(
                R.id.listOfCommands
            )
        val emptyList =
            view.findViewById<TextView>(
                R.id.emptyListSC
            )
        val commandsListHelper =
            CommandsListHelper(
                app.getApi()
            )
        if (scriptId != null && scriptId != 0) {
            progressBar?.visibility =
                View.VISIBLE
            commandsListHelper.getListOfCommands(
                scriptId,
                this
            )
        }
        if (scriptId == 0) {
            progressBar?.visibility =
                View.GONE
            listOfCommands.visibility =
                View.GONE
            emptyList.visibility =
                View.VISIBLE
        }
        val addButton =
            view.findViewById<FloatingActionButton>(
                R.id.addCommandButton
            )
        addButton.setOnClickListener {
            val addCommand =
                Intent(
                    this.activity,
                    CommandSettingsActivity::class.java
                )
            addCommand.putExtra(
                "scriptId",
                scriptId
            )
            addCommand.putExtra(
                "commandId",
                0
            )
            startActivity(
                addCommand
            )
        }
        return view
    }


}
