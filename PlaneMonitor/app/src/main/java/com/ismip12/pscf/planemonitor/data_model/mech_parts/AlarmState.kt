package com.ismip12.pscf.planemonitor.data_model.mech_parts

import com.fasterxml.jackson.annotation.JsonProperty
import com.ismip12.pscf.planemonitor.data_model.mech_parts.params.EcamHydState
import java.io.Serializable

data class AlarmState(
    @JsonProperty("ecamHydStates")
    var ecamHydStates: List<AlarmState>
) : Serializable