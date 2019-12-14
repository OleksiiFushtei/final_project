package com.example.final_project.api.helpers

import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.models.SensorModel
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import io.reactivex.Single
import okhttp3.WebSocket

class SensorHubHelper(
    token: String
) {
    private var hubConnection =
        HubConnectionBuilder.create(
            "https://fudokosmarthome.azurewebsites.net/real/sensors"
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
        hubConnection.on(
            "UpdateSensor",
            {
                callback(
                    it,
                    view
                )
            },
            SensorModel::class.java
        )
    }

    fun hubInit() {
        hubConnection.start()
            .blockingAwait()
    }

    fun hubStop() {
        hubConnection.stop()
    }

    fun hubState(): Boolean =
        hubConnection.connectionState == HubConnectionState.CONNECTED
}