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
import com.sw.wordgarden.presentation.model.DefaultEvent
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
            findNavController().popBackStack()
        }

        btnQuizAlone.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setMessage(R.string.quiz_msg_use_count)
            builder.setPositiveButton(R.string.common_positive) { _, _ ->
                val action = QuizFragmentDirections.actionQuizFragmentToStartQuizFragment(limitCount)
                findNavController().navigate(action)
            }
            builder.setNegativeButton(R.string.common_negative) { _, _ -> }
            builder.show()
        }

        btnQuizFriend.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_shareQuizFragment)
        }

        btnMakeQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_makeQuizFragment)
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
    }

    @SuppressLint("SetTextI18n")
    private fun setupUi() = with(binding) {
//        ivQuizUserThumbnail.setImageResource()

        val userName = "사용자" //TODO: 사용자 닉네임 home에서 받아오도록 함

        if (limitCount >= MAX) {
            tvQuizWelcomeUser.text =
                        "${userName}${getString(R.string.quiz_welcome)}\n"
            tvQuizDescription.text =
                        "${getString(R.string.quiz_all_clear)}\n" +
                        "(${MAX}/${MAX})"

            btnQuizAlone.visibility = View.INVISIBLE
            btnQuizFriend.visibility = View.INVISIBLE
        } else {
            tvQuizWelcomeUser.text =
                        "${userName}${getString(R.string.quiz_welcome)}\n" +
                        "(${limitCount}/${MAX})"
            tvQuizDescription.text = getString(R.string.quiz_support_btn)

            btnQuizAlone.visibility = View.VISIBLE
            btnQuizFriend.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}