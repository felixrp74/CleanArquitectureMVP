package com.example.cleanarquitecturemvp.presentation.register

interface RegisterContract {
    interface RegisterView{
        fun showError(msgError:String)
        fun showProgressBar()
        fun hideProgressBar()
        fun signIn()
        fun signUp()
        fun navigateToMain()
    }

    interface RegisterPresenter{
        fun attachView(view:RegisterView)
        fun detachedView()
        fun isViewAttached():Boolean
        fun checkEmptyFullname(fullname:String):Boolean
        fun checkEmptyEmail(email:String):Boolean
        fun checkEmptyPasswords(password1: String, password2: String):Boolean
        fun checkValidEmail(email:String):Boolean
        fun checkPasswordsMatch(password1: String, password2: String):Boolean
        fun signUp(fullname: String,email:String,password:String)
    }
}