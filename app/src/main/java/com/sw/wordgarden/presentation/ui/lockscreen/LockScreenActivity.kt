package com.sw.wordgarden.presentation.ui.lockscreen

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sw.wordgarden.databinding.ActivityLockScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class LockScreenActivity : AppCompatActivity() {
    private val binding:ActivityLockScreenBinding by lazy {
        ActivityLockScreenBinding.inflate(layoutInflater)
    }
    //private val viewModel:LockScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.answer.visibility = AppCompatTextView.INVISIBLE
        binding.correctAnswer.visibility = AppCompatTextView.INVISIBLE

        setupUi()
        //setUpClock()
        //initViewModel()
        buttonEvent()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            //setTurnScreenOn(true)
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        } else {
            this.window.addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        }

    }
    private fun buttonEvent(){
        binding.lockTrue.setOnClickListener {
            binding.answer.visibility = AppCompatTextView.VISIBLE
            binding.correctAnswer.visibility = AppCompatTextView.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
            }, 3000)
        }
        binding.lockFalse.setOnClickListener {
            binding.answer.visibility = AppCompatTextView.VISIBLE
            binding.correctAnswer.visibility = AppCompatTextView.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
            }, 3000)
        }
    }
//    private fun initViewModel(){
//        lifecycleScope.launch {
//            viewModel.quizData.flowWithLifecycle(lifecycle).collectLatest { data ->
//                if (data != null) {
//                    binding.lockTitle.text = data.title
//                    binding.lockDescription.text = data.question
//                    binding.correctAnswer.text = data.correctAnswer
//                }
//            }
//        }
//    }
    private fun setUpClock(){
        while (true){
            Handler(Looper.getMainLooper()).postDelayed({
                val current = LocalDateTime.now()
                val timeFormatter = DateTimeFormatter.ofPattern("HH : mm")
                val dateFormatter = DateTimeFormatter.ofPattern("MM.DD")

                val time = current.format(timeFormatter)
                val date = current.format(dateFormatter)

                binding.time.text = time
                binding.date.text = date
            }, 20000)
        }
    }
    private fun setupUi() {
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.clLock) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}