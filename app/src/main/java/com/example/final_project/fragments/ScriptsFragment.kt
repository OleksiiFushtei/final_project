package com.example.final_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.R
import com.example.final_project.ScriptSettingsActivity
import com.example.final_project.adapters.ScriptAdapter
import com.example.final_project.api.helpers.ScriptsListHelper
import com.example.final_project.api.interfaces.ScriptsListInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ErrorModel
import com.example.final_project.models.ScriptModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_device_settings.progressBar
import kotlinx.android.synthetic.main.fragment_script.*

class ScriptsFragment :
    Fragment(),
    ScriptsListInterface.ScriptsListListener {

    companion object {
        private const val ID =
            "id"
        private const val ISADMIN =
            "isAdmin"

        fun newInstance(
            caughtId: Int,
            caughtIsAdmin: Boolean
        ): ScriptsFragment {
            val args =
                Bundle()
            args.putSerializable(
                ID,
                caughtId
            )
            args.putSerializable(
                ISADMIN,
                caughtIsAdmin
            )
            val fragment =
                ScriptsFragment()
            fragment.arguments =
                args
            return fragment
        }
    }

    override fun onGetScriptsListResponseSuccess(
        list: ArrayList<ScriptModel>
    ) {
        val bundle =
            this.arguments
        val controllerId =
            bundle?.getInt(
                "id",
                0
            )
        val isAdmin =
            bundle?.getBoolean(
                "isAdmin",
                false
            )
        progressBar?.visibility =
            View.GONE
        listOfScripts.layoutManager =
            LinearLayoutManager(
                context
            )
        listOfScripts.adapter =
            ScriptAdapter(
                list,
                context,
                controllerId = controllerId,
                isAdmin = isAdmin
            )
        scriptsList.addAll(
            list
        )
        when {
            list.isEmpty() -> {
                listOfScripts.visibility =
                    View.GONE
                emptyList.visibility =
                    View.VISIBLE
            }
            else -> {
                listOfScripts.visibility =
                    View.VISIBLE
                emptyList.visibility =
                    View.GONE
            }
        }
    }

    override fun onGetScriptsListResponseFailure(
        errorModel: ErrorModel
    ) {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            errorModel.message,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetScriptsListCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetScriptsListFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    private val scriptsList: ArrayList<ScriptModel> =
        ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(
                R.layout.fragment_script,
                container,
                false
            )
        val bundle =
            this.arguments
        val controllerId =
            bundle?.getInt(
                "id",
                0
            )
        val app =
            context?.applicationContext as MainApplication
        val progressBar =
            view.findViewById<ProgressBar>(
                R.id.progressBar
            )
        progressBar?.visibility =
            View.VISIBLE
        val scriptsListHelper =
            ScriptsListHelper(
                app.getApi()
            )
        if (controllerId != null) {
            scriptsListHelper.getListOfScripts(
                controllerId,
                this
            )
        }
        val addButton =
            view.findViewById<FloatingActionButton>(
                R.id.addScriptButton
            )
        addButton?.setOnClickListener {
            val addScript =
                Intent(
                    this.activity,
                    ScriptSettingsActivity::class.java
                )
            addScript.putExtra(
                "controllerId",
                controllerId
            )
            addScript.putExtra(
                "scriptId",
                0
            )
            startActivity(
                addScript
            )
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        val bundle =
            this.arguments
        val controllerId =
            bundle?.getInt(
                "id",
                0
            )
        val app =
            context?.applicationContext as MainApplication
        val progressBar =
            view?.findViewById<ProgressBar>(
                R.id.progressBar
            )
        progressBar?.visibility =
            View.VISIBLE
        val scriptsListHelper =
            ScriptsListHelper(
                app.getApi()
            )
        if (controllerId != null) {
            scriptsListHelper.getListOfScripts(
                controllerId,
                this
            )
        }
    }
}
