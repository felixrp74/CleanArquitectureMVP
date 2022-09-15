package com.example.cleanarquitecturemvp.presentation.login.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.cleanarquitecturemvp.base.BaseActivity
import com.example.cleanarquitecturemvp.databinding.ActivityMainBinding
import com.example.cleanarquitecturemvp.presentation.login.LoginContract
import com.example.cleanarquitecturemvp.presentation.login.presenter.LoginPresenter

class MainActivity : BaseActivity<ActivityMainBinding>(), LoginContract.LoginView {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = LoginPresenter()
        presenter.attachView(this)

        binding.btnSignIn.setOnClickListener {
            signIn()
        }

    }

    override fun showError(msgError: String) {
        Toast.makeText(applicationContext, msgError, Toast.LENGTH_SHORT).show()
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

    override fun signUp() {
        TODO("Not yet implemented")
    }

    override fun navigateToMain() {
        TODO("Not yet implemented")
    }

    override fun navigateToRegister() {
        TODO("Not yet implemented")
    }
}
