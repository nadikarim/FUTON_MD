package com.capstone.futon.ui.form.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.futon.data.Session
import com.capstone.futon.databinding.ActivityLoginBinding
import com.capstone.futon.ui.main.MainActivity
import com.capstone.futon.ui.form.FormViewModel
import com.capstone.futon.ui.form.register.RegisterActivity
import com.capstone.futon.utils.DataStoreViewModel
import com.uk.tastytoasty.TastyToasty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: FormViewModel by viewModels()
    private val dataStoreViewModel by viewModels<DataStoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //TastyToasty.green(this, "Selamat datang di FUTON", null).show()
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.trim().toString()
            val password = binding.etPassword.text.trim().toString()
            Log.d("TAG", email)
            Log.d("TAG", password)

            viewModel.login2(email, password)
            viewModel.userLogin.observe(this) {
                if (it != null) {
                    Log.d("TAG", "Token masuk: $it")
                    savedSession(Session(it, true))
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }

            }
            viewModel.toast.observe(this) {
                TastyToasty.error(this, it).show()
            }
        }

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun savedSession(session: Session) {
        dataStoreViewModel.setSession(session)
    }
}