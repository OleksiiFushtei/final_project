package com.example.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.ControllerSettings
import com.example.final_project.MainActivity
import com.example.final_project.OnSwitchToggled
import com.example.final_project.R
import com.example.final_project.models.ControllerListItemModel
import kotlinx.android.synthetic.main.controller_row.view.*

class ControllerAdapter(
    private val items: ArrayList<ControllerListItemModel>,
    private val context: Context
//    private val switchInterface: OnSwitchToggled
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
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
        holder: ViewHolder,
        position: Int
    ) {
        holder.controllerRow.controllerName.text =
            items[position].controller.name
//        holder.controllerRow.controllerSwitch.isEnabled =
//            items[position].controller.status

        holder.itemView.setOnLongClickListener {
            val controllerSettingsIntent =
                Intent(
                    context,
                    ControllerSettings::class.java
                )
            controllerSettingsIntent.putExtra(
                "id",
                position+1
            )
            context.startActivity(
                controllerSettingsIntent
            )
            return@setOnLongClickListener true
        }
        holder.itemView.setOnClickListener {
            val controllerIntent =
                Intent(
                    context,
                    MainActivity::class.java
                )
            controllerIntent.putExtra(
                "id",
                position+1
            )
            context.startActivity(
                controllerIntent
            )
        }
//        holder.controllerRow.controllerSwitch.setOnCheckedChangeListener { _, isChecked ->
//            switchInterface.changeStatus(
//                holder.itemView.id,
//                status = isChecked
//            )
//        }
    }
}

class ViewHolder(
    view: View
) : RecyclerView.ViewHolder(
    view
) {
    val controllerRow: ConstraintLayout =
        view.controllerRow
}
