package com.example.cleanarquitecturemvp.presentation.register.presenter

import androidx.core.util.PatternsCompat
import com.example.cleanarquitecturemvp.domain.interactor.registerinteractor.SignUpInteractor
import com.example.cleanarquitecturemvp.presentation.login.LoginContract
import com.example.cleanarquitecturemvp.presentation.register.RegisterContract

class SignUpPresenter (val signUpInteractor: SignUpInteractor): RegisterContract.RegisterPresenter {

    private var view: RegisterContract.RegisterView? = null

    override fun attachView(view: RegisterContract.RegisterView) {
        this.view = view
    }

    override fun detachedView() {
        this.view = null
    }

    override fun isViewAttached(): Boolean {
        return this.view != null
    }

    override fun checkEmptyFullname(fullname: String): Boolean {
        return fullname.isEmpty()
    }

    override fun checkEmptyEmail(email: String): Boolean {
        return email.isEmpty()
    }

    override fun checkEmptyPasswords(password1: String, password2: String): Boolean {
        return password1.isEmpty() || password2.isEmpty()
    }

    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkPasswordsMatch(password1: String, password2: String): Boolean {
        return password1 == password2
    }

    override fun signUp(fullname: String, email: String, password: String) {

        view?.showProgressBar()

        signUpInteractor.signUp(fullname, email, password, object:SignUpInteractor.SignUpCallback{
            override fun onSignUpSuccess() {
                if (isViewAttached()){
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }
            }

            override fun onSignUpFailure(errorMsg: String) {
                view?.hideProgressBar()
                view?.showError(errorMsg)
            }

        })
    }


}