package com.sw.wordgarden.presentation.ui.mypage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.ActivityMypageBinding

class MyPageActivity : AppCompatActivity() {

    private val binding: ActivityMypageBinding by lazy {
        ActivityMypageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        uiSetting()
        setFragment(savedInstanceState)
    }

    private fun uiSetting() {
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.clMyMain) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setFragment(bundle: Bundle?) {
        if (bundle == null){ supportFragmentManager.beginTransaction()
            .replace(R.id.cl_my_main, MypageFragment())
            .commit()}
    }
}