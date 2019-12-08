package com.example.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.ScriptSettingsActivity
import com.example.final_project.models.ScriptModel
import kotlinx.android.synthetic.main.script_row.view.*

class ScriptAdapter(
    private val items: ArrayList<ScriptModel>,
    private val context: Context?,
    private val controllerId: Int?,
    private val isAdmin: Boolean?
) : RecyclerView.Adapter<ViewHolderScript>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderScript {
        return ViewHolderScript(
            LayoutInflater.from(
                context
            ).inflate(
                R.layout.script_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: ViewHolderScript,
        position: Int
    ) {
        holder.scriptRow.scriptName.text =
            items[position].name
        holder.scriptRow.imageSettings.setOnClickListener {
            val scriptSettingsIntent =
                Intent(
                    context,
                    ScriptSettingsActivity::class.java
                )
            scriptSettingsIntent.putExtra(
                "controllerId",
                controllerId
            )
            scriptSettingsIntent.putExtra(
                "scriptId",
                items[position].id
            )
            scriptSettingsIntent.putExtra(
                "scriptName",
                items[position].name
            )
            context?.startActivity(
                scriptSettingsIntent
            )
        }
    }

}

class ViewHolderScript(
    view: View
) : RecyclerView.ViewHolder(
    view
) {
    val scriptRow: ConstraintLayout =
        view.scriptRow
}