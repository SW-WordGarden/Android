package com.sw.wordgarden.presentation.ui.quiz.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentQuizBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizKey
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: QuizViewModel by viewModels()
    private var limitCount = 0
    private val MAX = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
        setupObserver()
    }

    private fun setupListener() = with(binding) {
        btnQuizBack.setOnClickListener {
            findNavController().navigateUp()
        }

        btnQuizAlone.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setMessage(R.string.quiz_msg_use_count)
            builder.setPositiveButton(R.string.common_positive) { _, _ ->
                viewmodel.saveDailyLimit(limitCount)
            }
            builder.setNegativeButton(R.string.common_negative) { _, _ -> }
            builder.show()
        }

        btnMakeQuiz.setOnClickListener {
            goMakeQuiz()
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getDailyLimitEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        setupUi()
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getDailyLimit.flowWithLifecycle(lifecycle).collectLatest { count ->
                if (count != null) {
                    limitCount = count
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.saveDailyLimitEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        goStartQuiz()
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupUi() = with(binding) {
//        ivQuizUserThumbnail.setImageResource()
        if (limitCount >= MAX) {
            tvQuizDescription.text =
                "${getString(R.string.quiz_all_clear)}\n" +
                        "(${MAX}/${MAX})"
            btnQuizAlone.visibility = View.INVISIBLE
        } else {
            tvQuizWelcomeUser.text =
                "${getString(R.string.quiz_welcome)}\n" +
                        "(${limitCount}/${MAX})"
            tvQuizDescription.text = getString(R.string.quiz_support_btn)
            btnQuizAlone.visibility = View.VISIBLE
        }
    }

    private fun goStartQuiz() {
        findNavController().navigate(R.id.action_quizFragment_to_startQuizFragment)
    }

    private fun goMakeQuiz() {
        val quizKey = QuizKey(
            qTitle = null,
            sqId = null,
            isWq = null
        )
        val action = QuizFragmentDirections.actionQuizFragmentToMakeQuizFragment(quizKey, true)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}