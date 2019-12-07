package com.example.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.UserSettingsActivity
import com.example.final_project.api.interfaces.ControllerAccessInterface
import com.example.final_project.models.ControllerListItemModel
import com.example.final_project.models.ErrorModel
import kotlinx.android.synthetic.main.user_row.view.*

class UserAdapter(
    private val items: ArrayList<ControllerListItemModel>,
    private val context: Context?,
    private val controllerId: Int?,
    private val isAdmin: Boolean?
) : RecyclerView.Adapter<ViewHolderUser>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderUser {
        return ViewHolderUser(
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
        holder: ViewHolderUser,
        position: Int
    ) {
        holder.userRow.userName.text =
            items[position].user.userName
        holder.userRow.imageSettings.setOnClickListener {
            val userSettingsIntent =
                Intent(
                    context,
                    UserSettingsActivity::class.java
                )
            userSettingsIntent.putExtra(
                "userUserName",
                items[position].user.userName
            )
            userSettingsIntent.putExtra(
                "userName",
                items[position].user.name
            )
            userSettingsIntent.putExtra(
                "userSurname",
                items[position].user.surname
            )
            userSettingsIntent.putExtra(
                "userEmail",
                items[position].user.email
            )
            userSettingsIntent.putExtra(
                "id",
                items[position].id
            )
            userSettingsIntent.putExtra(
                "isAdmin",
                isAdmin
            )
            context?.startActivity(
                userSettingsIntent
            )
        }
    }

    fun getItems(): ArrayList<ControllerListItemModel> {
        return items
    }

}

class ViewHolderUser(
    view: View
) : RecyclerView.ViewHolder(
    view
) {
    val userRow: ConstraintLayout =
        view.userRow
}