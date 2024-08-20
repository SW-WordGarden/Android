package com.sw.wordgarden.presentation.ui.word

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentWordBinding
import com.sw.wordgarden.presentation.model.WordModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class wordFragment : Fragment() {
    private var _binding : FragmentWordBinding? = null
    private val binding get() = _binding!!

    private val viewModel:WordViewModel by activityViewModels()
    private lateinit var wordAdapter : WordAdapter

    private lateinit var weeklyWordList: List<WordModel>
    private lateinit var basicWordList: List<WordModel>
    private lateinit var societyWordList: List<WordModel>
    private lateinit var scienceWordList: List<WordModel>
    private lateinit var idiomWordList: List<WordModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        initViewModel()
    }
    private fun setAdapter() = with(binding){
        wordAdapter = WordAdapter{ item ->
            findNavController().navigate(R.id.action_wordFragment_to_detailWordFragment)
            viewModel.selectWord(item)
        }
        rvWord.adapter = wordAdapter
        rvWord.layoutManager = LinearLayoutManager(this@wordFragment.activity)
    }
    private fun initViewModel(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wordListState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest { wordList ->
                weeklyWordList = wordList
                basicWordList = wordList.slice(0..9)
                societyWordList = wordList.slice(10..19)
                scienceWordList = wordList.slice(20..29)
                idiomWordList = wordList.slice(30..39)
                wordAdapter.submitList(basicWordList)

                setRadioBtn()
            }
        }
    }
    private fun setRadioBtn() = with(binding){
        wordRadioGrooup.setOnCheckedChangeListener { _, id ->
            when(id){
                R.id.rb_basic -> wordAdapter.submitList(basicWordList)
                R.id.rb_society -> wordAdapter.submitList(societyWordList)
                R.id.rb_science -> wordAdapter.submitList(scienceWordList)
                R.id.rb_idiom -> wordAdapter.submitList(idiomWordList)
            }
        }
    }
}