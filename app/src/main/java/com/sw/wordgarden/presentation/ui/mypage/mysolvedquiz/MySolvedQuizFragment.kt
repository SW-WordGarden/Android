package com.sw.wordgarden.presentation.ui.mypage.mysolvedquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.databinding.FragmentMySolvedQuizBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizKey
import com.sw.wordgarden.presentation.ui.mypage.common.MySelfSolvedQuizAdapter
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MySolvedQuizFragment : Fragment() {

    private var _binding: FragmentMySolvedQuizBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: MySolvedQuizViewModel by viewModels()
    private val adapter: MySelfSolvedQuizAdapter by lazy {
        MySelfSolvedQuizAdapter(object : MySelfSolvedQuizAdapter.QuizItemListener {
            override fun onItemClicked(sqId: String, qTitle: String) {
                val quizKey = QuizKey(
                    sqId = sqId,
                    qTitle = qTitle,
                    isWq = sqId.isEmpty() || sqId.isBlank()
                )
                goResultQuiz(quizKey)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMySolvedQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
        setupObserve()
    }

    private fun setupUi() = with(binding) {
        rvMySolvedQuiz.adapter = adapter
    }

    private fun setupListener() = with(binding) {
        ivMySolvedQuizBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObserve() {
        lifecycleScope.launch {
            viewmodel.getQuizTitlesEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getQuizTitles.flowWithLifecycle(lifecycle).collectLatest { quizzes ->
                adapter.submitList(quizzes)

                if (quizzes.isEmpty()) {
                    binding.rvMySolvedQuiz.visibility = View.INVISIBLE
                    binding.tvMySolvedQuizNoQuizzes.visibility = View.VISIBLE
                } else {
                    binding.rvMySolvedQuiz.visibility = View.VISIBLE
                    binding.tvMySolvedQuizNoQuizzes.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun goResultQuiz(quizKey: QuizKey) {
        val navController = findNavController()
        val action =
            MySolvedQuizFragmentDirections.actionMySolvedQuizFragmentToResultQuizFragment(quizKey)
        navController.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}