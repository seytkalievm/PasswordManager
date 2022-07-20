package com.seytkalievm.passwordmanager.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepository(val context: Context) {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _user = MutableLiveData<FirebaseUser>()
    val userLiveData: LiveData<FirebaseUser> get() = _user

    fun register(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    sendEmailVerification()
                    _user.postValue(firebaseAuth.currentUser)
                } else {
                     Toast
                         .makeText(context,
                             "Registration error ${it.exception}", Toast.LENGTH_SHORT)
                         .show()
                }
            }
    }

    suspend fun logout(){
        Toast
            .makeText(context,
                "Signing out", Toast.LENGTH_SHORT)
            .show()

        firebaseAuth.signOut()
        _user.postValue(firebaseAuth.currentUser)
        context.dataStore.edit {
            it.clear()
        }
        Log.i("User:", firebaseAuth.currentUser.toString())
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

    fun login(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    _user.postValue(firebaseAuth.currentUser)
                } else {
                    Toast
                        .makeText(context,
                            "Registration error ${it.exception}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }


}