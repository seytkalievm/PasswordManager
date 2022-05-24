package com.seytkalievm.passwordmanager.data

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.seytkalievm.passwordmanager.data.model.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(private val application: Application) {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private var user = firebaseAuth.currentUser
    private val _user = MutableLiveData<FirebaseUser>(user)
    val userLiveData: LiveData<FirebaseUser> get() = _user

    fun register(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    sendEmailVerification()
                    _user.postValue(firebaseAuth.currentUser)
                } else {
                     Toast
                         .makeText(application,
                             "Registration error ${it.exception}", Toast.LENGTH_SHORT)
                         .show()
                }
            }
    }

    fun logout(){
        firebaseAuth.signOut()
    }

    private fun sendEmailVerification(){
        if (user != null){
            user!!.sendEmailVerification()
                .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Toast
                        .makeText(application,
                            "Verification email sent", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }
    fun login(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    _user.postValue(firebaseAuth.currentUser)
                } else {
                    Toast
                        .makeText(application,
                            "Registration error ${it.exception}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    fun loginWithGoogle(){

    }

}