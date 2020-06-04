package com.ismip12.pscf.planemonitor.controller

import android.util.Log
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import com.ismip12.pscf.planemonitor.data_model.mech_parts.MechanicalPartsState
import com.ismip12.pscf.planemonitor.data_model.mech_parts.Alert
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import okhttp3.OkHttpClient
import java.lang.Exception
import java.util.concurrent.TimeUnit

class ComponentsController {
    private val statePublisher: PublishSubject<MechanicalPartsState> = PublishSubject.create()
    private val alarmPublisher: PublishSubject<List<Alert>> = PublishSubject.create()
    private val disconnectPublisher: PublishSubject<Boolean> = PublishSubject.create()
    fun getData() {

        lateinit var stompConnection: Disposable

        lateinit var topic: Disposable

        val intervalMillis = 1000L

        val client = OkHttpClient().newBuilder().connectTimeout(1000,TimeUnit.MILLISECONDS).build()

        val stomp = StompClient(client, intervalMillis)

        stomp.url = "http://192.168.1.113:8080/stomp-ws"

        stompConnection = stomp.connect().subscribe(

            {

                when (it.type) {

                    Event.Type.OPENED -> {

                        stomp.join("/sensor-network/mechanical-parts").subscribe {

                            Log.println(Log.DEBUG, "MechanicalPartsState", it)
                            val mapper = ObjectMapper()
                            println("To jest state:"+it)
                            val mech: MechanicalPartsState =
                                mapper.readValue(it, MechanicalPartsState::class.java)
                            statePublisher.onNext(mech);
                            disconnectPublisher.onNext(false)
                        }

                        stomp.join("/sensor-network/alerts").subscribe {

                            Log.println(Log.DEBUG, "Alarms", it)
                            val mapper = ObjectMapper()
                            println("To jest alaram:"+it)
                            val alarm: List<Alert> =
                                mapper.readValue(it, object : TypeReference<List<Alert?>?>() {})
                            alarmPublisher.onNext(alarm);
                            disconnectPublisher.onNext(false)

                        }

                    }

                    Event.Type.CLOSED -> {
                        println("dupa closed")
                    }

                    Event.Type.ERROR -> {
                        disconnectPublisher.onNext(true)
                    }


                }

            },

            {
                    error ->
                Log.println(Log.ERROR, "BLAD", error.message!!)
                println("dupa")
                statePublisher.onError(error)
            }

        )
    }

    fun subscribeNewStates():Flowable<MechanicalPartsState>{
        return statePublisher.toFlowable(BackpressureStrategy.LATEST)
    }
    fun subscribeNewAlarms():Flowable<List<Alert>>{
        return alarmPublisher.toFlowable(BackpressureStrategy.LATEST)
    }
    fun subscribeNewErrors():Flowable<Boolean>{
        return disconnectPublisher.toFlowable(BackpressureStrategy.LATEST)
    }
}


