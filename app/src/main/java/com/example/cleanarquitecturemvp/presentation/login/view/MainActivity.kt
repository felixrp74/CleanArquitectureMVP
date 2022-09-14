package com.example.cleanarquitecturemvp.presentation.login.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.cleanarquitecturemvp.base.BaseActivity
import com.example.cleanarquitecturemvp.databinding.ActivityMainBinding
import com.example.cleanarquitecturemvp.presentation.login.LoginContract

class MainActivity : BaseActivity<ActivityMainBinding>(), LoginContract.View {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            Toast.makeText(this, "TIENE HAMBRE", Toast.LENGTH_SHORT).show()
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
        showToast(this, "hola lluvia")

    }

    override fun signUp() {
        TODO("Not yet implemented")
    }
}