package com.sw.wordgarden.presentation.ui.mypage.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentMypageBinding
import com.sw.wordgarden.domain.entity.user.FriendEntity
import com.sw.wordgarden.domain.entity.user.UserInfoEntity
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.util.ImageConverter.stringToByteArray
import com.sw.wordgarden.presentation.util.ImageConverter.uriToString
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MypageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: MyPageViewModel by viewModels()
    private val adapter: MyPageFriendAdapter by lazy {
        MyPageFriendAdapter(object : MyPageFriendAdapter.FriendItemListener {
            override fun onItemClicked(item: FriendEntity) {
                //TODO: 친구 상세 페이지 기능 추가 시 구현
            }
        })
    }
    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            viewmodel.updateUserImage(uriToString(uri, requireContext()) ?: "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
        setupObserver()
    }

    private fun setupListener() = with(binding) {
        ivMyEdit.setOnClickListener {
            galleryLauncher.launch("image/*")
        }
        ivMyBack.setOnClickListener {
            findNavController().navigateUp()
        }
        ivMySetting.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_settingFragment)
        }
        ivMyWeeklyScoreMore.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_weeklyQuizCheckFragment)
        }
        ivMySelfQuizMore.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_mySelfQuizFragment)
        }
        ivMySolvedQuizMore.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_mySolvedQuizFragment)
        }
//        ivMyTakenQuizMore.setOnClickListener { //TODO: 받은 퀴즈 api 구현 후 진행
//            findNavController().navigate(R.id.action_mypageFragment_to_myTakenQuizFragment)
//        }
        ivMyFriendsMore.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_friendsFragment)
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getUserInfoEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getUserInfo.flowWithLifecycle(lifecycle).collectLatest { info ->
                setupUi(info)
            }
        }

        lifecycleScope.launch {
            viewmodel.updateUserImageEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }
    }

    private fun setupUi(info: UserInfoEntity?) = with(binding) {
        val thumbnail = stringToByteArray(info?.profileImage ?: "")
        Glide.with(requireContext())
            .load(thumbnail)
            .error(R.drawable.img_default_thumbnail)
            .fallback(R.drawable.img_default_thumbnail)
            .into(ivMyProfile)
        tvMyName.text = info?.name ?: ""

        tvMyPoint.text = info?.point.toString()
        tvMyRank.text = info?.rank.toString()

        val weeklyState =
            "${info?.all}${getString(R.string.mypage_weekly_score_text1)} ${info?.right}${getString(R.string.mypage_weekly_score_text2)}"
        tvMyScore.text = weeklyState

        tvMySelfQuizTitleName.text = info?.latestCustomQuiz?.sqTitle ?: ""
        tvMySolvedQuizTitleName.text = info?.latestSolvedQuiz?.title ?: ""

        rvMyFriendsList.adapter = adapter
        adapter.submitList(info?.randomFriends)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}