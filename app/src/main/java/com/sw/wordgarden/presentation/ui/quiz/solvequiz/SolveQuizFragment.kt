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
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.QAModel
import com.sw.wordgarden.presentation.model.QuizKey
import com.sw.wordgarden.presentation.model.QuizModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SolveQuizFragment : Fragment() {
    private var _binding: FragmentSolveQuizBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: SolveQuizViewModel by viewModels()
    private val enteredAnswers: List<QAModel> = List(10) { QAModel("", "", "", "", null) }
    private var qTitle = ""
    private var sqId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSolveQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setupListener()
        setupObserver()
    }

    private fun getData() {
        val args: SolveQuizFragmentArgs by navArgs()
        val quizModel = args.argsQuizModel
        val questionList = quizModel?.qaList ?: emptyList()
        qTitle = quizModel?.qTitle ?: ""
        sqId = quizModel?.sqId ?: ""

        setupUi(questionList)
    }

    private fun setupUi(questionList: List<QAModel>) = with(binding) {
        val indicatorAdapter = IndicatorAdapter(questionList.size) { position ->
            vpSolveQuiz.setCurrentItem(position, true)
        }

        val pagerAdapter = SolveQuizAdapter(
            this@SolveQuizFragment,
            questionList
        ) { position, question, answer, isFull ->
            enteredAnswers[position].question = question
            enteredAnswers[position].userAnswer = answer

            if (isFull) {
                indicatorAdapter.markAsFilled(position)
                if (position < questionList.size - 1) {
                    vpSolveQuiz.setCurrentItem(position + 1, true)
                } else {
                    checkQuiz()
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
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    indicatorAdapter.updateSelectedPosition(position)
                }
            })
        }
    }

    private fun checkQuiz() {
        val hasEmptyValue = this.enteredAnswers.any { it.userAnswer.isNullOrEmpty() }
        if (hasEmptyValue) {
            ToastMaker.make(requireContext(), R.string.solve_quiz_msg_need_all_check)
        } else {
            val quizModelForCheck = QuizModel(
                sqId = sqId,
                qTitle = qTitle,
                qaList = enteredAnswers
            )

            viewmodel.submitAnswer(quizModelForCheck)
        }
    }

    private fun setupListener() = with(binding) {
        btnSolveQuizBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.submitEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        val quizKey = QuizKey(
                            qTitle = qTitle,
                            sqId = sqId,
                            sqId.isEmpty() || sqId.isBlank()
                        )
                        goResult(quizKey)
                    }
                }
            }
        }
    }

    private fun goResult(quizKey: QuizKey) {
        val action =
            SolveQuizFragmentDirections.actionSolveQuizFragmentToResultQuizFragment(quizKey)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}