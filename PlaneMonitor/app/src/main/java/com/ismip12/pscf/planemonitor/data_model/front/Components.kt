package com.ismip12.pscf.planemonitor.data_model.front

import com.ismip12.pscf.planemonitor.data_model.mech_parts.MechanicalPartsState
import java.time.LocalDateTime
import com.fasterxml.jackson.annotation.JsonProperty as JsonProperty

class Components{
    var ICE: Boolean = false
    var DOR: BooleanArray = booleanArrayOf(true,true,false,false)
    var LGP: Boolean =false
    var ECAM_HYD: FloatArray = floatArrayOf(100F,200F,300F)

    constructor(){

    }

    constructor(mechanicalPartsState: MechanicalPartsState) {
        ICE = mechanicalPartsState.iceState.iceCovered
        LGP = mechanicalPartsState.landingGearState.open
        for(m in mechanicalPartsState.doorStates){
            DOR[m.sensorId]=m.forbiddenState;
        }
        for(m in mechanicalPartsState.ecamHydStates){
            ECAM_HYD[m.sensorId]=m.pressure
        }
    }
}

