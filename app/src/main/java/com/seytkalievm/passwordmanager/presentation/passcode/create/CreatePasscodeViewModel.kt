package com.seytkalievm.passwordmanager.presentation.passcode.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.data.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreatePasscodeViewModel @Inject constructor(
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private var passcode = ""
    private var confPasscode = ""

    private val _passcodeCreated = MutableLiveData(false)
    val passcodeSet: LiveData<Boolean> get() = _passcodeCreated

    private val _status = MutableLiveData(R.string.create_passcode)
    val status: LiveData<Int> get() = _status

    private val _coloredDots = MutableLiveData(0)
    val coloredDots: LiveData<Int> get() = _coloredDots

    fun numberPressed(number: Int){
        if (passcode.length < 3) {
            passcode = passcode.plus(number.toString())
            _coloredDots.postValue(passcode.length)
        } else if (passcode.length == 3) {
            passcode = passcode.plus(number.toString())
            _status.postValue(R.string.confirm_passcode)
            _coloredDots.postValue(0)
        } else {
            _status.postValue(R.string.confirm_passcode)
            if (confPasscode.length < 3){
                confPasscode = confPasscode.plus(number.toString())
                _coloredDots.postValue(confPasscode.length)
            } else {
                confPasscode = confPasscode.plus(number.toString())
                _coloredDots.postValue(confPasscode.length)
                checkPasscodes()
            }
        }
        Log.i("ConfPasscodeViewModel", "$passcode and $confPasscode.")
    }

    fun remove(){
        if (status.value == R.string.create_passcode){
            passcode = passcode.dropLast(1)
            _coloredDots.value = passcode.length
        } else {
            confPasscode = confPasscode.dropLast(1)
            _coloredDots.value = confPasscode.length
        }
        Log.i("ConfPasscodeViewModel", "$passcode and $confPasscode.")
    }

    private fun checkPasscodes(){
        if (passcode == confPasscode){
            createPasscode()
        } else {
            _status.value = R.string.passcodes_dont_match
            confPasscode = ""
            _coloredDots.postValue(0)
        }
    }

    private fun createPasscode(){
        viewModelScope.launch {
            preferencesRepository.setPasscode(passcode)
            _passcodeCreated.postValue(true)
        }
    }
}