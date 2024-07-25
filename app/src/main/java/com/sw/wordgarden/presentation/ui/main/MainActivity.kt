package com.sw.wordgarden.presentation.ui.main

import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.ActivityMainBinding
import com.sw.wordgarden.presentation.ui.quiz.quiz.QuizFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.clMain) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getUidTest()
        goQuiz()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }

    private fun getUidTest() {
        viewmodel.getUid()

        lifecycleScope.launch {
            viewmodel.uid.flowWithLifecycle(lifecycle).collectLatest { uid ->
                binding.tvMain.text = uid
            }
        }
    }

    /**
     * quiz fragment 확인을 위한 테스트 코드입니다.
     */
    private fun goQuiz() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.cl_main, QuizFragment())
            .commit()
    }
}