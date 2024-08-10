package com.sw.wordgarden.presentation.ui.garden

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentGardenBinding
import com.sw.wordgarden.presentation.model.TreeModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GardenFragment : Fragment() {
    private var _binding : FragmentGardenBinding? = null
    private val binding get() = _binding!!
    private val viewModel:GardenViewModel by activityViewModels()
    private lateinit var navController: NavController

    private val testImg = context?.let { ContextCompat.getDrawable(it, R.drawable.testtree).toString() }
    private val testTree = TreeModel("testTree", "아카시아 나무", testImg, 0)

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
        /** 서버 연결 후 수정 필요 **/
        tvCoin.text = "test"
        tvWater.text = "test"
        tvCategory.text = "test"
        tvUserName.text = "testUser"

        tvFlowerName.text = testTree.name
        Glide.with(requireContext())
            .load(testTree.image)
            .into(ivFlower)
    }
    private fun buttonEvent() = with(binding){
        ivTopWater.setOnClickListener {
            //navigation 이동
        // navController.navigate()
        }
        ivTopCategory.setOnClickListener {
            //navigation 이동
            //navController.navigate()
        }
        btCancel.setOnClickListener {
            //navigation 이동
            //navController.navigate()
        }
        btWater.setOnClickListener {

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}