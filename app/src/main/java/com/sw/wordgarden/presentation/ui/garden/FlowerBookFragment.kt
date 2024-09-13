package com.sw.wordgarden.presentation.ui.garden

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentFlowerBookBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FlowerBookFragment:Fragment() {
    private var _binding:FragmentFlowerBookBinding? = null
    private val binding get() = _binding!!
    private val viewModel:GardenViewModel by activityViewModels()
    private lateinit var navController: NavController
    private var bookPage = 1

    private var growValue : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlowerBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        viewModel.getBookFlowerData(1)
        buttonEvent()
        observeViewModel()
    }
    private fun buttonEvent() = with(binding){
        btCancel.setOnClickListener {
            findNavController().navigate(R.id.action_flowerBookFragment_to_gardenFragment)
        }
        btLeftArrow.setOnClickListener {
            if(bookPage == 1){
            }
            else {
                bookPage--
                pageCount.text = "$bookPage"

                viewModel.getBookFlowerData(bookPage)
            }
        }
        btRightArrow.setOnClickListener {
            if (bookPage == 12) {}
            else {
                bookPage++
                pageCount.text = "$bookPage"

                viewModel.getBookFlowerData(bookPage)
            }
        }
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
                    flowerChapter.text = data.plantNum.toString()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flowerName.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest {  data ->
                if (data != null) {
                    if (data == 0) tvFlowerSpecies.text = "??"
                    else tvFlowerSpecies.setText(data)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flowerImg.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest {  data ->
                if (data != null) {
                    setFlowerImg(data)
                }
            }
        }
    }
    private fun setFlowerImg(list : List<Int>){
        val size = list.size
        val setList = list.toMutableList()
        for(i in 1 .. 4){
            if(i > size) setList += R.drawable.ic_book_item_layer2
        }
        Glide.with(requireContext())
            .load(setList[0])
            .into(binding.itemSmallLayer1)
        Glide.with(requireContext())
            .load(setList[1])
            .into(binding.itemSmallLayer2)
        Glide.with(requireContext())
            .load(setList[2])
            .into(binding.itemSmallLayer3)
        Glide.with(requireContext())
            .load(setList[3])
            .into(binding.itemSmallLayer4)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}