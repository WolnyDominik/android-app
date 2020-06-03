package com.ismip12.pscf.planemonitor.controller

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import com.ismip12.pscf.planemonitor.data_model.front.Components
import com.ismip12.pscf.planemonitor.data_model.mech_parts.MechanicalPartsState
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import okhttp3.OkHttpClient

class ComponentsController {
    lateinit var mechanicalPartsState: MechanicalPartsState
    private val statePublisher: PublishSubject<MechanicalPartsState> = PublishSubject.create()
    fun getData() {

        lateinit var stompConnection: Disposable

        lateinit var topic: Disposable

        val intervalMillis = 1000L

        val client = OkHttpClient()

        val stomp = StompClient(client, intervalMillis)

        stomp.url = "http://192.168.1.113:8080/stomp-ws"

        stompConnection = stomp.connect().subscribe(

            {

                when (it.type) {

                    Event.Type.OPENED -> {

                        stomp.join("/sensor-network/mechanical-parts").subscribe {

                            Log.println(Log.DEBUG, "WIADOMOSC", it)
                            val mapper = ObjectMapper()
                            val values: MechanicalPartsState =
                                mapper.readValue(it, MechanicalPartsState::class.java)
                            statePublisher.onNext(values);

                        }

                    }

                    Event.Type.CLOSED -> {

                    }

                    Event.Type.ERROR -> {

                    }

                }

            },

            {
                    error ->
                Log.println(Log.ERROR, "BLAD", error.message!!)
                statePublisher.onError(error)
            }

        )
    }

    fun subscribeNewStates():Flowable<MechanicalPartsState>{
        return statePublisher.toFlowable(BackpressureStrategy.LATEST)
    }
}


