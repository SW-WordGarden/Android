package com.sw.wordgarden.presentation.ui.word

import android.os.Bundle
import android.util.Log
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
import com.sw.wordgarden.presentation.ui.loading.LoadingDialog
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
    private var loadingDialog: LoadingDialog? = null

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

        viewModel.getCategoryWordList("basic")
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
            viewModel.wordCListState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest { wordList ->
                wordAdapter.submitList(wordList)
                setRadioBtn()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest {   state ->
                if (state.isLoading) {
                    if (loadingDialog == null || loadingDialog?.dialog?.isShowing == false) {
                        loadingDialog = LoadingDialog()
                        loadingDialog?.show(parentFragmentManager, null)
                    }
                } else {
                    loadingDialog?.dismiss()
                    loadingDialog = null
                }
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
                R.id.rb_basic -> viewModel.getCategoryWordList("basic")
                R.id.rb_idiom -> viewModel.getCategoryWordList("idiom")
                R.id.rb_science -> viewModel.getCategoryWordList("science")
                R.id.rb_society -> viewModel.getCategoryWordList("society")
            }
        }
        btBack.setOnClickListener {
            findNavController().navigate(R.id.action_wordFragment_to_homeFragment)
        }
    }
}