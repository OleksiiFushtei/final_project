package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.adapters.ControllerAdapter
import com.example.final_project.api.helpers.ControllersListHelper
import com.example.final_project.api.helpers.FirebaseHelper
import com.example.final_project.api.interfaces.ControllersListInterface
import com.example.final_project.api.interfaces.FirebaseInteface
import com.example.final_project.core.MainApplication
import com.example.final_project.models.ControllerListItemModel
import com.example.final_project.models.ErrorModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_list_of_controllers.*
import kotlinx.android.synthetic.main.activity_list_of_controllers.root_layout
import kotlinx.android.synthetic.main.activity_list_of_controllers.toolbar

class ListOfControllersActivity :
    AppCompatActivity(),
    ControllersListInterface.ControllersListListener,
    FirebaseInteface.DeleteTokenListener {

    override fun onGetControllersListResponseSuccess(
        list: ArrayList<ControllerListItemModel>
    ) {
        progressBar.visibility =
            View.GONE
        val llm =
            LinearLayoutManager(
                this
            )
        llm.orientation =
            LinearLayoutManager.VERTICAL
        listOfControllers.layoutManager =
            llm
        val controllerAdapter =
            ControllerAdapter(
                list,
                this
            )
        listOfControllers.adapter =
            controllerAdapter
        when {
            list.isEmpty() -> {
                listOfControllers.visibility =
                    View.GONE
                emptyList.visibility =
                    View.VISIBLE
            }
            else -> {
                listOfControllers.visibility =
                    View.VISIBLE
                emptyList.visibility =
                    View.GONE
            }
        }
    }

    override fun onGetControllersListResponseFailure(
        errorModel: ErrorModel?
    ) {
        progressBar.visibility =
            View.GONE
        val errorMessage =
            when (errorModel) {
                null -> "Server error"
                else -> errorModel.message
            }
        Snackbar.make(
            root_layout,
            errorMessage,
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

    override fun onDeleteTokenResponseSuccess() {
        Hawk.delete(
            "firebaseToken"
        )
    }

    override fun onDeleteTokenResponseFailure(
        errorModel: ErrorModel?
    ) {
        val errorMessage =
            when (errorModel) {
                null -> "Server error"
                else -> errorModel.message
            }
        Snackbar.make(
            root_layout,
            errorMessage,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onDeleteTokenCancelled() {
        Snackbar.make(
            root_layout,
            "Something went wrong. Try again",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onDeleteTokenFailure() {
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
        FirebaseMessaging.getInstance()
            .isAutoInitEnabled =
            false
        FirebaseMessaging.getInstance()
            .isAutoInitEnabled =
            true
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
            addControllerIntent.putExtra(
                "isAdmin",
                true
            )
            startActivity(
                addControllerIntent
            )
        }
        toolbar.setOnMenuItemClickListener {
            val app: MainApplication =
                application as MainApplication
            val firebaseHelper =
                FirebaseHelper(
                    app.getApi()
                )
            firebaseHelper.deleteToken(
                this
            )
            val signInIntent =
                Intent(
                    this@ListOfControllersActivity,
                    SignInActivity::class.java
                )
            Hawk.delete(
                "firebaseToken"
            )
            Hawk.delete(
                "token"
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
