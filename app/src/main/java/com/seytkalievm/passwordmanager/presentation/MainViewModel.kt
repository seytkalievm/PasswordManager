package com.seytkalievm.passwordmanager.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.seytkalievm.passwordmanager.domain.use_case.GetPasscodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(
    private val getPasscodeUseCase: GetPasscodeUseCase,
): ViewModel() {

    private val _isReady = MutableLiveData(false)
    val isReady: LiveData<Boolean> get() = _isReady

    val user = FirebaseAuth.getInstance().currentUser

    private var _passcode: String? = null
    val passcode get() = _passcode

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _passcode = getPasscodeUseCase()
            _isReady.postValue(true)
        }
    }
}