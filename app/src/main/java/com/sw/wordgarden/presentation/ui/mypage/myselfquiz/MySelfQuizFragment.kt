package com.sw.wordgarden.presentation.ui.mypage.myselfquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentMySelfQuizBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizKey
import com.sw.wordgarden.presentation.ui.loading.LoadingDialog
import com.sw.wordgarden.presentation.ui.mypage.common.MySelfSolvedQuizAdapter
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MySelfQuizFragment : Fragment() {

    private var _binding: FragmentMySelfQuizBinding? = null
    private val binding get() = _binding!!

    private var loadingDialog: LoadingDialog? = null
    private val viewmodel: MySelfQuizViewModel by viewModels()
    private val adapter: MySelfSolvedQuizAdapter by lazy {
        MySelfSolvedQuizAdapter(object : MySelfSolvedQuizAdapter.QuizItemListener {
            override fun onItemClicked(sqId: String, qTitle: String) {
                val quizKey = QuizKey(
                    sqId = sqId,
                    qTitle = qTitle,
                    isWq = sqId.isEmpty() || sqId.isBlank()
                )
                goMakeQuiz(quizKey)
            }
        })
    }

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
        _binding = FragmentMySelfQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
        setupObserve()
    }

    private fun setupUi() = with(binding) {
        rvMySelfQuiz.adapter = adapter
    }

    private fun setupListener() = with(binding) {
        ivMySelfQuizBack.setOnClickListener {
            ivMySelfQuizBack.isEnabled = false
            goBack()
        }
    }

    private fun setupObserve() {
        lifecycleScope.launch {
            viewmodel.getMySelfQuizTitleListEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getMySelfQuizTitleList.flowWithLifecycle(lifecycle).collectLatest { quizzes ->
                adapter.submitList(quizzes)

                if (quizzes.isEmpty()) {
                    binding.rvMySelfQuiz.visibility = View.INVISIBLE
                    binding.tvMySelfQuizNoQuizzes.visibility = View.VISIBLE
                } else {
                    binding.rvMySelfQuiz.visibility = View.VISIBLE
                    binding.tvMySelfQuizNoQuizzes.visibility = View.INVISIBLE
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
        findNavController().navigateUp()
    }

    private fun goMakeQuiz(quizKey: QuizKey) {
        val navController = findNavController()
        val currentDestination = navController.currentDestination?.id
        val action = MySelfQuizFragmentDirections.actionMySelfQuizFragmentToMakeQuizFragment(quizKey, false)

        if (currentDestination == R.id.myselfQuizFragment) {
            navController.navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}