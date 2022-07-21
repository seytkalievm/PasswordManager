package com.seytkalievm.passwordmanager.presentation.passcode.create.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CreatePasscodeViewModel @Inject constructor() : ViewModel() {

    private var _passcode = ""
    val passcode get() = _passcode

    private val _coloredDots = MutableLiveData(0)
    val coloredDots: LiveData<Int> get() = _coloredDots

    fun numberPressed(number: Int){
        if (_passcode.length <= 3) {
            _passcode = _passcode.plus(number.toString())
            _coloredDots.postValue(_passcode.length)
        }
        Log.i("ConfPasscodeViewModel", _passcode)
    }

    fun remove(){
        _passcode = _passcode.dropLast(1)
        _coloredDots.value = _passcode.length
        Log.i("ConfPasscodeViewModel", "$_passcode.")
    }

}