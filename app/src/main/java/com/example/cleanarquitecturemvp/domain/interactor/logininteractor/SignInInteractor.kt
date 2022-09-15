package com.example.cleanarquitecturemvp.domain.interactor.logininteractor

interface SignInInteractor {

    interface SignInCallback {
        fun onSignInSuccess()
        fun onSignInFailure(errorMsg: String)
    }

    fun SignInWithEmailAndPassword(email: String, password: String, listener: SignInCallback)


}