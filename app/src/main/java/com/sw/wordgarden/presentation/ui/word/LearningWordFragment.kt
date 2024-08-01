package com.sw.wordgarden.presentation.ui.word

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentLearningWordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearningWordFragment : Fragment() {
    private var _binding : FragmentLearningWordBinding? = null
    private val binding get() = _binding!!

    private lateinit var wordAdapter : WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLearningWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        initViewModel()
    }
    private fun setAdapter() = with(binding){
        wordAdapter = WordAdapter{ item ->
            parentFragmentManager.beginTransaction()
                .add(R.id.frame_layout, DetailWordFragment())
                .addToBackStack(null)
                .commit()
        }
        rvWord.adapter = wordAdapter
        rvWord.layoutManager = LinearLayoutManager(this@LearningWordFragment.activity)
    }
    private fun initViewModel(){
        wordAdapter.submitList()
    }
}