package com.sw.wordgarden.presentation.ui.quiz.solvequiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentSolveQuizBinding
import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.QuestionAnswerModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SolveQuizFragment : Fragment() {
    private var _binding: FragmentSolveQuizBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: SolveQuizViewModel by viewModels()
    private val enteredAnswers: List<QuestionAnswerModel> = List(10) { QuestionAnswerModel("", "") }

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSolveQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataFromStart()
        setupListener()
        setupObserver()
    }

    private fun getDataFromStart() {
        val args: SolveQuizFragmentArgs by navArgs()
        val quiz = args.argsQuizEntity ?: QuizListEntity("", emptyList(), emptyList())
        setupUi(quiz)
    }

    private fun setupUi(quiz: QuizListEntity) = with(binding) {
        val quizList = quiz.quiz

        if (quizList != null) {
            val indicatorAdapter = IndicatorAdapter(quizList.size) { position ->
                vpSolveQuiz.setCurrentItem(position, true)
            }

            val pagerAdapter = SolveQuizAdapter(this@SolveQuizFragment, quiz.quiz) { position, question, answer, isFull ->
                enteredAnswers[position].question = question
                enteredAnswers[position].answer = answer

                if (isFull) {
                    indicatorAdapter.markAsFilled(position)

                    if (position < quiz.quiz.size - 1) {
                        vpSolveQuiz.setCurrentItem(position + 1, true)
                    } else {
                        checkQuiz(quiz)
                    }
                } else {
                    indicatorAdapter.markAsEmpty(position)
                }
            }

            rvSolveQuizIndicator.apply {
                adapter = indicatorAdapter
                layoutManager = GridLayoutManager(context, 5)
            }

            vpSolveQuiz.apply {
                adapter = pagerAdapter
                registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        indicatorAdapter.updateSelectedPosition(position)
                    }
                })
            }
        }
    }

    private fun checkQuiz(quiz: QuizListEntity) {
        val hasEmptyValue = this.enteredAnswers.any { it.answer.isEmpty() }
        if (hasEmptyValue) {
            ToastMaker.make(requireContext(), R.string.solve_quiz_msg_need_all_check)
        } else {
            viewmodel.checkQuizAnswer(quiz, enteredAnswers)
        }
    }

    private fun setupListener() = with(binding) {
        btnSolveQuizBack.setOnClickListener {
            findNavController().navigate(R.id.action_solveQuizFragment_to_quizFragment)
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.sendQuizEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        goResult(viewmodel.checkQuizResult.value)
                    }
                }
            }
        }
    }

    private fun goResult(result: QuizListEntity?) {
        val action = SolveQuizFragmentDirections.actionSolveQuizFragmentToResultQuizFragment(result)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}