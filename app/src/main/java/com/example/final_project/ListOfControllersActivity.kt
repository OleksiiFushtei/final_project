package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.adapters.ControllerAdapter
import com.example.final_project.api.helpers.ControllerSaveHepler
import com.example.final_project.api.helpers.ControllersListHelper
import com.example.final_project.api.interfaces.ControllersListInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ControllerListItemModel
import com.example.final_project.models.ControllerModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list_of_controllers.*

class ListOfControllersActivity :
    AppCompatActivity(),
    ControllersListInterface.ControllersListListener {

//    override fun changeStatus(
//        id: Int,
//        status: Boolean
//    ) {
//        when (status) {
//            true -> Snackbar.make(
//                root_layout,
//                "Controller is OFF",
//                Snackbar.LENGTH_SHORT
//            )
//                .show()
//            else -> Snackbar.make(
//                root_layout,
//                "Controller is OFF",
//                Snackbar.LENGTH_SHORT
//            )
//                .show()
//        }
//        val controllerSaveHelper =
//            ControllerSaveHepler(
//                app.getApi()
//            )
//        controllerSaveHelper.saveController(
//            ControllerModel(id, "xd", "1122", status))
//    }

    override fun onGetControllersListResponseSuccess(
        list: ArrayList<ControllerListItemModel>
    ) {
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
                Snackbar.LENGTH_INDEFINITE
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

        addControllerButton.setOnClickListener {
            val addControllerIntent =
                Intent(
                    this@ListOfControllersActivity,
                    ControllerSettings::class.java
                )
            addControllerIntent.putExtra(
                "id",
                -1
            )
            startActivity(
                addControllerIntent
            )
        }

        controllersListHelper.getListOfController(
            this
        )

    }

}
