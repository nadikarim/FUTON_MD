package com.capstone.futon.ui.form.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.capstone.futon.R
import com.capstone.futon.data.Session
import com.capstone.futon.databinding.ActivityLoginBinding
import com.capstone.futon.databinding.ActivityRegisterBinding
import com.capstone.futon.ui.MainActivity
import com.capstone.futon.ui.form.FormViewModel
import com.capstone.futon.ui.form.login.LoginActivity
import com.capstone.futon.utils.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: FormViewModel by viewModels()
    private val dataStoreViewModel by viewModels<DataStoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.trim().toString()
            val email = binding.etEmail.text.trim().toString()
            val password = binding.etPassword.text.trim().toString()
            Log.d("TAG", name)
            Log.d("TAG", email)
            Log.d("TAG", password)

            viewModel.register(name, email, password)

            viewModel.toast.observe(this) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
        
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }
}