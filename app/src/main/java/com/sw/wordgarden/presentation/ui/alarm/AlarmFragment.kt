package com.sw.wordgarden.presentation.ui.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.databinding.FragmentAlarmBinding
import com.sw.wordgarden.domain.entity.alarm.AlarmEntity
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizKey
import com.sw.wordgarden.presentation.ui.loading.LoadingDialog
import com.sw.wordgarden.presentation.util.Constants.QUIZ_TYPE_WQ
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlarmFragment : Fragment() {
    private var _binding: FragmentAlarmBinding? = null
    private val binding get() = _binding!!

    private var loadingDialog: LoadingDialog? = null
    private val viewmodel: AlarmViewModel by viewModels()
    private val adapter: AlarmAdapter by lazy {
        AlarmAdapter(object : AlarmAdapter.AlarmItemListener {
            override fun onItemClicked(alarmEntity: AlarmEntity) {
                alarm = alarmEntity
                viewmodel.deleteAlarm(alarmEntity.alarmId ?: "")
            }
        })
    }

    private lateinit var alarm: AlarmEntity

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
            viewmodel.deleteAlarmEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
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

    private fun goStartQuiz() {
        val quizKey = QuizKey(
            qTitle = alarm.content,
            sqId = alarm.content,
            isWq = alarm.quizType == QUIZ_TYPE_WQ
        )
        val navController = findNavController()
        val action = AlarmFragmentDirections.actionAlarmFragmentToStartQuizFragment(quizKey)
        navController.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
