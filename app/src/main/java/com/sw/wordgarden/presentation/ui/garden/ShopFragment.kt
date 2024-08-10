package com.sw.wordgarden.presentation.ui.garden

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentShopBinding
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint

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
        tvCoin.text = "test"
        tvWater.text = "test"

    }
    private fun buttonEvent() = with(binding){
        btCancel.setOnClickListener {
            //navigation 이동
            //navController.navigate()
        }
        btBuyWater.setOnClickListener{
//            if(userWater >= 1000){
//
//            }
//            else {
//                ToastMaker.make( requireContext(), R.string.shop_lake_of_momey)
//            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}