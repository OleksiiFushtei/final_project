package com.example.final_project.api.interfaces

import com.example.final_project.models.ErrorModel

interface FirebaseInteface {

    fun postToken(
        token: String,
        postTokenListener: PostTokenListener
    )

    fun deleteToken(
        deleteTokenListener: DeleteTokenListener
    )

    interface PostTokenListener {
        fun onPostTokenResponseSuccess(
            token: String
        )

        fun onPostTokenResponseFailure(
            errorModel: ErrorModel?
        )

        fun onPostTokenCancelled()
        fun onPostTokenFailure()
    }

    interface DeleteTokenListener {
        fun onDeleteTokenResponseSuccess()
        fun onDeleteTokenResponseFailure(
            errorModel: ErrorModel?
        )

        fun onDeleteTokenCancelled()
        fun onDeleteTokenFailure()
    }

}