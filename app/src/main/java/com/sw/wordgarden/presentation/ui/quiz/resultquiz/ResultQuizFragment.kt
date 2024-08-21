package com.sw.wordgarden.presentation.ui.quiz.resultquiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentResultQuizBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizKey
import com.sw.wordgarden.presentation.model.QuizModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResultQuizFragment : Fragment() {

    private var _binding: FragmentResultQuizBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: ResultQuizViewModel by viewModels()
    private var quizKey = QuizKey("", "", null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                goQuizOrBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setupListener()
        setupObserver()
    }

    private fun getData() {
        val args: ResultQuizFragmentArgs by navArgs()
        quizKey = args.argsQuizKey ?: QuizKey("", "", null)
        viewmodel.getResult(quizKey)
    }

    private fun setupListener() = with(binding) {
        btnResultQuizExit.setOnClickListener {
            goQuizOrBack()
        }

        btnResultFriend.setOnClickListener {
            val action =
                ResultQuizFragmentDirections.actionResultQuizFragmentToShareQuizFragment(quizKey)
            findNavController().navigate(action)
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getResultEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getResult.flowWithLifecycle(lifecycle).collectLatest { result ->
                setupUi(result)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupUi(result: QuizModel?) = with(binding) {
        val quizList = result?.qaList ?: emptyList()

        //-- 상단 text --
        val correctNumber = quizList.count { it.correct == true }
        val resultText = when (correctNumber) {
            0 -> getString(R.string.result_quiz_title_all_incorrect)
            10 -> getString(R.string.result_quiz_title_all_correct)
            else -> getString(R.string.result_quiz_title_total) + "\n" + "$correctNumber${
                getString(
                    R.string.result_quiz_title_correct
                )
            }"
        }

        tvResultQuizResult.text = resultText

        //-- indicator --
        val indicatorAdapter = IndicatorAdapter(quizList.size, quizList) { position ->
            vpResultQuiz.setCurrentItem(position, true)
        }

        rvResultQuizIndicator.apply {
            adapter = indicatorAdapter
            layoutManager = GridLayoutManager(context, 5)
        }

        //-- viewpager --
        val pagerAdapter = ResultQuizAdapter(this@ResultQuizFragment, quizList) { _, _, _, _, _ -> }

        vpResultQuiz.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    indicatorAdapter.updateSelectedPosition(position)
                }
            })
        }
    }

    private fun goQuizOrBack() {
        val navController = findNavController()
        if (navController.previousBackStackEntry?.destination?.id == R.id.solveQuizFragment) {
            val fromQuiz =
                navController.previousBackStackEntry?.arguments?.getBoolean("argsFromQuiz") ?: false
            if (fromQuiz) {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.quizFragment, true)
                    .setLaunchSingleTop(true)
                    .build()
                navController.navigate(
                    R.id.action_resultQuizFragment_to_quizFragment, null, navOptions)
            } else {
                navController.navigateUp()
            }
        } else {
            navController.navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}