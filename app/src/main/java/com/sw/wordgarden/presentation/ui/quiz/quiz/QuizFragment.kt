package com.sw.wordgarden.presentation.ui.quiz.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentQuizBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.event.UserCheckEvent
import com.sw.wordgarden.presentation.model.QuizKey
import com.sw.wordgarden.presentation.ui.loading.LoadingDialog
import com.sw.wordgarden.presentation.util.Constants.DAILY_LIMIT
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private var loadingDialog: LoadingDialog? = null
    private var isDialogShowing = false
    private val viewmodel: QuizViewModel by viewModels()
    private var limitCount = 0

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
            if (limitCount < DAILY_LIMIT) {
                goStartQuiz()
            }
        }

        btnMakeQuiz.setOnClickListener {
            goMakeQuiz()
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getUidEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getUid.flowWithLifecycle(lifecycle).collectLatest { uid ->
                if (!uid.isNullOrEmpty() && uid.isNotBlank()) {
                    viewmodel.checkUserInfo(uid)
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.checkUserEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is UserCheckEvent.Success -> {}
                    else -> ToastMaker.make(requireContext(), R.string.quiz_msg_fail_get_user_info)
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getLimit.flowWithLifecycle(lifecycle).collectLatest { limit ->
                if (limit != null) {
                    limitCount = limit
                }

                setupUi()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.uiState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { state ->
                    if (state.isLoading && !isDialogShowing) {
                        if (loadingDialog == null || loadingDialog?.isAdded == false) {
                            loadingDialog = LoadingDialog()
                            loadingDialog?.show(parentFragmentManager, null)
                            isDialogShowing = true
                        }
                    } else if (!state.isLoading && isDialogShowing) {
                        hideLoadingDialog()
                    }
                }
        }
    }

    private fun hideLoadingDialog() {
        loadingDialog?.dismiss()
        loadingDialog = null
        isDialogShowing = false
    }

    @SuppressLint("SetTextI18n")
    private fun setupUi() = with(binding) {
        tvQuizLimit.text = "(${limitCount}/${DAILY_LIMIT})"

        if (limitCount >= DAILY_LIMIT) { //최대 도달
            tvQuizAllClear.visibility = View.VISIBLE
            tvQuizWelcomeUser.visibility = View.INVISIBLE
            tvQuizLimit.visibility = View.INVISIBLE
            ivQuizBean.visibility = View.INVISIBLE
            btnQuizAlone.visibility = View.INVISIBLE

            tvQuizAllClear.text =
                "${getString(R.string.quiz_all_clear)}\n" +
                        "(${DAILY_LIMIT}/${DAILY_LIMIT})"
        } else {
            tvQuizAllClear.visibility = View.INVISIBLE
            tvQuizWelcomeUser.visibility = View.VISIBLE
            tvQuizLimit.visibility = View.VISIBLE
            ivQuizBean.visibility = View.VISIBLE
            btnQuizAlone.visibility = View.VISIBLE
        }
    }

    private fun goStartQuiz() {
        val action = QuizFragmentDirections.actionQuizFragmentToStartQuizFragment(true)
        findNavController().navigate(action)
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
        hideLoadingDialog()
        _binding = null
    }
}