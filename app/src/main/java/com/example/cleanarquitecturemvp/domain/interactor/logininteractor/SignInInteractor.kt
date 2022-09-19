package com.example.cleanarquitecturemvp.domain.interactor.logininteractor

interface SignInInteractor {

    interface SignInCallback {
        fun onSignInSuccess()
        fun onSignInFailure(errorMsg: String)
    }

    suspend fun signInWithEmailAndPassword(email: String, password: String)


}