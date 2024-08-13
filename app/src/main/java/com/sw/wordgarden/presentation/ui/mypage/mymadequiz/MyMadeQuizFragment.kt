package com.sw.wordgarden.presentation.ui.mypage.mymadequiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.databinding.FragmentMyMadeQuizBinding
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizListModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyMadeQuizFragment : Fragment() {

    private val TAG = "MyMadeQuizFragment"

    private var _binding: FragmentMyMadeQuizBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: MyMadeQuizViewModel by viewModels()
    private val adapter: MyMadeQuizAdapter by lazy {
        MyMadeQuizAdapter(object : MyMadeQuizAdapter.QuizItemListener {
            override fun onItemClicked(title: String) {
                viewmodel.getQuizList(title)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyMadeQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
        setupObserve()
    }

    private fun setupUi() = with(binding) {
        rvMyMadeQuiz.adapter = adapter
    }

    private fun setupListener() = with(binding) {
        ivMyMadeQuizBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupObserve() {
        lifecycleScope.launch {
            viewmodel.getMyMadeQuizTitleListEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getMyMadeQuizTitleList.flowWithLifecycle(lifecycle).collectLatest { quizzes ->
                adapter.submitList(quizzes)

                if (quizzes.isEmpty()) {
                    binding.rvMyMadeQuiz.visibility = View.INVISIBLE
                    binding.tvMyMadeQuizNoQuizzes.visibility = View.VISIBLE
                } else {
                    binding.rvMyMadeQuiz.visibility = View.VISIBLE
                    binding.tvMyMadeQuizNoQuizzes.visibility = View.INVISIBLE
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getMyMadeQuizByTitleEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getMyMadeQuizByTitle.flowWithLifecycle(lifecycle).collectLatest { quiz ->
                quiz?.let {
                    goMakeQuiz(quiz)
                }
            }
        }
    }

    private fun goMakeQuiz(quiz: QuizListModel?) {
        val action = MyMadeQuizFragmentDirections.actionMyMadeQuizFragmentToMakeQuizFragment(quiz)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewmodel.clearQuizByTitle()
        _binding = null
    }
}