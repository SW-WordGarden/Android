package com.sw.wordgarden.presentation.ui.garden

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sw.wordgarden.databinding.FragmentFlowerBookBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowerBookFragment:Fragment() {
    private var _binding:FragmentFlowerBookBinding? = null
    private val binding get() = _binding!!
    private val viewModel:GardenViewModel by activityViewModels()
    private lateinit var navController: NavController
    private var bookPage = 1

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

        buttonEvent()
        observeViewModel()
    }
    private fun buttonEvent() = with(binding){
        btCancel.setOnClickListener {
            //navigation 이동
            //navController.navigate()
        }
        btLeftArrow.setOnClickListener {
            if(bookPage == 1){}
            else {
                bookPage--
                pageCount.text = "$bookPage"
                //도감 불러오기
            }
        }
        btRightArrow.setOnClickListener {
            if (bookPage == 12) {}
            else {
                bookPage++
                pageCount.text = "$bookPage"
                //도감 불러오기
            }
        }
    }
    private fun observeViewModel() = with(binding){
        userName.text = "test"
        tvFlowerSpecies.text = "xx"
        flowerChapter.text = "x"
        //이미지, 날짜 적용 하는 코드 필요
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}