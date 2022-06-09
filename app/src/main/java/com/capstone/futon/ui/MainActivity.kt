package com.capstone.futon.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import com.capstone.futon.R
import com.capstone.futon.databinding.ActivityMainBinding
import com.capstone.futon.ui.form.login.LoginActivity
import com.capstone.futon.ui.list.ListActivity
import com.capstone.futon.ui.scan.ScanActivity
import com.capstone.futon.ui.upload.UploadActivity
import com.capstone.futon.utils.DataStoreViewModel
import com.uk.tastytoasty.TastyToasty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataStoreViewModel by viewModels<DataStoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Home"


        setupViewModel()

        binding.btnList.setOnClickListener {
            startActivity(Intent(this@MainActivity, ListActivity::class.java))
        }

        binding.btnScan.setOnClickListener {
            startActivity(Intent(this@MainActivity, ScanActivity::class.java))
        }

        binding.btnUpload.setOnClickListener {
            startActivity(Intent(this@MainActivity, UploadActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            dataStoreViewModel.logout()
            Log.d("tag", "token dihapus")
            //Log.d("tag", mLoginPreference.getUser().isLogin.toString())
            TastyToasty.success(this, "Berhasil Logout").show()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()

        }
    }

    private fun setupViewModel() {
        dataStoreViewModel.getSession().observe(this) { userSession ->
            if (!userSession.sessionStatus) {
                TastyToasty.success(this, "token tidak ada, login dulu yuk").show()
                Log.d("tag", userSession.token)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity as Activity).toBundle()
                )
                finish()
            } else {
                //
            }
        }

    }
}