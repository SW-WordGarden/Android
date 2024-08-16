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
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.FriendModel
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
            override fun onItemClicked(item: FriendModel) {
                val builder = AlertDialog.Builder(requireActivity())
                builder.setMessage(R.string.share_quiz_msg_share)
                builder.setPositiveButton(R.string.common_positive) { _, _ ->
                    viewmodel.shareQuiz(quizId, item.uid)
                }
                builder.setNegativeButton(R.string.common_negative) { _, _ -> }
                builder.show()
            }
        })
    }
    private lateinit var quizId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupData()

        _binding = FragmentShareQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupData() {
        val args: ShareQuizFragmentArgs by navArgs()
        quizId = args.argsQuizId ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()

        /**
         * test code
         * TODO: 서버 연동 후 더미 test code 삭제
         */
        val dummyFriends = listOf(
            FriendModel(uid = "test_friend_uid_1", nickname = "test_friend_1", thumbnail = ""),
            FriendModel(uid = "test_friend_uid_2", nickname = "test_friend_2", thumbnail = ""),
            FriendModel(uid = "test_friend_uid_3", nickname = "test_friend_23", thumbnail = ""),
        )
        adapter.submitList(dummyFriends)

        if (dummyFriends.isEmpty()) {
            binding.rvShareQuiz.visibility = View.INVISIBLE
            binding.tvShareQuizNoFriends.visibility = View.VISIBLE
        } else {
            binding.rvShareQuiz.visibility = View.VISIBLE
            binding.tvShareQuizNoFriends.visibility = View.INVISIBLE
        }
        /**
         * test code end
         */

//        setupObserver()
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