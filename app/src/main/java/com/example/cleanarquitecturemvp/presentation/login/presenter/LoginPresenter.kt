package com.example.cleanarquitecturemvp.presentation.login.presenter

import com.example.cleanarquitecturemvp.domain.interactor.logininteractor.SignInInteractor
import com.example.cleanarquitecturemvp.presentation.login.LoginContract
import com.example.cleanarquitecturemvp.presentation.login.exceptions.FirebaseLoginException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginPresenter(private val signInInteractor: SignInInteractor) : LoginContract.LoginPresenter,
    CoroutineScope {

    private var view: LoginContract.LoginView? = null

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun attachView(view: LoginContract.LoginView) {
        this.view = view
    }

    override fun detachedView() {
        this.view = null
    }

    override fun detachedJob() {
        coroutineContext.cancel()
    }

    override fun isViewAttached(): Boolean {
        return this.view != null
    }

    override fun signInUserWithEmailAndPassword(email: String, password: String) {

        launch {
            view?.showProgressBar()
            try {
                signInInteractor.signInWithEmailAndPassword(email, password)

                if (isViewAttached()) {
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }
            } catch (e: FirebaseLoginException) {

                if (isViewAttached()) {
                    view?.hideProgressBar()
                    view?.showError(e.message)
                }
            }
        }
    }

    override fun checkEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() || password.isEmpty()
    }


}