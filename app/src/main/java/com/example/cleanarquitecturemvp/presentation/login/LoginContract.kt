package com.example.cleanarquitecturemvp.presentation.login

interface LoginContract {

    interface LoginView{
        fun showError(msgError:String?)
        fun showProgressBar()
        fun hideProgressBar()
        fun signIn()
        fun navigateToPasswordRecover()
        fun navigateToMain()
        fun navigateToRegister()
    }

    interface LoginPresenter{
        fun attachView(view:LoginView)
        fun detachedView()
        fun detachedJob()
        fun isViewAttached():Boolean
        fun signInUserWithEmailAndPassword(email:String,password:String)
        fun checkEmptyFields(email:String,password: String):Boolean
    }

}