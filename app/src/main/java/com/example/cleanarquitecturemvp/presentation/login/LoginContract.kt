package com.example.cleanarquitecturemvp.presentation.login

interface LoginContract {

    interface LoginView{
        fun showError(msgError:String)
        fun showProgressBar()
        fun hideProgressBar()
        fun signIn()
        fun signUp()
        fun navigateToMain()
        fun navigateToRegister()
    }

    interface LoginPresenter{
        fun attachView(view:LoginView)
        fun deattachedView()
        fun isViewAttached():Boolean
        fun signInUserWithEmailAndPassword(email:String,password:String)
        fun checkEmptyFields(email:String,password: String):Boolean
    }

}