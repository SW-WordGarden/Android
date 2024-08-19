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
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartQuizFragment : Fragment() {
    private var _binding: FragmentStartQuizBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: StartQuizViewModel by viewModels()
    private lateinit var quiz: QuizModel

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
        setupObserver()
    }

    private fun getData() {
        val args: StartQuizFragmentArgs by navArgs()
        val quizKey = args.argsQuizKey

        if (quizKey != null) { //공유 받은 퀴즈
            if (quizKey.isWq == true) { //wq 퀴즈
                viewmodel.getQuizFromWq()
            } else { //sq 퀴즈
                viewmodel.getQuizFromSq("", quizKey.sqId ?: "")
            }
        } else { //wq 퀴즈
            viewmodel.getQuizFromWq()
        }
    }

    private fun setupListener() = with(binding) {
        btnStartQuizBack.setOnClickListener {
            findNavController().navigateUp()
        }

        btnStartQuizStart.setOnClickListener {
            val action =
                StartQuizFragmentDirections.actionStartQuizFragmentToSolveQuizFragmentForWq(quiz)
            findNavController().navigate(action)
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
    }

    private fun setupUi() = with(binding) {
        tvStartQuizIntroduce.text = quiz.qTitle
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}