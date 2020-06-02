package com.ismip12.pscf.planemonitor.ui.taws

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TawsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is TAWS Fragment"
    }
    val text: LiveData<String> = _text
}