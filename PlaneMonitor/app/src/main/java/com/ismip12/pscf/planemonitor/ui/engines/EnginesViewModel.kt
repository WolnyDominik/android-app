package com.ismip12.pscf.planemonitor.ui.engines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EnginesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is engines Fragment"
    }
    val text: LiveData<String> = _text
}