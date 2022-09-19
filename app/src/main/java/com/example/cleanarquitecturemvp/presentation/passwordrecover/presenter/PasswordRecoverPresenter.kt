package com.example.cleanarquitecturemvp.presentation.passwordrecover.presenter

import com.example.cleanarquitecturemvp.domain.interactor.passwordrecoverinteractor.PasswordRecoverInteractor
import com.example.cleanarquitecturemvp.presentation.passwordrecover.PasswordRecoverContract
import com.example.cleanarquitecturemvp.presentation.passwordrecover.exceptions.FirebasePasswordRecoverException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PasswordRecoverPresenter(val passwordRecoverInteractor: PasswordRecoverInteractor) :
    PasswordRecoverContract.PasswordRecoverPresenter, CoroutineScope {
    var view:PasswordRecoverContract.PasswordRecoverView? = null

    var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun attachView(view: PasswordRecoverContract.PasswordRecoverView) {
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

    override fun sendEmailRecoverPassword(email: String) {

        launch {
            view?.showProgressBar()
            try {
                //this is a suspend function
                passwordRecoverInteractor.sendEmailResetPassword(email)

                if (isViewAttached()) {
                    view?.hideProgressBar()
                    view?.navigateToLogin()
                }
            } catch (e: FirebasePasswordRecoverException) {

                if (isViewAttached()) {
                    view?.hideProgressBar()
                    view?.showError(e.message)
                }
            }
        }
    }
}