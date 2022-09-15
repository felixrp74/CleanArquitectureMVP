package com.example.cleanarquitecturemvp.domain.interactor.logininteractor

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

const val TAG = "SignInInteractorImpl"


class SignInInteractorImpl : SignInInteractor {
    private lateinit var auth: FirebaseAuth

    override fun SignInWithEmailAndPassword(
        email: String,
        password: String,
        listener: SignInInteractor.SignInCallback
    ) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) listener.onSignInSuccess()
                else listener.onSignInFailure(it.exception?.message!!)
            }

    }


}