package com.ismip12.pscf.planemonitor.data_model.mech_parts

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.ismip12.pscf.planemonitor.data_model.LocalDateTimeDeserializer
import com.ismip12.pscf.planemonitor.data_model.mech_parts.params.AlertCode
import com.ismip12.pscf.planemonitor.data_model.mech_parts.params.AlertLevel
import java.io.Serializable
import java.time.LocalDateTime

data class Alert (
    @JsonProperty("sensorId")
    val sensorId: Int,
    @JsonProperty("code")
    var code: String,
    @JsonProperty("level")
    var level: String,
    @JsonProperty("timestamp")
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var timestamp: LocalDateTime
) : Serializable