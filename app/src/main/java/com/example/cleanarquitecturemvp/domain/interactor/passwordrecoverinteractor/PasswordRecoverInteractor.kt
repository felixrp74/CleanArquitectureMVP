package com.example.cleanarquitecturemvp.domain.interactor.passwordrecoverinteractor

interface PasswordRecoverInteractor {

    suspend fun sendEmailResetPassword(email:String)

}