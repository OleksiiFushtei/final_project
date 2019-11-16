package com.example.final_project.api.helpers

import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.models.SensorModel
import com.microsoft.signalr.HubConnectionBuilder
import io.reactivex.Single

class SensorHubHelper(
    token: String
) {
    private var hubConnection =
        HubConnectionBuilder.create(
            "https://fudokosmarthomeweb20191110084623.azurewebsites.net/real/sensors"
        )
            .withAccessTokenProvider(
                Single.defer {
                    Single.just(
                        token
                    )
                })
            .build()

    fun updateSensor(
        view: RecyclerView,
        callback: (sensorModel: SensorModel, view: RecyclerView) -> Unit
    ) {
        hubConnection.on("UpdateSensor", {
            callback(it, view)
        }, SensorModel::class.java)
    }

    fun hubInit() {
        hubConnection.start().blockingAwait()
    }

    fun hubStop() {
        hubConnection.stop()
    }
}