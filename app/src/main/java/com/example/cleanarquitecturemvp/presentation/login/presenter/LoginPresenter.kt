package com.example.cleanarquitecturemvp.presentation.login.presenter

import com.example.cleanarquitecturemvp.presentation.login.LoginContract

class LoginPresenter( ) : LoginContract.LoginPresenter {

    private var view: LoginContract.LoginView? = null

    override fun attachView(view: LoginContract.LoginView) {
        this.view = view
    }

    override fun deattachedView() {
        this.view = null
    }

    override fun isViewAttached(): Boolean {
        return this.view != null
    }

    override fun signInUserWithEmailAndPassword(email: String, password: String) {
        view?.showProgressBar()
        view?.showError("hola")

    }

    override fun checkEmptyFields(email: String, password: String):Boolean {
        return email.isEmpty() || password.isEmpty()
    }


}