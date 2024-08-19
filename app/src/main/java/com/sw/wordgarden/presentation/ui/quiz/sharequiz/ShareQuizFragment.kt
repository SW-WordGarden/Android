package com.sw.wordgarden.presentation.ui.quiz.sharequiz

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentShareQuizBinding
import com.sw.wordgarden.domain.entity.user.UserEntity
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizKey
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShareQuizFragment : Fragment() {

    private var _binding: FragmentShareQuizBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: ShareQuizViewModel by viewModels()
    private val adapter: ShareQuizAdapter by lazy {
        ShareQuizAdapter(object : ShareQuizAdapter.FriendItemListener {
            override fun onItemClicked(item: UserEntity) {
                val builder = AlertDialog.Builder(requireActivity())
                builder.setMessage(R.string.share_quiz_msg_share)
                builder.setPositiveButton(R.string.common_positive) { _, _ ->
                    viewmodel.shareQuiz(quizKey, item.uid ?: "")
                }
                builder.setNegativeButton(R.string.common_negative) { _, _ -> }
                builder.show()
            }
        })
    }
    private lateinit var quizKey: QuizKey

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShareQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setupUi()
        setupListener()
        setupObserver()
    }

    private fun getData() {
        val args: ShareQuizFragmentArgs by navArgs()
        quizKey = args.argsQuizKey ?: QuizKey("", "", null)
    }

    private fun setupUi() = with(binding) {
        rvShareQuiz.adapter = adapter
    }

    private fun setupListener() = with(binding) {
        ivShareQuizBack.setOnClickListener {
            findNavController().navigate(R.id.action_shareQuizFragment_to_quizFragment)
        }

        etShareQuizInputTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun afterTextChanged(s: Editable?) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }
        })
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getFriendEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getFriendList.flowWithLifecycle(lifecycle).collectLatest { friends ->
                adapter.submitList(friends)

                if (friends.isEmpty()) {
                    binding.rvShareQuiz.visibility = View.INVISIBLE
                    binding.tvShareQuizNoFriends.visibility = View.VISIBLE
                } else {
                    binding.rvShareQuiz.visibility = View.VISIBLE
                    binding.tvShareQuizNoFriends.visibility = View.INVISIBLE
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.shareEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        ToastMaker.make(requireContext(), R.string.share_quiz_msg_success_sharing)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}