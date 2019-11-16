package com.example.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.SensorSettingsActivity
import com.example.final_project.models.SensorModel
import kotlinx.android.synthetic.main.sensor_row.view.*

class SensorAdapter(
    private val items: ArrayList<SensorModel>,
    private val context: Context?,
    private val controlledId: Int?
) : RecyclerView.Adapter<ViewHolderSensor>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderSensor {
        return ViewHolderSensor(
            LayoutInflater.from(
                context
            ).inflate(
                R.layout.sensor_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: ViewHolderSensor,
        position: Int
    ) {
        holder.sensorRow.sensorName.text =
            items[position].name
        holder.sensorRow.sensorValue.text =
            "Value: " + items[position].value
        holder.sensorRow.imageSettings.setOnClickListener {
            val sensorSettingsIntent =
                Intent(
                    context,
                    SensorSettingsActivity::class.java
                )
            sensorSettingsIntent.putExtra(
                "controllerId",
                controlledId
            )
            sensorSettingsIntent.putExtra(
                "sensorId",
                items[position].id
            )
            context?.startActivity(
                sensorSettingsIntent
            )
        }
    }

    fun getItems(): ArrayList<SensorModel> {
        return items
    }
}

class ViewHolderSensor(
    view: View
) : RecyclerView.ViewHolder(
    view
) {
    val sensorRow: ConstraintLayout =
        view.sensorRow
}