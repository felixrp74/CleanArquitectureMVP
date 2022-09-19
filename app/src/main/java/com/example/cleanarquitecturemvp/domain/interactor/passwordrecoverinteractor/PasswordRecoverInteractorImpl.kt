package com.example.cleanarquitecturemvp.domain.interactor.passwordrecoverinteractor

import com.example.cleanarquitecturemvp.presentation.passwordrecover.exceptions.FirebasePasswordRecoverException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class PasswordRecoverInteractorImpl : PasswordRecoverInteractor {

    override suspend fun sendEmailResetPassword(email: String): Unit =
        suspendCancellableCoroutine { continuation ->
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful)
                    continuation.resume(Unit)
                else
                    continuation.resumeWithException(FirebasePasswordRecoverException(it.exception?.message))
            }
        }


}