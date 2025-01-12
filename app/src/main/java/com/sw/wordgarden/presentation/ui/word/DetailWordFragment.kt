package com.sw.wordgarden.presentation.ui.word

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentDetailWordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailWordFragment : Fragment() {
    private var _binding : FragmentDetailWordBinding? = null
    private val binding get() = _binding!!

    private val viewModel:WordViewModel by activityViewModels()
    private var position = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailWordBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setButton()
    }
    private fun initViewModel() = with(binding){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wordSelectState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest { wordData ->
                if (wordData != null) {
                    tvQuizTitle.text = wordData.category
                    detailTitle.text = wordData.title
                    detailContents.text = wordData.description
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wordSelectPosition.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest { getPosition ->
                val showPosition = getPosition + 1
                count.text = showPosition.toString()
                position = getPosition

                if (getPosition == 0)  btTextBack.visibility = AppCompatTextView.INVISIBLE
                else btTextBack.visibility = AppCompatTextView.VISIBLE
                if(getPosition == 39) btTextNext.visibility = AppCompatTextView.INVISIBLE
                else btTextNext.visibility = AppCompatTextView.VISIBLE
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wordLikeState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest { state ->
                when(state) {
                    true -> {
                        binding.ivHeart.visibility = AppCompatImageView.INVISIBLE
                        binding.ivFullHeart.visibility = AppCompatImageView.VISIBLE
                    }
                    else -> {
                        binding.ivHeart.visibility = AppCompatImageView.VISIBLE
                        binding.ivFullHeart.visibility = AppCompatImageView.INVISIBLE
                    }
                }
            }
        }
    }
    private fun setButton() = with(binding){
        btQuizBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailWordFragment_to_wordFragment)
        }

        btTextBack.setOnClickListener {
            position--
            viewModel.selectWord(position)
        }
        btTextNext.setOnClickListener {
            position++
            viewModel.selectWord(position)
        }
        ivHeart.setOnClickListener {
            viewModel.insertLikeState(true)
        }
        ivFullHeart.setOnClickListener {
            viewModel.insertLikeState(false)
        }
    }
}