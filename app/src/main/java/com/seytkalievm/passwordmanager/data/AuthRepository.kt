package com.seytkalievm.passwordmanager.data

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
@Singleton
class AuthRepository @Inject constructor(val context: Context) {

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

    fun logout(){
        Toast
            .makeText(context,
                "Signing out", Toast.LENGTH_SHORT)
            .show()

        firebaseAuth.signOut()
        _user.postValue(firebaseAuth.currentUser)
        Log.i("User:", firebaseAuth.currentUser.toString())
    }

    private fun sendEmailVerification(){
        val user = firebaseAuth.currentUser
        if (user != null){
            user!!.sendEmailVerification()
                .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Toast
                        .makeText(context,
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
                        .makeText(context,
                            "Registration error ${it.exception}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    fun loginWithGoogle(): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(
                Resources.getSystem().getString(com.firebase.ui.auth.R.string.default_web_client_id)
            )
            .requestEmail()
            .build()
        val mSignInClient = GoogleSignIn.getClient(context, gso)
        return mSignInClient.signInIntent
    }

}