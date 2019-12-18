package com.example.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.DeviceUserSettingsActivity
import com.example.final_project.R
import com.example.final_project.models.DeviceListItemModel
import kotlinx.android.synthetic.main.user_row.view.*

class DeviceUserAdapter(
    private val items: ArrayList<DeviceListItemModel>,
    private val context: Context?
) : RecyclerView.Adapter<ViewHolderDeviceUser>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderDeviceUser {
        return ViewHolderDeviceUser(
            LayoutInflater.from(
                context
            ).inflate(
                R.layout.user_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: ViewHolderDeviceUser,
        position: Int
    ) {
        holder.userRow.userName.text =
            items[position].userHasController.user.userName
        holder.userRow.imageSettings.setOnClickListener {
            val deviceUserSettingsIntent =
                Intent(
                    context,
                    DeviceUserSettingsActivity::class.java
                )
            deviceUserSettingsIntent.putExtra(
                "deviceUserUserName",
                items[position].userHasController.user.userName
            )
            deviceUserSettingsIntent.putExtra(
                "deviceUserName",
                items[position].userHasController.user.name
            )
            deviceUserSettingsIntent.putExtra(
                "deviceUserSurname",
                items[position].userHasController.user.surname
            )
            deviceUserSettingsIntent.putExtra(
                "deviceUserEmail",
                items[position].userHasController.user.email
            )
            deviceUserSettingsIntent.putExtra(
                "id",
                items[position].id
            )
            context?.startActivity(
                deviceUserSettingsIntent
            )
        }
    }

}

class ViewHolderDeviceUser(
    view: View
) : RecyclerView.ViewHolder(
    view
) {
    val userRow: ConstraintLayout =
        view.userRow
}