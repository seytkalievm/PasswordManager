package com.seytkalievm.passwordmanager.data.repository

import android.content.Context
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.seytkalievm.passwordmanager.common.Resource
import com.seytkalievm.passwordmanager.domain.repository.AuthRepository

class AuthRepositoryImpl(val context: Context): AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _user = MutableLiveData<FirebaseUser>()
    override val user: LiveData<FirebaseUser> get() = _user

    private val _loginStatus = MutableLiveData<Resource<Boolean>>()
    override val loginStatus: LiveData<Resource<Boolean>> get() = _loginStatus

    private val _registerStatus = MutableLiveData<Resource<Boolean>>()
    override val registerStatus: LiveData<Resource<Boolean>> get() = _registerStatus

    private val _logoutStatus = MutableLiveData<Resource<Boolean>>()
    override val logoutStatus: LiveData<Resource<Boolean>> get() = _logoutStatus

    override fun register(email: String, password: String){
        _registerStatus.postValue(Resource.Loading())

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    sendEmailVerification()
                    _user.postValue(firebaseAuth.currentUser)
                    _registerStatus.postValue(Resource.Success(data = true))
                } else {
                    _registerStatus.postValue(
                        Resource.Error(message = it.exception?.message.toString())
                    )
                }
            }
    }

    override suspend fun logout(){
        _logoutStatus.postValue(Resource.Loading())
        try {
            firebaseAuth.signOut()
            _user.postValue(firebaseAuth.currentUser)
            context.dataStore.edit {
                it.clear()
            }
            _logoutStatus.postValue(Resource.Success(data = true))

        } catch (e: Exception){
            _logoutStatus.postValue(Resource.Error(message = e.message.toString()))
        }

    }

    private fun sendEmailVerification(){
        val user = firebaseAuth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener{ task ->
            if(task.isSuccessful){
                Toast
                    .makeText(context,
                        "Verification email sent", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun login(email: String, password: String) {
        _loginStatus.postValue(Resource.Loading())
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    _user.postValue(firebaseAuth.currentUser)
                    _loginStatus.postValue(Resource.Success(data = true))
                } else {
                    _loginStatus.postValue(
                        Resource.Error(message = it.exception?.message.toString())
                    )
                }
            }
    }


}