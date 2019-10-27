package com.example.final_project

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.api.interfaces.ControllerInterface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ControllerModel

import kotlinx.android.synthetic.main.activity_controllers_list.*
import kotlinx.android.synthetic.main.content_controllers_list.*

class ControllersListActivity :
    AppCompatActivity(), ControllerInterface.ControllerListener {
    override fun onGetControllersResponseSuccess(
        list: List<ControllerModel>
    ) {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGetControllersResponseFailure() {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGetControllersCancelled() {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGetControllerFailure() {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    private val controllers: ArrayList<ControllerModel> =
        ArrayList()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_controllers_list
        )
        setSupportActionBar(
            toolbar
        )

        fab.setOnClickListener { view ->
            Snackbar.make(
                view,
                "Replace with your own action",
                Snackbar.LENGTH_LONG
            )
                .setAction(
                    "Action",
                    null
                )
                .show()
        }

        val app: MainApplication =
            application as MainApplication

        val controllerHelper =
            app.getApi()

        controllerHelper.getListOfControllers(controllers)
        //show list
        controllersList.layoutManager = LinearLayoutManager(this)
        controllersList.adapter = ControllerAdapter(controllers, this)

    }

}
