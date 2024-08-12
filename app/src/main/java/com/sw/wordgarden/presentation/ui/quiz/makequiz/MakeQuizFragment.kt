package com.sw.wordgarden.presentation.ui.quiz.makequiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentMakeQuizBinding
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizListModel
import com.sw.wordgarden.presentation.model.QuizModel
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MakeQuizFragment : Fragment() {

    private val TAG = "MakeQuizFragment"

    private var _binding: FragmentMakeQuizBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: MakeQuizViewModel by viewModels()
    private var quizListModelForCheck: List<QuizModel> = List(10) { QuizModel("", "", 0)}
    private var enableMode = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMakeQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setUpListener()
        setupObserver()
    }

    private fun getData() {
        val args: MakeQuizFragmentArgs by navArgs()
        val quizList = if (args.argsMyMadeQuiz == null) {
            enableMode = true
            QuizListModel("", List(10) { QuizModel("", "", 0)}, emptyList())
        } else {
            setConfirmDialog()
            enableMode = false
            args.argsMyMadeQuiz!!
        }

        setupUi(quizList)
    }

    private fun setConfirmDialog() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage(R.string.make_quiz_msg_can_not_edit)
        builder.setPositiveButton(R.string.common_got_it) { _, _ -> }
        builder.show()
    }

    private fun setupUi(quizList: QuizListModel) = with(binding) {
        if (!enableMode) { etMakeQuizInputTitle.isEnabled = false }
        etMakeQuizInputTitle.setText(quizList.title)

        val quizListModel = quizList.quiz!!
        val indicatorAdapter = IndicatorAdapter(enableMode, quizListModel.size) { position ->
            vpMakeQuiz.setCurrentItem(position, true)
        }
        val pagerAdapter = MakeQuizAdapter(this@MakeQuizFragment, enableMode, quizListModel) { position, question, answer, isFull ->
            quizListModel[position].question = question
            quizListModel[position].answer = answer
            quizListModelForCheck[position].question = question
            quizListModelForCheck[position].answer = answer

            if (isFull) {
                indicatorAdapter.markAsFilled(position)

                if (position < quizListModel.size - 1) {
                    vpMakeQuiz.setCurrentItem(position + 1, true)
                } else {
                    checkQuiz(quizListModelForCheck)
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
            findNavController().popBackStack()
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

    private fun checkQuiz(quizListModelForCheck: List<QuizModel>) {
        val title = binding.etMakeQuizInputTitle.text.toString()
        if (title.isEmpty()) {
            ToastMaker.make(requireContext(), R.string.make_quiz_msg_need_title)
            return
        }

        val hasEmptyValue = quizListModelForCheck.any { it.question.isNullOrEmpty() || it.answer.isNullOrEmpty() }
        if (hasEmptyValue) {
            ToastMaker.make(requireContext(), R.string.make_quiz_msg_need_all_check)
        } else {
            shareQuiz(title)
        }
    }

    private fun shareQuiz(title: String) {
        Log.i(TAG, "서버에 퀴즈 추가 요청 : $title || $quizListModelForCheck")
        viewmodel.insertQuiz(title, quizListModelForCheck)

        goShare(title)
    }

    private fun goShare(title: String) {
        val action = MakeQuizFragmentDirections.actionMakeQuizFragmentToShareQuizFragment(title)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}