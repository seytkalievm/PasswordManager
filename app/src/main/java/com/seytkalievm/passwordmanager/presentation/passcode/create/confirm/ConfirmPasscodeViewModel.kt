package com.seytkalievm.passwordmanager.presentation.passcode.create.confirm

import android.util.Log
import androidx.lifecycle.*
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.domain.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmPasscodeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _passcodeSet = MutableLiveData(false)
    val passcodeSet: LiveData<Boolean> get() = _passcodeSet

    private val passcode = savedStateHandle.get<String>("passcode")

    private var confPasscode = ""

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> get() = _error

    private val _coloredDots = MutableLiveData(0)
    val coloredDots: LiveData<Int> get() = _coloredDots


    fun numberPressed(number: Int){
        if (confPasscode.length < 3) {
            confPasscode = confPasscode.plus(number.toString())
            _coloredDots.postValue(confPasscode.length)
        } else if (confPasscode.length == 3) {
            confPasscode = confPasscode.plus(number.toString())
            checkPasscodes()
        }
        Log.i("ConfPasscodeViewModel", "$passcode and $confPasscode.")
    }

    fun remove(){
        confPasscode = confPasscode.dropLast(1)
        _coloredDots.value = confPasscode.length
        Log.i("ConfPasscodeViewModel", "$passcode and $confPasscode.")
    }

    private fun checkPasscodes(){
        if (passcode == confPasscode){
            createPasscode()
        } else {
            confPasscode = ""
            _coloredDots.postValue(0)
            _error.postValue(R.string.passcodes_dont_match)
        }
    }

    private fun createPasscode(){
        viewModelScope.launch {
            preferencesRepository.setPasscode(confPasscode)
            _passcodeSet.postValue(true)
        }
    }
}