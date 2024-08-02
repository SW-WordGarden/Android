package com.sw.wordgarden.presentation.ui.quiz.resultquiz

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentResultQuizBinding
import com.sw.wordgarden.domain.entity.QuizListEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultQuizFragment : Fragment() {

    private var _binding: FragmentResultQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataFromSolve()
        setupListener()
    }

    private fun getDataFromSolve() {
        setFragmentResultListener(SOLVE_TO_RESULT_BUNDLE_KEY) { _, bundle ->
            val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(SOLVE_TO_RESULT_BUNDLE_KEY, QuizListEntity::class.java)
                    ?: QuizListEntity("", emptyList(), emptyList())
            } else {
                bundle.getParcelable(SOLVE_TO_RESULT_BUNDLE_KEY)
                    ?: QuizListEntity("", emptyList(), emptyList())
            }

            setupUi(result)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupUi(result: QuizListEntity) = with(binding) {
        val quizList = result.quiz
        val resultList = result.quizResult

        if (quizList != null && resultList != null) {
            //-- 상단 text --
            val correctNumber = resultList.count { it.correct == true }
            val resultText =  when (correctNumber) {
                0 -> getString(R.string.result_quiz_title_all_incorrect)
                10 -> getString(R.string.result_quiz_title_all_correct)
                else -> getString(R.string.result_quiz_title_total) + "\n" + "$correctNumber${getString(R.string.result_quiz_title_correct)}"
            }

            tvResultQuizResult.text = resultText

            //-- indicator --
            val indicatorAdapter = IndicatorAdapter(quizList.size, resultList) { position ->
                vpResultQuiz.setCurrentItem(position, true)
            }

            rvResultQuizIndicator.apply {
                adapter = indicatorAdapter
                layoutManager = GridLayoutManager(context, 5)
            }

            //-- viewpager --
            val pagerAdapter = ResultQuizAdapter(this@ResultQuizFragment, quizList, resultList) { _, _, _, _, _ ->  }

            vpResultQuiz.apply {
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

    private fun setupListener() = with(binding) {
        btnResultQuizExit.setOnClickListener {
            //findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SOLVE_TO_RESULT_BUNDLE_KEY = "SOLVE_TO_RESULT_BUNDLE_KEY"
    }
}