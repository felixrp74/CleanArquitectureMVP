package com.example.cleanarquitecturemvp.presentation.passwordrecover.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.cleanarquitecturemvp.base.BaseActivity
import com.example.cleanarquitecturemvp.databinding.ActivityPasswordRecoverBinding
import com.example.cleanarquitecturemvp.domain.interactor.passwordrecoverinteractor.PasswordRecoverInteractorImpl
import com.example.cleanarquitecturemvp.presentation.login.view.MainActivity
import com.example.cleanarquitecturemvp.presentation.passwordrecover.PasswordRecoverContract
import com.example.cleanarquitecturemvp.presentation.passwordrecover.presenter.PasswordRecoverPresenter

class PasswordRecoverActivity : BaseActivity<ActivityPasswordRecoverBinding>(),
    PasswordRecoverContract.PasswordRecoverView {

    private lateinit var presenter : PasswordRecoverPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = PasswordRecoverPresenter(PasswordRecoverInteractorImpl())
        presenter.attachView(this)

        binding.btnSendEmailRecoverPass.setOnClickListener {
            recoverPassword()
        }
    }

    override fun getViewBinding() = ActivityPasswordRecoverBinding.inflate(layoutInflater)

    override fun showError(msgError: String?) {
        toast(this,msgError)
    }

    override fun showProgressBar() {
        binding.progressBarRecoverPass.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBarRecoverPass.visibility = View.GONE
    }

    override fun recoverPassword() {
        val email = binding.etRecoverPass.text.trim().toString()
        if (!email.isEmpty())
        presenter.sendEmailRecoverPassword(email)
        else binding.etRecoverPass.error = "Empty email"
    }

    override fun navigateToLogin() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachedView()
        presenter.detachedJob()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachedView()
        presenter.detachedJob()
    }


}