package com.ismip12.pscf.planemonitor.ui.comfort

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComfortViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is comfort Fragment"
    }
    val text: LiveData<String> = _text
}