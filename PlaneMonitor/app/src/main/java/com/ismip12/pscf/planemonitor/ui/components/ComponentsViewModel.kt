package com.ismip12.pscf.planemonitor.ui.components

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ismip12.pscf.planemonitor.MainActivity
import com.ismip12.pscf.planemonitor.controller.ComponentsController
import com.ismip12.pscf.planemonitor.data_model.front.Components

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
        },
            {error->
                Log.println(Log.ERROR, "BLAD", error.message!!)
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

    var flDoor: LiveData<String> = _flDoor
    var frDoor: LiveData<String> = _frDoor
    var rlDoor: LiveData<String> = _rlDoor
    var rrDoor: LiveData<String> = _rrDoor
    var gears: LiveData<String> = _gears
    var redHydro: LiveData<String> = _redHydro
    var blueHydro: LiveData<String> = _blueHydro
    var yellowHydro: LiveData<String> = _yellowHydro

    /*fun update(){
        _flDoor.p
    }*/

}