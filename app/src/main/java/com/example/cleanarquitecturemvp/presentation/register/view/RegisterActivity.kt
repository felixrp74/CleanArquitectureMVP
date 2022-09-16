package com.example.cleanarquitecturemvp.presentation.register.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.cleanarquitecturemvp.base.BaseActivity
import com.example.cleanarquitecturemvp.databinding.ActivityRegisterBinding
import com.example.cleanarquitecturemvp.domain.interactor.registerinteractor.SignUpInteractorImpl
import com.example.cleanarquitecturemvp.presentation.login.view.HomeActivity
import com.example.cleanarquitecturemvp.presentation.login.view.MainActivity
import com.example.cleanarquitecturemvp.presentation.register.RegisterContract
import com.example.cleanarquitecturemvp.presentation.register.presenter.SignUpPresenter

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(), RegisterContract.RegisterView {


    private lateinit var presenter: SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SignUpPresenter(SignUpInteractorImpl())
        presenter.attachView(this)

        binding.btnSignUp.setOnClickListener {
            signUp()
        }

    }

    override fun getViewBinding() = ActivityRegisterBinding.inflate(layoutInflater)

    override fun showError(msgError: String) {
        toast(this, msgError)
    }

    override fun showProgressBar() {
        binding.progressBarSignUp.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBarSignUp.visibility = View.GONE
    }

    override fun signIn() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }
    }

    override fun signUp() {

        val fullname = binding.etFullNameSignUp.text.trim().toString()
        val email = binding.etEmailSignUp.text.trim().toString()
        val password1 = binding.etPassword1SignUp.text.trim().toString()
        val password2 = binding.etPassword2SignUp.text.trim().toString()

        if (presenter.checkEmptyFullname(fullname)) {
            binding.etFullNameSignUp.error = "This is empty"
            return
        }
        if (presenter.checkEmptyEmail(email)) {
            binding.etEmailSignUp.error = "This is empty"
            return
        }
        if (presenter.checkEmptyPasswords(password1,password2)) {
            binding.etPassword1SignUp.error = "Password are empty"
            binding.etPassword2SignUp.error = "Password are empty"
            return
        }
        if (!presenter.checkPasswordsMatch(password1,password2)) {
            binding.etPassword1SignUp.error = "Passwords are different"
            binding.etPassword2SignUp.error = "Passwords are different"
            return
        }
        if (!presenter.checkValidEmail(email)){
            binding.etEmailSignUp.error = "Email invalid"
            return
        }

        presenter.signUp(fullname,email,password1)
    }

    override fun navigateToMain() {
        Intent(this, HomeActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachedView()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachedView()
    }

}