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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_script.progressBar
import kotlinx.android.synthetic.main.fragment_script_commands.*

class ScriptCommandsFragment :
    Fragment(),
    CommandsListInterface.CommandsListListener {

    companion object {
        private const val CONTROLLERID =
            "controllerId"
        private const val SCRIPTID =
            "scriptId"

        fun newInstance(
            caughtId: Int,
            caughtScriptId: Int
        ): ScriptCommandsFragment {
            val args =
                Bundle()
            args.putSerializable(
                CONTROLLERID,
                caughtId
            )
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
        commandsList.clear()
        commandsList.addAll(
            list
        )
        val bundle =
            this.arguments
        val scriptId =
            bundle?.getInt(
                "scriptId",
                0
            )
        val controllerId =
            bundle?.getInt(
                "controllerId",
                0
            )
        progressBar?.visibility =
            View.GONE
        val llm =
            LinearLayoutManager(
                this.context
            )
        llm.orientation =
            LinearLayoutManager.VERTICAL
        listOfCommands.layoutManager =
            llm
        val commandAdapter =
            CommandAdapter(
                commandsList,
                context = context,
                controllerId = controllerId,
                scriptId = scriptId
            )
        listOfCommands.adapter =
            commandAdapter
        when {
            commandsList.isEmpty() -> {
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
        errorModel: ErrorModel?
    ) {
        val errorMessage =
            when (errorModel) {
                null -> "Server error"
                else -> errorModel.message
            }
        Snackbar.make(
            root_layout,
            errorMessage,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetCommandsListCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetCommandsListFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
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
        val controllerId =
            bundle?.getInt(
                "controllerId",
                0
            )
        val scriptId =
            bundle?.getInt(
                "scriptId",
                0
            )
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
                "controllerId",
                controllerId
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

    override fun onResume() {
        super.onResume()
        val bundle =
            this.arguments
        val scriptId =
            bundle?.getInt(
                "scriptId",
                0
            )
        val app =
            context?.applicationContext as MainApplication
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
    }
}
