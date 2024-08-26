package com.sw.wordgarden.presentation.ui.garden

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentShopBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShopFragment : Fragment() {
    private var _binding : FragmentShopBinding? = null
    private val binding get() = _binding!!
    private val viewModel:GardenViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        observeViewModel()
        buttonEvent()
    }
    private fun observeViewModel() = with(binding){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gardenEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest {   event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.resourceData.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest {  data ->
                if (data != null) {
                    btBuyWater.isEnabled = data.coins!! >= 1000
                    tvCoin.text = data.coins.toString()
                    tvWater.text = data.wateringCans.toString()
                }
            }
        }

    }
    private fun buttonEvent() = with(binding){
        btCancel.setOnClickListener {
            findNavController().navigate(R.id.action_shopFragment_to_gardenFragment)
        }
        btBuyWater.setOnClickListener{
            viewModel.buyWateringCans()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}