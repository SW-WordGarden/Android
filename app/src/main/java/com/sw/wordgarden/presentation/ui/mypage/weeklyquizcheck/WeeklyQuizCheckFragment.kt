package com.sw.wordgarden.presentation.ui.mypage.weeklyquizcheck

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
import com.sw.wordgarden.databinding.FragmentWeeklyBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeeklyQuizCheckFragment : Fragment() {

    private var _binding: FragmentWeeklyBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: WeeklyQuizCheckViewModel by viewModels()
    private val adapter: WeeklyQuizCheckAdapter by lazy {
        WeeklyQuizCheckAdapter()
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
        _binding = FragmentWeeklyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
        setupObserver()
    }

    private fun setupUi() = with(binding) {
        rvMyWeeklyFailWord.adapter = adapter
    }

    private fun setupListener() = with(binding) {
        ivMyWeeklyScoreBack.setOnClickListener {
            goBack()
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getWrongWqsEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getWrongWqs.flowWithLifecycle(lifecycle).collectLatest { wrongWqs ->
                adapter.submitList(wrongWqs)

                if (wrongWqs.isEmpty()) {
                    binding.rvMyWeeklyFailWord.visibility = View.INVISIBLE
                    binding.tvWeeklyNoWrong.visibility = View.VISIBLE
                } else {
                    binding.rvMyWeeklyFailWord.visibility = View.VISIBLE
                    binding.tvWeeklyNoWrong.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun goBack() {
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}