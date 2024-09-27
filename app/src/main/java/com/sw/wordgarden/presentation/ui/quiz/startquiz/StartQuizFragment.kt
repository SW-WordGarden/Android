package com.sw.wordgarden.presentation.ui.quiz.startquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentStartQuizBinding
import com.sw.wordgarden.domain.entity.quiz.SqCreatorInfoEntity
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizKey
import com.sw.wordgarden.presentation.model.QuizModel
import com.sw.wordgarden.presentation.ui.loading.LoadingDialog
import com.sw.wordgarden.presentation.util.ImageConverter.stringToByteArray
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartQuizFragment : Fragment() {
    private var _binding: FragmentStartQuizBinding? = null
    private val binding get() = _binding!!

    private var loadingDialog: LoadingDialog? = null
    private val viewmodel: StartQuizViewModel by viewModels()
    private lateinit var quizKey: QuizKey
    private lateinit var quiz: QuizModel
    private lateinit var info: SqCreatorInfoEntity
    private var fromQuiz = false

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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setupListener()
        setupObserver()
    }

    private fun getData() {
        val args: StartQuizFragmentArgs by navArgs()
        quizKey = args.argsQuizKey ?: QuizKey(qTitle = "", sqId = "", isWq = false)
        fromQuiz = args.argsFromQuiz

        if (quizKey.isWq == true) { //공유 wq 퀴즈
            viewmodel.getQuizFromWq(quizKey.qTitle ?: "")
        } else if (!quizKey.sqId.isNullOrBlank()) { //공유 sq 퀴즈
            viewmodel.getQuizFromSq(quizKey.sqId ?: "")
        } else { //신규 wq 퀴즈
            viewmodel.getWq()
        }
    }

    private fun setupListener() = with(binding) {
        btnStartQuizBack.setOnClickListener {
            goBack()
        }

        btnStartQuizStart.setOnClickListener {
            goSolveQuiz()
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getQuizEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        if (!quizKey.sqId.isNullOrBlank()) { //sq 추가 정보 요청
                            viewmodel.getQuizCreatorInfo(quizKey.sqId ?: "")
                        } else {
                            setupUi()
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getQuiz.flowWithLifecycle(lifecycle).collectLatest { quizData ->
                if (quizData != null) {
                    quiz = quizData
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getQuizCreatorInfoEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        setupUi()
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getQuizCreatorInfo.flowWithLifecycle(lifecycle).collectLatest { info ->
                if (info != null) {
                    this@StartQuizFragment.info = info
                    quiz.qTitle = "${info.quizTitle}\n- ${info.nickname} -"
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.uiState.flowWithLifecycle(lifecycle).collectLatest { state ->
                if (state.isLoading) {
                    loadingDialog = LoadingDialog()
                    loadingDialog?.show(parentFragmentManager, null)
                } else {
                    loadingDialog?.dismiss()
                    loadingDialog = null
                }
            }
        }
    }

    private fun setupUi() = with(binding) {
        if (quizKey.isWq == true || fromQuiz) { //공유 받은 wq 퀴즈 or 신규 wq 퀴즈
            ivStartQuizThumbnail.visibility = View.INVISIBLE
            tvStartQuizIntroduce.text = getString(R.string.start_quiz_app_quiz)
        } else { //sq 퀴즈
            val thumbnail = stringToByteArray(info.thumbnail ?: "")
            Glide.with(requireContext())
                .load(thumbnail)
                .error(R.drawable.img_default_thumbnail)
                .fallback(R.drawable.img_default_thumbnail)
                .into(ivStartQuizThumbnail)

            tvStartQuizIntroduce.text = quiz.qTitle
        }
    }

    private fun goBack() {
        findNavController().navigateUp()
    }

    private fun goSolveQuiz() {
        val action = StartQuizFragmentDirections.actionStartQuizFragmentToSolveQuizFragmentForWq(quiz, true)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}