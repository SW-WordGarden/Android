package com.sw.wordgarden.presentation.ui.garden

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentGardenBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.TreeModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GardenFragment : Fragment() {
    private var _binding : FragmentGardenBinding? = null
    private val binding get() = _binding!!
    private val viewModel:GardenViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGardenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
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
            viewModel.growData.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest {  data ->
                if (data != null) {
                    tvFlowerName.text = data.name
                    val img = GetFlowerImg.getFlowerImg(data.growthStage!!, data.growthValue!!)
                    Glide.with(requireContext())
                        .load(img)
                        .into(ivFlower)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.resourceData.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest {  data ->
                if (data != null) {
                    btWater.isEnabled = data.plantCount != 0

                    tvCoin.text = data.coins.toString()
                    tvWater.text = data.wateringCans.toString()
                    tvCategory.text = data.plantCount.toString()
                }
            }
        }
    }
    private fun buttonEvent() = with(binding){
        ivTopWater.setOnClickListener {
            findNavController().navigate(R.id.action_gardenFragment_to_shopFragment)
        }
        ivTopCategory.setOnClickListener {
            findNavController().navigate(R.id.action_gardenFragment_to_flowerBookFragment)
        }
        btCancel.setOnClickListener {
            findNavController().navigate(R.id.action_gardenFragment_to_homeFragment)
        }
        btWater.setOnClickListener {
            viewModel.useWateringCans()
            viewModel.getGrowInfo()
            viewModel.getUserResource()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}