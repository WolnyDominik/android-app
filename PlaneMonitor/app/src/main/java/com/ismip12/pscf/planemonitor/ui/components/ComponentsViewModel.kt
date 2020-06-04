package com.ismip12.pscf.planemonitor.ui.components

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ismip12.pscf.planemonitor.controller.ComponentsController
import com.ismip12.pscf.planemonitor.data_model.front.Components
import com.ismip12.pscf.planemonitor.data_model.mech_parts.Alert
import com.ismip12.pscf.planemonitor.data_model.mech_parts.params.AlertCode
import com.ismip12.pscf.planemonitor.data_model.mech_parts.params.AlertLevel

class ComponentsViewModel : ViewModel() {

    private var compContr = ComponentsController();
    private var compModel = Components()

     init{

        compContr.getData()
        compContr.subscribeNewStates().subscribe({state->
            compModel= Components(state)
            _flDoor.postValue(if (compModel.DOR[0]) "Otwarte" else "Zamkniete" )
            _frDoor.postValue(if (compModel.DOR[1]) "Otwarte" else "Zamkniete" )
            _rlDoor.postValue(if (compModel.DOR[2]) "Otwarte" else "Zamkniete" )
            _rrDoor.postValue(if (compModel.DOR[3]) "Otwarte" else "Zamkniete" )
            _gears.postValue(if (compModel.LGP) "Opuszczone" else  "Zablokowane")
            _redHydro.postValue(compModel.ECAM_HYD[0].toString() + " PSI")
            _blueHydro.postValue(compModel.ECAM_HYD[1].toString() + " PSI")
            _yellowHydro.postValue(compModel.ECAM_HYD[2].toString() + " PSI")
            _noConnection.postValue(false)
        },
            {error->
                Log.println(Log.ERROR, "BLAD", error.message!!)
                _noConnection.postValue(true)
            })

        compContr.subscribeNewAlarms().subscribe({alarm->
           _alertDoor.postValue(alarm)

        },
            {error->
                Log.println(Log.ERROR, "BLAD", error.message!!)
            })

         compContr.subscribeNewErrors().subscribe({error->
             if(error==true){
                 _noConnection.postValue(true)
             }else{
                 _noConnection.postValue(false)
             }
         })
    }

    private val _flDoor = MutableLiveData<String>().apply {
        println(compModel.toString())
        if (compModel.DOR[0]) {
            value = "Otwarte"
        } else {
            value = "Zamknięte"
        }

    }

    private val _frDoor = MutableLiveData<String>().apply {
        if (compModel.DOR[1]) {
            value = "Otwarte"
        } else {
            value = "Zamknięte"
        }
    }

    private val _rlDoor = MutableLiveData<String>().apply {
        if (compModel.DOR[2]) {
            value = "Otwarte"
        } else {
            value = "Zamknięte"
        }
    }

    private val _rrDoor = MutableLiveData<String>().apply {
        if (compModel.DOR[3]) {
            value = "Otwarte"
        } else {
            value = "Zamknięte"
        }
    }

    private val _gears = MutableLiveData<String>().apply {
        if (compModel.LGP) {
            value = "Opuszczone"
        } else {
            value = "Zablokowane"
        }
    }

    private val _redHydro = MutableLiveData<String>().apply {
        value = compModel.ECAM_HYD[0].toString() + " PSI"
    }

    private val _blueHydro = MutableLiveData<String>().apply {
        value = compModel.ECAM_HYD[2].toString() + " PSI"
    }

    private val _yellowHydro = MutableLiveData<String>().apply {
        value = compModel.ECAM_HYD[1].toString() + " PSI"
    }
    private val _alertDoor = MutableLiveData<List<Alert>>().apply {
        value = emptyList()
    }
    private val _noConnection = MutableLiveData<Boolean>().apply {
        value = false
    }

    var flDoor: LiveData<String> = _flDoor
    var frDoor: LiveData<String> = _frDoor
    var rlDoor: LiveData<String> = _rlDoor
    var rrDoor: LiveData<String> = _rrDoor
    var gears: LiveData<String> = _gears
    var redHydro: LiveData<String> = _redHydro
    var blueHydro: LiveData<String> = _blueHydro
    var yellowHydro: LiveData<String> = _yellowHydro
    var alertDoor: LiveData<List<Alert>> = _alertDoor
    var noConnection: LiveData<Boolean> = _noConnection
    /*fun update(){
        _flDoor.p
    }*/

}