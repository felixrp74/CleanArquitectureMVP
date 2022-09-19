package com.example.cleanarquitecturemvp.presentation.passwordrecover

interface PasswordRecoverContract {

    interface PasswordRecoverView{
        fun showError(msgError:String?)
        fun showProgressBar()
        fun hideProgressBar()
        fun recoverPassword()
        fun navigateToLogin()
    }

    interface PasswordRecoverPresenter{
        fun attachView(view:PasswordRecoverView)
        fun detachedView()
        fun detachedJob()
        fun isViewAttached():Boolean
        fun sendEmailRecoverPassword(email:String)
    }



}