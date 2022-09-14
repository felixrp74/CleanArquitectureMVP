package com.example.cleanarquitecturemvp.presentation.login

interface LoginContract {

    interface View{
        fun showError(msgError:String)
        fun showProgressBar()
        fun hideProgressBar()
        fun signIn()
        fun signUp()
    }

}