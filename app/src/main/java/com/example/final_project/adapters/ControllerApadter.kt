package com.example.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.ControllerSettingsActivity
import com.example.final_project.MainActivity
import com.example.final_project.R
import com.example.final_project.models.ControllerListItemModel
import kotlinx.android.synthetic.main.controller_row.view.*

class ControllerAdapter(
    private val items: ArrayList<ControllerListItemModel>,
    private val context: Context
) : RecyclerView.Adapter<ViewHolderController>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderController {
        return ViewHolderController(
            LayoutInflater.from(
                context
            ).inflate(
                R.layout.controller_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: ViewHolderController,
        position: Int
    ) {
        holder.controllerRow.controllerName.text =
            items[position].controller.name
        holder.itemView.setOnClickListener {
            val controllerIntent =
                Intent(
                    context,
                    MainActivity::class.java
                )
            controllerIntent.putExtra(
                "id",
                items[position].controller.id
            )
            controllerIntent.putExtra(
                "name",
                items[position].controller.name
            )
            controllerIntent.putExtra(
                "isAdmin",
                items[position].isAdmin
            )
            context.startActivity(
                controllerIntent
            )
        }
        holder.controllerRow.imageSettings.setOnClickListener {
            val controllerSettingsIntent =
                Intent(
                    context,
                    ControllerSettingsActivity::class.java
                )
            controllerSettingsIntent.putExtra(
                "id",
                items[position].controller.id
            )
            controllerSettingsIntent.putExtra(
                "isAdmin",
                items[position].isAdmin
            )
            context.startActivity(
                controllerSettingsIntent
            )
        }
    }
}

class ViewHolderController(
    view: View
) : RecyclerView.ViewHolder(
    view
) {
    val controllerRow: ConstraintLayout =
        view.controllerRow
}
