package com.example.cleanarquitecturemvp.presentation.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.cleanarquitecturemvp.base.BaseActivity
import com.example.cleanarquitecturemvp.databinding.ActivityMainBinding
import com.example.cleanarquitecturemvp.domain.interactor.logininteractor.SignInInteractorImpl
import com.example.cleanarquitecturemvp.presentation.login.LoginContract
import com.example.cleanarquitecturemvp.presentation.login.presenter.LoginPresenter
import com.example.cleanarquitecturemvp.presentation.passwordrecover.view.PasswordRecoverActivity
import com.example.cleanarquitecturemvp.presentation.register.view.RegisterActivity

class MainActivity : BaseActivity<ActivityMainBinding>(), LoginContract.LoginView {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = LoginPresenter(SignInInteractorImpl())
        presenter.attachView(this)

        binding.btnSignIn.setOnClickListener {
            signIn()
        }

        binding.btnGoSignUp.setOnClickListener {
            navigateToRegister()
        }

        binding.btnGoPasswordRecover.setOnClickListener {
            navigateToPasswordRecover()
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun showError(msgError: String?) {
        toast(applicationContext,msgError)
    }

    override fun showProgressBar() {
        binding.progressBarSignIn.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBarSignIn.visibility = View.GONE
    }

    override fun signIn() {
        val email = binding.etEmailSignIn.text.trim().toString()
        val password = binding.etPasswordSignIn.text.trim().toString()

        if (presenter.checkEmptyFields(email,password)) toast(this, "Empty fields")
        else presenter.signInUserWithEmailAndPassword(email,password)

    }

    override fun navigateToPasswordRecover() {
        Intent(this, PasswordRecoverActivity::class.java).also {
            startActivity(it)
        }

    }

    override fun navigateToMain() {
        Intent(this, HomeActivity::class.java).also {
            startActivity(it)
        }
    }

    override fun navigateToRegister() {
        Intent(this, RegisterActivity::class.java).also {
            startActivity(it)
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
