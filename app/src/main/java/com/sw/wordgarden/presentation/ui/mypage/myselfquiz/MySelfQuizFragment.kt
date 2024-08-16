package com.sw.wordgarden.presentation.ui.mypage.myselfquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentMySelfQuizBinding
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.SelfQuizModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MySelfQuizFragment : Fragment() {

    private val TAG = "MySelfQuizFragment"

    private var _binding: FragmentMySelfQuizBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: MySelfQuizViewModel by viewModels()
    private val adapter: MySelfQuizAdapter by lazy {
        MySelfQuizAdapter(object : MySelfQuizAdapter.QuizItemListener {
            override fun onItemClicked(quizId: String) {
                viewmodel.getQuizList(quizId)
            }
        })
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
            findNavController().navigateUp()
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
            viewmodel.getMySelfQuizByQuizIdEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getMySelfQuizByQuizId.flowWithLifecycle(lifecycle).collectLatest { quiz ->
                quiz?.let {
                    goMakeQuiz(quiz)
                }
            }
        }
    }

    private fun goMakeQuiz(quiz: SelfQuizModel?) {
        val navController = findNavController()
        if (navController.currentDestination?.id == R.id.myselfQuizFragment) {
            val action = MySelfQuizFragmentDirections.actionMySelfQuizFragmentToMakeQuizFragment(quiz)
            navController.navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewmodel.clearQuizByQuizId()
        _binding = null
    }
}