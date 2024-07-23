package com.sw.wordgarden.presentation.ui.quiz.makequiz

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentMakeQuizBinding
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.SelfQuizModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MakeQuizFragment : Fragment() {

    private var _binding: FragmentMakeQuizBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: MakeQuizViewModel by viewModels()
    private val onlyQuizList: List<SelfQuizModel> = List(10) { SelfQuizModel("", "") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMakeQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setUpListener()
        setupObserver()
    }

    private fun setupUi() = with(binding) {
        val indicatorAdapter = IndicatorAdapter(onlyQuizList.size) { position ->
            vpMakeQuiz.setCurrentItem(position, true)
        }
        val pagerAdapter = MakeQuizAdapter(this@MakeQuizFragment, onlyQuizList) { position, question, answer, isFull ->
            onlyQuizList[position].question = question
            onlyQuizList[position].answer = answer

            if (isFull) {
                indicatorAdapter.markAsFilled(position)

                if (position < onlyQuizList.size - 1) {
                    vpMakeQuiz.setCurrentItem(position + 1, true)
                } else {
                    checkQuiz()
                }

            } else {
                indicatorAdapter.markAsEmpty(position)
            }
        }

        rvMakeQuizIndicator.apply {
            adapter = indicatorAdapter
            layoutManager = GridLayoutManager(context, 5)
        }

        vpMakeQuiz.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    indicatorAdapter.updateSelectedPosition(position)
                }
            })
        }
    }

    private fun setUpListener() = with(binding) {
        btnMakeQuizBack.setOnClickListener {
            //findNavController().popBackStack()
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.insertQuizEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }
                    DefaultEvent.Success -> { }
                }
            }
        }
    }

    private fun checkQuiz() {
        val title = binding.etMakeQuizInputTitle.text.toString()
        if (title.isEmpty()) {
            ToastMaker.make(requireContext(), R.string.make_quiz_msg_need_title)
            return
        }

        val hasEmptyValue = onlyQuizList.any { it.question.isEmpty() || it.answer.isEmpty() }
        if (hasEmptyValue) {
            ToastMaker.make(requireContext(), R.string.make_quiz_msg_need_all_check)
        } else {
            shareQuiz(title)
        }
    }

    private fun shareQuiz(title: String) {
        Log.i(TAG, "서버에 퀴즈 추가 요청 : $title || $onlyQuizList")
        viewmodel.insertQuiz(title, onlyQuizList)

        goShare()
    }

    private fun goShare() {
        //findNavController().navigate(해당 화면)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}