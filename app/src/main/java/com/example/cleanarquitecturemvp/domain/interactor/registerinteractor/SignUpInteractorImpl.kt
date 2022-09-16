package com.example.cleanarquitecturemvp.domain.interactor.registerinteractor

import android.net.Uri
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class SignUpInteractorImpl : SignUpInteractor {
    override fun signUp(
        fullname: String,
        email: String,
        password: String,
        listener: SignUpInteractor.SignUpCallback
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { it ->
                if (it.isSuccessful){

                    val profileUpdates = userProfileChangeRequest {
                        displayName = fullname
                        photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg")
                    }

                    FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { result->
                            if (result.isSuccessful) {
                                listener.onSignUpSuccess()
                            }
                        }
                }
                else listener.onSignUpFailure(it.exception?.message!!)
            }
    }
}