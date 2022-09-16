package com.example.cleanarquitecturemvp.domain.interactor.registerinteractor

interface SignUpInteractor {

    interface SignUpCallback {
        fun onSignUpSuccess()
        fun onSignUpFailure(errorMsg: String)
    }

    fun signUp(fullname: String, email: String, password: String, listener: SignUpCallback)


}