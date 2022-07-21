package com.seytkalievm.passwordmanager.presentation.passcode.enter

import android.util.Log
import androidx.lifecycle.*
import com.seytkalievm.passwordmanager.domain.repository.AuthRepository
import com.seytkalievm.passwordmanager.domain.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterPasscodeViewModel @Inject constructor(
    private val preferencesRepository: UserPreferencesRepository,
    private val authRepository: AuthRepository,
): ViewModel() {

    private var currentPasscode = ""
    private val _isPasscodeValid = MutableLiveData<Boolean>()
    val isPasscodeValid: LiveData<Boolean> get() = _isPasscodeValid

    private var _userPasscode = ""

    private val _coloredDots = MutableLiveData(0)
    val coloredDots: LiveData<Int> get() = _coloredDots

    init {
        viewModelScope.launch {
            currentPasscode = preferencesRepository.passcode.first()
        }
    }

    fun numberPressed(number: Int){
        if(_userPasscode.length < 3){
            _userPasscode = _userPasscode.plus(number.toString())
            _coloredDots.value = _userPasscode.length
        } else {
            _userPasscode = _userPasscode.plus(number.toString())
            _coloredDots.value = _userPasscode.length
            checkPasscode()
        }
    }

    fun remove(){
        _userPasscode = _userPasscode.dropLast(1)
        _coloredDots.value = _userPasscode.length
    }

    private fun checkPasscode(){
        Log.i("PasscodeViewModel", "Checking password")
        Log.i("PasscodeViewModel", "$currentPasscode vs $_userPasscode")
        if ( _userPasscode == currentPasscode){
            _isPasscodeValid.postValue(true)
        } else {
            _isPasscodeValid.postValue(false)
            _userPasscode = ""
            _coloredDots.value = 0
        }
    }

    fun logout(){
        viewModelScope.launch {
            authRepository.logout()
        }
    }

}