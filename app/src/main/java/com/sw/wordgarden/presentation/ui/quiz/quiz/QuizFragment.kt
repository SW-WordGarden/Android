package com.sw.wordgarden.presentation.ui.quiz.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentQuizBinding
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.ui.quiz.makequiz.MakeQuizFragment
import com.sw.wordgarden.presentation.ui.quiz.sharequiz.ShareQuizFragment
import com.sw.wordgarden.presentation.ui.quiz.startquiz.StartQuizFragment
import com.sw.wordgarden.presentation.ui.quiz.startquiz.StartQuizFragment.Companion.QUIZ_TO_START_BUNDLE_KEY
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
            //findNavController().popBackStack()
        }

        btnQuizAlone.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setMessage(R.string.quiz_msg_use_count)
            builder.setPositiveButton(R.string.common_positive) { _, _ ->
                //findNavController().navigate(해당 화면)

                /**
                 * test용 이동 코드
                 */
                setFragmentResult(
                    QUIZ_TO_START_BUNDLE_KEY,
                    bundleOf(QUIZ_TO_START_BUNDLE_KEY to limitCount)
                )

                parentFragmentManager.beginTransaction()
                    .replace(R.id.cl_quiz_main, StartQuizFragment())
                    .addToBackStack(null)
                    .commit()
            }
            builder.setNegativeButton(R.string.common_negative) { _, _ -> }
            builder.show()
        }

        btnQuizFriend.setOnClickListener {
            //findNavController().navigate(해당 화면)

            /**
             * test용 이동 코드
             */
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_quiz_main, ShareQuizFragment())
                .addToBackStack(null)
                .commit()
        }

        btnMakeQuiz.setOnClickListener {
            //findNavController().navigate(해당 화면)

            /**
             * test용 이동 코드
             */
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_quiz_main, MakeQuizFragment())
                .addToBackStack(null)
                .commit()
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