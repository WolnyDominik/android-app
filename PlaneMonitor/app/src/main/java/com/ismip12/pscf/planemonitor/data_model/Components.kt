package com.ismip12.pscf.planemonitor.data_model

data class Components(
    var ICE: Boolean,
    var DOR: BooleanArray,
    var LGP: Boolean,
    var ECAM_HYD: FloatArray
) {
}