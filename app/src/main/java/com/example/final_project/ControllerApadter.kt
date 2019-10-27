package com.example.final_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.models.ControllerModel
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.android.synthetic.main.controller_row.view.*

class ControllerAdapter(
    private val items: ArrayList<ControllerModel>,
    private val context: Context
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
        holder.controller.text =
            items[position].name
        holder.controller.isEnabled =
            items[position].status
    }
}

class ViewHolder(
    view: View
) : RecyclerView.ViewHolder(
    view
) {
    val controller: SwitchMaterial =
        view.controllerSwitch
}
