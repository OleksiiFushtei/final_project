package com.example.final_project.api.interfaces

import com.example.final_project.models.DeviceListItemModel
import com.example.final_project.models.ErrorModel

interface DeviceAccessInterface {

    interface DeviceAccessGetListInterface {
        fun getListOfUsers(
            id: Int,
            getUsersForDevicesListener: GetUsersForDevicesListener
        )
    }

    interface DeviceAccessDeleteUserInterface {
        fun deleteUser(
            id: Int,
            deleteUserFromListListener: DeleteUserFromListListener
        )
    }

    interface GetUsersForDevicesListener {
        fun onGetUsersFroDevicesResponseSuccess(
            list: ArrayList<DeviceListItemModel>
        )

        fun onGetUsersForDevicesResponseFailure(
            errorModel: ErrorModel?
        )

        fun onGetUsersForDevicesCancelled()
        fun onGetUsersForDevicesFailure()
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