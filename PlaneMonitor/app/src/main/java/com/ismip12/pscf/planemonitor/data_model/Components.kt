package com.ismip12.pscf.planemonitor.data_model

import java.time.LocalDateTime
import com.fasterxml.jackson.annotation.JsonProperty as JsonProperty

data class Components(
   /* @JsonProperty("timestamp")
    var timestamp: LocalDateTime,*/
    @JsonProperty("iceState")
    var iceCovered: Pair<Int,Boolean>,
    @JsonProperty("landingGearState")
    var gearOpen: Pair<Int,Boolean>,
    @JsonProperty("ecamHydStates")
    var hydPressure: List<Pair<Int,Double>>,
    @JsonProperty("author")
    var doors: List<Pair<Int,Boolean>>

){}