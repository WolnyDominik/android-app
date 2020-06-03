package com.ismip12.pscf.planemonitor.data_model.mech_parts.params

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable


data class LandingGearState(
    @JsonProperty("sensorId")
    val sensorId: Int,
    @JsonProperty("open")
    var open: Boolean
) : Serializable