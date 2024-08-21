package com.sw.wordgarden.presentation.ui.quiz.makequiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
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
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.QAModel
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
    private var qaModelListForInsert: List<QAModel> =
        List(10) { QAModel("", "", "", "", null) }
    private var enableMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                goBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

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
        val quizKey = args.argsQuizKey
        if (quizKey?.sqId == null) { // 새로운 퀴즈 생성 모드
            enableMode = true
            val quizModel = QuizModel(
                "",
                "",
                List(10) { QAModel("", "", "", "", null) })

            setupUi(quizModel)
        } else { // 기존 퀴즈 확인 모드
            setConfirmDialog()
            enableMode = false
            viewmodel.getQuiz(quizKey.sqId ?: "", quizKey.qTitle ?: "")
        }
    }

    private fun setConfirmDialog() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage(R.string.make_quiz_msg_can_not_edit)
        builder.setPositiveButton(R.string.common_got_it) { _, _ -> }
        builder.show()
    }

    private fun setupUi(quizModel: QuizModel) = with(binding) {
        if (!enableMode) {
            etMakeQuizInputTitle.isEnabled = false
        }
        etMakeQuizInputTitle.setText(quizModel.qTitle)

        val quizListModel = quizModel.qaList ?: emptyList()
        val indicatorAdapter = IndicatorAdapter(enableMode, quizListModel.size) { position ->
            vpMakeQuiz.setCurrentItem(position, true)
        }
        val pagerAdapter = MakeQuizAdapter(
            this@MakeQuizFragment,
            enableMode,
            quizListModel
        ) { position, question, answer, isFull ->
            quizListModel[position].question = question
            quizListModel[position].correctAnswer = answer
            qaModelListForInsert[position].question = question
            qaModelListForInsert[position].correctAnswer = answer

            if (isFull) {
                indicatorAdapter.markAsFilled(position)
                if (position < quizListModel.size - 1) {
                    vpMakeQuiz.setCurrentItem(position + 1, true)
                } else {
                    checkQuiz(qaModelListForInsert)
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
            goBack()
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getSqEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getSq.flowWithLifecycle(lifecycle).collectLatest { quizModel ->
                if (quizModel != null) {
                    setupUi(quizModel)
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.insertQuizEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }
    }

    private fun checkQuiz(quizListModelForCheck: List<QAModel>) {
        val title = binding.etMakeQuizInputTitle.text.toString()
        if (title.isEmpty()) {
            ToastMaker.make(requireContext(), R.string.make_quiz_msg_need_title)
            return
        }

        val hasEmptyValue =
            quizListModelForCheck.any { it.question.isNullOrEmpty() || it.correctAnswer.isNullOrEmpty() }
        if (hasEmptyValue) {
            ToastMaker.make(requireContext(), R.string.make_quiz_msg_need_all_check)
        } else {
            shareQuiz(title)
        }
    }

    private fun shareQuiz(title: String) {
        Log.i(TAG, "request make quiz to server : $title || $qaModelListForInsert")
        viewmodel.insertQuiz(qaModelListForInsert, title)

        goShare(title)
    }

    private fun goBack() {
        findNavController().navigateUp()
    }

    private fun goShare(quizId: String) {
        val action = MakeQuizFragmentDirections.actionMakeQuizFragmentToShareQuizFragment(quizId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}