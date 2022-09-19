package com.example.cleanarquitecturemvp.domain.interactor.logininteractor

import android.util.Log
import com.example.cleanarquitecturemvp.presentation.login.exceptions.FirebaseLoginException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

const val TAG = "SignInInteractorImpl"


class SignInInteractorImpl : SignInInteractor {
    private lateinit var auth: FirebaseAuth
    override suspend fun signInWithEmailAndPassword(email: String, password: String) : Unit = suspendCancellableCoroutine{ continuation ->
        FirebaseAuth.getInstance().signInWithEmailAndPassword (email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) continuation.resume(Unit)
                else continuation.resumeWithException(FirebaseLoginException(it.exception?.message))
            }
    }
/*
    override fun signInWithEmailAndPassword(
        email: String,
        password: String,
        listener: SignInInteractor.SignInCallback
    ) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword (email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) listener.onSignInSuccess()
                else listener.onSignInFailure(it.exception?.message!!)
            }

    }
*/

}