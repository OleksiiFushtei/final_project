package com.example.final_project.api.interfaces

import com.example.final_project.models.ControllerAccessModel
import com.example.final_project.models.ControllerListItemModel
import com.example.final_project.models.ErrorModel

interface ControllerAccessInterface {

    interface ControllerAccessGetListInterface {
        fun getListOfUsers(
            id: Int,
            getUsersForControllersListener: GetUsersForControllersListener
        )
    }

    interface ControllerAccessDeleteUser {
        fun deleteUser(
            id: Int,
            deleteUserFromListListener: DeleteUserFromListListener
        )
    }

    interface GetUsersForControllersListener {
        fun onGetUsersForControllersResponseSuccess(
            list: ArrayList<ControllerListItemModel>
        )

        fun onGetUsersForControllersResponseFailure(
            errorModel: ErrorModel?
        )

        fun onGetUsersForControllerCancelled()
        fun onGetUsersForControllerFailure()
    }

    interface DeleteUserFromListListener {
        fun onDeleteUserFromListResponseSuccess()
        fun onDeleteUserFromListResponseFailure(
            errorModel: ErrorModel?
        )

        fun onDeleteUserFromListCancelled()
        fun onDeleteUserFromListFailure()
    }
}