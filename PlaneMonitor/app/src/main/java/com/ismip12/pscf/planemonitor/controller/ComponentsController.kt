package com.ismip12.pscf.planemonitor.controller
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import com.ismip12.pscf.planemonitor.data_model.Components
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
class ComponentsController{

val component: Components
    get() {
        return this.component
    }


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
                        val id="""{"subsystemName":"MECHANICAL_PARTS","timestamp":{"nano":381214700,"year":2020,"monthValue":6,"dayOfMonth":2,"hour":21,"minute":27,"second":4,"month":"JUNE","dayOfWeek":"TUESDAY","dayOfYear":154,"chronology":{"id":"ISO","calendarType":"iso8601"}},"iceState":{"sensorId":0,"iceCovered":false},"landingGearState":{"sensorId":1,"open":false},"ecamHydStates":[{"sensorId":2,"pressure":300},{"sensorId":3,"pressure":300},{"sensorId":4,"pressure":300}],"doorStates":[{"sensorId":5,"forbiddenState":false},{"sensorId":6,"forbiddenState":false},{"sensorId":7,"forbiddenState":false},{"sensorId":8,"forbiddenState":false}]}"""
                        val msg = mapper.writeValueAsString(id)
                        println(msg.toString());
                        val com: Components =mapper.readValue(id,Components::class.java)
                        //println(com.toString());
                    }

                }

                Event.Type.CLOSED -> {

                }

                Event.Type.ERROR -> {

                }

            }

        },

        {

                error -> Log.println(Log.ERROR, "BLAD", error.message!!)

        }

    )

}
}


