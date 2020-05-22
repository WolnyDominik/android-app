package com.ismip12.pscf.planemonitor.ui.stabilization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StabilizationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is stabilization Fragment"
    }
    val text: LiveData<String> = _text
}