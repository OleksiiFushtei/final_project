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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list_of_controllers.*

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

    override fun onGetControllersListResponseFailure() {
    }

    override fun onGetControllersListCancelled() {

    }

    override fun onGetControllerListFailure() {
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

        val app: MainApplication =
            application as MainApplication

        val controllersListHelper =
            ControllersListHelper(
                app.getApi()
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

        controllersListHelper.getListOfController(
            this
        )

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
