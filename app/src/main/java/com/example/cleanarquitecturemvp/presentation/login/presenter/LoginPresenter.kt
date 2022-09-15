package com.example.cleanarquitecturemvp.presentation.login.presenter

import com.example.cleanarquitecturemvp.domain.interactor.logininteractor.SignInInteractor
import com.example.cleanarquitecturemvp.presentation.login.LoginContract

class LoginPresenter(private val signInInteractor: SignInInteractor) : LoginContract.LoginPresenter {

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
        signInInteractor.SignInWithEmailAndPassword(email,password,object : SignInInteractor.SignInCallback{

            override fun onSignInSuccess() {
                if (isViewAttached()){
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }
            }

            override fun onSignInFailure(errorMsg: String) {
                if (isViewAttached()){
                    view?.hideProgressBar()
                    view?.showError(errorMsg)
                }
            }

        })
    }

    override fun checkEmptyFields(email: String, password: String):Boolean {
        return email.isEmpty() || password.isEmpty()
    }

}