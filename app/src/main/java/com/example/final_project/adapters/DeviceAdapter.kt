package com.example.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.DeviceSettingsActivity
import com.example.final_project.R
import com.example.final_project.models.DeviceModel
import kotlinx.android.synthetic.main.device_row.view.*

class DeviceAdapter(
    private val items: ArrayList<DeviceModel>,
    private val context: Context?,
    private val controllerId: Int?
) : RecyclerView.Adapter<ViewHolderDevice>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderDevice {
        return ViewHolderDevice(
            LayoutInflater.from(
                context
            ).inflate(
                R.layout.device_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: ViewHolderDevice,
        position: Int
    ) {
        holder.deviceRow.deviceName.text =
            items[position].name
        holder.deviceRow.imageSettings.setOnClickListener {
            val deviceSettingsActivity =
                Intent(
                    context,
                    DeviceSettingsActivity::class.java
                )
            deviceSettingsActivity.putExtra(
                "controllerId",
                controllerId
            )
            deviceSettingsActivity.putExtra(
                "deviceId",
                items[position].id
            )
            deviceSettingsActivity.putExtra(
                "deviceName",
                items[position].name
            )
            context?.startActivity(
                deviceSettingsActivity
            )
        }
    }

    fun getItems(): ArrayList<DeviceModel> {
        return items
    }
}

class ViewHolderDevice(
    view: View
) : RecyclerView.ViewHolder(
    view
) {
    val deviceRow: ConstraintLayout =
        view.deviceRow
}