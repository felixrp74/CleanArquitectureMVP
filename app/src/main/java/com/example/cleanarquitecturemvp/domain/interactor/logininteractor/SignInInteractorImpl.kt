package com.example.cleanarquitecturemvp.domain.interactor.logininteractor


import com.example.cleanarquitecturemvp.presentation.login.exceptions.FirebaseLoginException
import com.google.firebase.auth.FirebaseAuth
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

}