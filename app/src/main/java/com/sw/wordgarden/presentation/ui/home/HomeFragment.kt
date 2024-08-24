package com.sw.wordgarden.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentHomeBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel:HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivAlarm.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_alarmFragment)
        }

        setViewModel()
    }

    private fun setViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.homeEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { event ->
                    when (event) {
                        is DefaultEvent.Failure -> {
                            ToastMaker.make(requireContext(), event.msg)
                        }
                        DefaultEvent.Success -> {}
                    }
                }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flowerData.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest { data ->
                if (data != null) {
                    binding.homeFlowerName.text = data.name
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wordData.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest { data ->
                if (data != null) {
                    binding.tvTodayWordTitle.text = data.title
                    binding.tvTodayWordDescription.text = data.description
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}