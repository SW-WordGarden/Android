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
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.WordModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordFragment : Fragment() {
    private var _binding : FragmentWordBinding? = null
    private val binding get() = _binding!!

    private val viewModel:WordViewModel by activityViewModels()
    private lateinit var wordAdapter : WordAdapter

    private lateinit var weeklyWordList: List<WordModel>
    private var basicWordList: List<WordModel> = listOf<WordModel>()
    private var societyWordList: List<WordModel> = listOf<WordModel>()
    private var scienceWordList: List<WordModel> = listOf<WordModel>()
    private var idiomWordList: List<WordModel> = listOf<WordModel>()

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
        rvWord.layoutManager = LinearLayoutManager(this@WordFragment.activity)
    }
    private fun initViewModel(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wordListState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest { wordList ->
                if(wordList.isNotEmpty()){
                    weeklyWordList = wordList
                    basicWordList = wordList.slice(0..9)
                    idiomWordList = wordList.slice(10..19)
                    scienceWordList = wordList.slice(20..29)
                    societyWordList = wordList.slice(30..39)
                    wordAdapter.submitList(basicWordList)
                }

                setRadioBtn()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wordEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest { event ->
                when(event){
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }
                    DefaultEvent.Success -> {}
                }
            }
        }

    }
    private fun setRadioBtn() = with(binding){
        wordRadioGrooup.setOnCheckedChangeListener { _, id ->
            when(id){
                R.id.rb_basic -> wordAdapter.submitList(basicWordList)
                R.id.rb_idiom -> wordAdapter.submitList(idiomWordList)
                R.id.rb_science -> wordAdapter.submitList(scienceWordList)
                R.id.rb_society -> wordAdapter.submitList(societyWordList)
            }
        }
        btBack.setOnClickListener {
            findNavController().navigate(R.id.action_wordFragment_to_homeFragment)
        }
    }
}