package com.sw.wordgarden.presentation.ui.alarm

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
import com.sw.wordgarden.databinding.FragmentAlarmBinding
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.SelfQuizModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlarmFragment : Fragment() {
    private var _binding: FragmentAlarmBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: AlarmViewModel by viewModels()
    private val adapter: AlarmAdapter by lazy {
        AlarmAdapter(object : AlarmAdapter.AlarmItemListener {
            override fun onItemClicked(quizId: String) {
                viewmodel.getQuiz(quizId)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlarmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
        setupObserver()
    }

    private fun setupUi() = with(binding) {
        rvAlarmList.adapter = adapter
    }

    private fun setupListener() = with(binding) {
        ivAlarmBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getAlarmListEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getAlarmList.flowWithLifecycle(lifecycle).collectLatest { alarms ->
                adapter.submitList(alarms)

                if (alarms.isNullOrEmpty()) {
                    binding.rvAlarmList.visibility = View.INVISIBLE
                    binding.tvAlarmNoAlarms.visibility = View.VISIBLE
                } else {
                    binding.rvAlarmList.visibility = View.VISIBLE
                    binding.tvAlarmNoAlarms.visibility = View.INVISIBLE
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getQuizByQuizIdEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getQuizByQuizId.flowWithLifecycle(lifecycle).collectLatest { quiz ->
                quiz?.let {
                    goStartQuiz(quiz)
                }
            }
        }
    }

    private fun goStartQuiz(quiz: SelfQuizModel?) {
        val navController = findNavController()
        if (navController.currentDestination?.id == R.id.alarmFragment) {
            val action = AlarmFragmentDirections.actionAlarmFragmentToStartQuizFragment(quiz)
            navController.navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
