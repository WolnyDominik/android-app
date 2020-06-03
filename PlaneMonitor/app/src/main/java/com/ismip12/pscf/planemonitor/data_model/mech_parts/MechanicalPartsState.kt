package com.ismip12.pscf.planemonitor.data_model.mech_parts

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.ismip12.pscf.planemonitor.data_model.LocalDateTimeDeserializer

import com.ismip12.pscf.planemonitor.data_model.mech_parts.params.DoorState
import com.ismip12.pscf.planemonitor.data_model.mech_parts.params.EcamHydState
import com.ismip12.pscf.planemonitor.data_model.mech_parts.params.IceState
import com.ismip12.pscf.planemonitor.data_model.mech_parts.params.LandingGearState
import java.io.Serializable
import java.time.LocalDateTime

data class MechanicalPartsState(
    @JsonProperty("subsystemName")
    var subsystemName: String,

    @JsonProperty("timestamp")
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var timestamp: LocalDateTime,

    @JsonProperty("iceState")
    var iceState: IceState,

    @JsonProperty("landingGearState")
    var landingGearState: LandingGearState,

    @JsonProperty("ecamHydStates")
    var ecamHydStates: List<EcamHydState>,

    @JsonProperty("doorStates")
    var doorStates: List<DoorState>
    ) : Serializable
