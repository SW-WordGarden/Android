package com.sw.wordgarden.presentation.ui.quiz.startquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.databinding.FragmentStartQuizBinding
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.QuestionModel
import com.sw.wordgarden.presentation.model.QuestionResultModel
import com.sw.wordgarden.presentation.model.SelfQuizModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.sql.Timestamp

@AndroidEntryPoint
class StartQuizFragment : Fragment() {
    private var _binding: FragmentStartQuizBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: StartQuizViewModel by viewModels()
    private lateinit var quiz: SelfQuizModel
    private var limitCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setupListener()
    }

    private fun getData() {
        val args: StartQuizFragmentArgs by navArgs()
        limitCount = args.argsLimit
        viewmodel.saveDailyLimit(limitCount)

        val quizFromAlarm = args.argsQuizModel
        if (quizFromAlarm == null) { //신규 퀴즈 받아오는 상황
//        setupObserver() //TODO:api 개발 완료 시 주석 해제
            setupObserverTest() //api 개발 전까지 test용으로 실행되는 함수
        } else { //공유 받은 퀴즈 받아오는 상황
            quiz = quizFromAlarm
        }
    }

    private fun setupListener() = with(binding) {
        btnStartQuizBack.setOnClickListener {
            findNavController().navigateUp()
        }

        btnStartQuizStart.setOnClickListener {
            val action =
                StartQuizFragmentDirections.actionStartQuizFragmentToSolveQuizFragment(quiz)
            findNavController().navigate(action)
        }
    }

    private fun setupObserverTest() {
        val time = Timestamp(2024, 8, 1, 10, 11, 12, 1)
        val quizList: List<QuestionModel> = List(10) { QuestionModel("question", "answer", 0) }
        val quizResult: List<QuestionResultModel> =
            List(10) { QuestionResultModel("userAnswer", true, time, 0) }

        quiz = SelfQuizModel(
            quizId = "quizIdTest1",
            title = "test title",
            quiz = quizList,
            quizResult = quizResult
        )

        setupUi()

        lifecycleScope.launch {
            viewmodel.saveDailyLimitEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getQuizEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getQuiz.flowWithLifecycle(lifecycle).collectLatest { quizData ->
                if (quizData != null) {
                    quiz = quizData

                    setupUi()
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.saveDailyLimitEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }
    }

    private fun setupUi() = with(binding) {
        tvStartQuizIntroduce.text = quiz.title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}