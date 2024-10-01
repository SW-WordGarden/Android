package com.sw.wordgarden.presentation.ui.quiz.solvequiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
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
import com.sw.wordgarden.presentation.ui.loading.LoadingDialog
import com.sw.wordgarden.presentation.util.Constants.QUIZ_AMOUNT
import com.sw.wordgarden.presentation.util.KeyboardCleaner
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SolveQuizFragment : Fragment() {
    private var _binding: FragmentSolveQuizBinding? = null
    private val binding get() = _binding!!

    private var loadingDialog: LoadingDialog? = null
    private val viewmodel: SolveQuizViewModel by viewModels()
    private val enteredAnswers: List<QAModel> = List(QUIZ_AMOUNT) { QAModel.emptyQAModel() }
    private var qTitle = ""
    private var sqId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                goBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSolveQuizBinding.inflate(inflater, container, false)
        val rootView = binding.root
        KeyboardCleaner.setup(rootView, this)
        return rootView
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
            questionList,
            enteredAnswers
        ) { position, qid, question, _, answer, _, isFull, isNext ->
            enteredAnswers[position].question = question
            enteredAnswers[position].userAnswer = answer
            enteredAnswers[position].questionId = qid

            if (isNext) {
                vpSolveQuiz.setCurrentItem(position + 1, true)
            }

            if (isFull) {
                indicatorAdapter.markAsFilled(position)
                if (position == QUIZ_AMOUNT - 1) {
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

            val builder = AlertDialog.Builder(requireActivity())
            builder.setMessage(R.string.solve_quiz_msg_use_count)
            builder.setPositiveButton(R.string.common_positive) { _, _ ->
                viewmodel.submitAnswers(quizModelForCheck)
            }
            builder.setNegativeButton(R.string.common_negative) { _, _ -> }
            builder.show()
        }
    }

    private fun setupListener() = with(binding) {
        btnSolveQuizBack.setOnClickListener {
            btnSolveQuizBack.isEnabled = false
            goBack()
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

        lifecycleScope.launch {
            viewmodel.uiState.flowWithLifecycle(lifecycle).collectLatest { state ->
                if (state.isLoading) {
                    loadingDialog = LoadingDialog()
                    loadingDialog?.show(parentFragmentManager, null)
                } else {
                    loadingDialog?.dismiss()
                    loadingDialog = null
                }
            }
        }
    }

    private fun goBack() {
        val navController = findNavController()
        val currentDestination = navController.currentDestination?.id

        if (currentDestination == R.id.solveQuizFragment) {
            navController.navigate(R.id.action_solveQuizFragment_to_quizFragment)
        }
    }

    private fun goResult(quizKey: QuizKey) {
        val action =
            SolveQuizFragmentDirections.actionSolveQuizFragmentToResultQuizFragment(quizKey, true)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}