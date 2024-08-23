package com.sw.wordgarden.presentation.ui.mypage.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentMypageBinding
import com.sw.wordgarden.domain.entity.user.FriendEntity
import com.sw.wordgarden.domain.entity.user.UserInfoEntity
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.util.ImageConverter.stringToByteArray
import com.sw.wordgarden.presentation.util.ImageConverter.uriToString
import com.sw.wordgarden.presentation.ui.loading.LoadingDialog
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MypageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    private var loadingDialog: LoadingDialog? = null
    private val viewmodel: MyPageViewModel by viewModels()
    private val adapter: MyPageFriendAdapter by lazy {
        MyPageFriendAdapter(requireContext(), object : MyPageFriendAdapter.FriendItemListener {
            override fun onItemClicked(item: FriendEntity) {
                //TODO: 친구 상세 페이지 기능 추가 시 구현
            }
        })
    }
    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                binding.ivMyProfile.setImageURI(uri)
                viewmodel.updateUserImage(uriToString(uri, requireContext()) ?: "")
            }
        }
    private var myCode = ""

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
            val action = MypageFragmentDirections.actionMypageFragmentToFriendsFragment(myCode)
            findNavController().navigate(action)
        }
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewmodel.getUserInfoEvent.collectLatest { event ->
                        when (event) {
                            is DefaultEvent.Failure -> {
                                ToastMaker.make(requireContext(), event.msg)
                            }
                            DefaultEvent.Success -> {}
                        }
                    }
                }

                launch {
                    viewmodel.getUserInfo.collectLatest { info ->
                        myCode = info?.uUrl ?: ""
                        setupUi(info)
                    }
                }

                launch {
                    viewmodel.updateUserImageEvent.collectLatest { event ->
                        when (event) {
                            is DefaultEvent.Failure -> {
                                ToastMaker.make(requireContext(), event.msg)
                            }
                            DefaultEvent.Success -> {}
                        }
                    }
                }

                launch {
                    viewmodel.uiState.collectLatest { state ->
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

        tvMyPoint.text = (info?.point ?: 0).toString()
        tvMyRank.text = (info?.rank ?: 0).toString()

        val weeklyState = if (info?.all == null || info.right == null) {
            ""
        } else {
            "${info.all}${getString(R.string.mypage_weekly_score_text1)} ${info.right}${getString(R.string.mypage_weekly_score_text2)}"
        }
        tvMyScore.text = weeklyState

        tvMySelfQuizTitleName.text = info?.latestCustomQuiz?.sqTitle ?: getString(R.string.mypage_my_self_quiz_no_quiz_list)
        tvMySolvedQuizTitleName.text = info?.latestSolvedQuiz?.title ?: getString(R.string.mypage_my_solved_quiz_no_quiz_list)

        rvMyFriendsList.adapter = adapter
        adapter.submitList(info?.randomFriends)
    }

    override fun onResume() {
        super.onResume()
        viewmodel.getUserInfoData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}