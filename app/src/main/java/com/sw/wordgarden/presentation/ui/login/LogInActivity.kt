package com.sw.wordgarden.presentation.ui.login

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewmodel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSplash()
        setupUi()
        setFragment(savedInstanceState)
    }

    private fun setupSplash() {
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewmodel.isLoading.value }
        }
    }

    private fun setupUi() {
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.clLoginMain) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setFragment(bundle: Bundle?) {
        if (bundle == null){ supportFragmentManager.beginTransaction()
            .replace(R.id.cl_login_main, LoginFragment())
            .commit()}
    }
}