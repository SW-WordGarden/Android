package com.sw.wordgarden.presentation.ui.main

import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.ActivityMainBinding
import com.sw.wordgarden.presentation.ui.login.login.LoginFragment
import com.sw.wordgarden.presentation.ui.mypage.MypageFragment
import com.sw.wordgarden.presentation.ui.quiz.quiz.QuizFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSplash()
        setupUi()
        goLogin()

        /**
         * test code
         * TODO: nav 개발 후 테스트 코드 삭제
         */
//        goQuiz()
//        goMy()
        /**
         * test code end
         */
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }

    private fun setupSplash() {
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewmodel.isLoading.value }
        }
    }

    private fun setupUi() {
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.clMain) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * login fragment 확인을 위한 테스트 코드입니다.
     */
    private fun goLogin() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.cl_main, LoginFragment())
            .commit()
    }

    /**
     * quiz fragment 확인을 위한 테스트 코드입니다.
     */
    private fun goQuiz() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.cl_main, QuizFragment())
            .commit()
    }

    /**
     * mypage fragment 확인을 위한 테스트 코드입니다.
     */
    private fun goMy() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.cl_main, MypageFragment())
            .commit()
    }
}