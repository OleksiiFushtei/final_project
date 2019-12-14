package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.adapters.ControllerAdapter
import com.example.final_project.api.helpers.ControllersListHelper
import com.example.final_project.api.interfaces.ControllersListInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ControllerListItemModel
import com.example.final_project.models.ErrorModel
import com.google.android.material.snackbar.Snackbar
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_list_of_controllers.*
import kotlinx.android.synthetic.main.activity_list_of_controllers.root_layout
import kotlinx.android.synthetic.main.activity_list_of_controllers.toolbar

class ListOfControllersActivity :
    AppCompatActivity(),
    ControllersListInterface.ControllersListListener {

    override fun onGetControllersListResponseSuccess(
        list: ArrayList<ControllerListItemModel>
    ) {
        progressBar.visibility =
            View.GONE
        listOfControllers.layoutManager =
            LinearLayoutManager(
                this
            )
        listOfControllers.adapter =
            ControllerAdapter(
                list,
                this
            )
        if (list.isEmpty()) {
            Snackbar.make(
                root_layout,
                "You don't have any available controllers",
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun onGetControllersListResponseFailure(
        errorModel: ErrorModel
    ) {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            errorModel.message,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetControllersListCancelled() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onGetControllerListFailure() {
        progressBar.visibility =
            View.GONE
        Snackbar.make(
            root_layout,
            "Check your connection to the Internet",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_list_of_controllers
        )
        progressBar.visibility =
            View.VISIBLE

        addControllerButton.setOnClickListener {
            val addControllerIntent =
                Intent(
                    this@ListOfControllersActivity,
                    ControllerSettingsActivity::class.java
                )
            addControllerIntent.putExtra(
                "id",
                0
            )
            startActivity(
                addControllerIntent
            )
        }
        toolbar.setOnMenuItemClickListener {
            Hawk.delete(
                "token"
            )
            val signInIntent =
                Intent(
                    this@ListOfControllersActivity,
                    SignInActivity::class.java
                )
            startActivity(
                signInIntent
            )
            finish()
            false
        }
    }

    override fun onResume() {
        super.onResume()
        val app: MainApplication =
            application as MainApplication
        val controllersListHelper =
            ControllersListHelper(
                app.getApi()
            )
        progressBar.visibility =
            View.VISIBLE
        controllersListHelper.getListOfController(
            this
        )
    }
}
