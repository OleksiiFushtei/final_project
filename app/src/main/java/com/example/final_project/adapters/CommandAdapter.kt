package com.example.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.CommandSettingsActivity
import com.example.final_project.R
import com.example.final_project.models.CommandModel
import kotlinx.android.synthetic.main.command_row.view.*

class CommandAdapter(
    private val items: ArrayList<CommandModel>,
    private val context: Context?,
    private val scriptId: Int?
) : RecyclerView.Adapter<ViewHolderCommand>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderCommand {
        return ViewHolderCommand(
            LayoutInflater.from(
                context
            ).inflate(
                R.layout.command_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: ViewHolderCommand,
        position: Int
    ) {
        holder.commandRow.commandName.text =
            items[position].name
        holder.commandRow.imageSettings.setOnClickListener {
            val commandSettingsIntent =
                Intent(
                    context,
                    CommandSettingsActivity::class.java
                )
            commandSettingsIntent.putExtra(
                "scriptId",
                scriptId
            )
            commandSettingsIntent.putExtra(
                "commandId",
                items[position].id
            )
            context?.startActivity(
                commandSettingsIntent
            )
        }
    }


}

class ViewHolderCommand(
    view: View
) : RecyclerView.ViewHolder(
    view
) {
    val commandRow: ConstraintLayout =
        view.commandRow
}