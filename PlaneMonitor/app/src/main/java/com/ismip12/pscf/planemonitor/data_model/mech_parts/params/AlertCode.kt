package com.ismip12.pscf.planemonitor.data_model.mech_parts.params

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

enum class AlertCode(val text: String) {
    ICE("ice"),
    LANDING_GEAR("landing_gear_open"),
    DOOR("door_forbidden_state"),
    ECAM_HYD_LOW("ecam_hyd_low"),
    ECAM_HYD_HIGH("ecam_hyd_high"),
    NETWORK_ERROR("network_error")
}