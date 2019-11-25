package com.example.final_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.R
import com.example.final_project.UserGiveAccessActivity
import com.example.final_project.adapters.UserAdapter
import com.example.final_project.api.helpers.ControllerAccessHelper
import com.example.final_project.api.interfaces.ControllerAccessInterface
import com.example.final_project.api.interfaces.ControllerAccessInterface.ControllerAccessDeleteUser
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ControllerListItemModel
import com.example.final_project.models.ErrorModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment :
    Fragment(),
    ControllerAccessInterface.GetUsersForControllersListener {

    companion object {
        private const val ID =
            "id"

        fun newInstance(
            caught: Int
        ): UsersFragment {
            val args =
                Bundle()
            args.putSerializable(
                ID,
                caught
            )
            val fragment =
                UsersFragment()
            fragment.arguments =
                args
            return fragment
        }
    }

    override fun onGetUsersForControllersResponseSuccess(
        list: ArrayList<ControllerListItemModel>
    ) {
        val bundle =
            this.arguments
        val controllerId =
            bundle?.getInt(
                "id",
                0
            )
        userList.addAll(
            list
        )
        val app =
            context?.applicationContext as MainApplication
        val controllerAccessHelper =
            ControllerAccessHelper(
                app.getApi()
            )
        listOfUsers.layoutManager =
            LinearLayoutManager(
                context
            )
        listOfUsers?.adapter =
            UserAdapter(
                list,
                context,
                controllerId = controllerId
            )
        when {
            list.isEmpty() -> {
                listOfUsers.visibility =
                    View.GONE
                emptyList.visibility =
                    View.VISIBLE
            }
            else -> {
                listOfUsers.visibility =
                    View.VISIBLE
                emptyList.visibility =
                    View.GONE
            }
        }
    }

    override fun onGetUsersForControllersResponseFailure(
        errorModel: ErrorModel
    ) {
        var errorMessage =
            errorModel.message
        when {
            errorMessage.isEmpty() -> errorMessage =
                "Response failure. Try again"
        }
        Snackbar.make(
            root_layout,
            errorMessage,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetUsersForControllerCancelled() {

    }

    override fun onGetUsersForControllerFailure() {

    }

    private val userList: ArrayList<ControllerListItemModel> =
        ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(
                R.layout.fragment_users,
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
        val controllerAccessHelper =
            ControllerAccessHelper(
                app.getApi()
            )
        if (controllerId != null) {
            controllerAccessHelper.getListOfUsers(
                controllerId,
                this
            )
        }

        val buttonAdd =
            view.findViewById<FloatingActionButton>(
                R.id.addUserButton
            )
        buttonAdd.setOnClickListener {
            val addUser =
                Intent(
                    this.activity,
                    UserGiveAccessActivity::class.java
                )
            addUser.putExtra(
                "id",
                controllerId
            )
            startActivity(
                addUser
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
        val controllerAccessHelper =
            ControllerAccessHelper(
                app.getApi()
            )
        if (controllerId != null) {
            controllerAccessHelper.getListOfUsers(
                controllerId,
                this
            )
        }
    }
}
