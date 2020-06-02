package com.ismip12.pscf.planemonitor.ui.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ismip12.pscf.planemonitor.data_model.Components

class ComponentsViewModel : ViewModel() {

    private var compModel = Components(false, booleanArrayOf(true, false, true, false), true, floatArrayOf(100F, 200F, 300F))

    private val _flDoor = MutableLiveData<String>().apply {
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



}