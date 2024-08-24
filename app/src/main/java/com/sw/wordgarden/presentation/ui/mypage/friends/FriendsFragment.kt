package com.sw.wordgarden.presentation.ui.mypage.friends

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.DialogReportBinding
import com.sw.wordgarden.databinding.FragmentFriendsBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.ui.loading.LoadingDialog
import com.sw.wordgarden.presentation.util.Constants.CLIP_LABEL
import com.sw.wordgarden.presentation.util.KeyboardCleaner
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FriendsFragment : Fragment() {

    private var _binding: FragmentFriendsBinding? = null
    private val binding get() = _binding!!

    private var loadingDialog: LoadingDialog? = null
    private val viewmodel: FriendsViewModel by viewModels()
    private val adapter: FriendsAdapter by lazy {
        FriendsAdapter(object : FriendsAdapter.FriendsItemListener {
            override fun onItemDeleteClick(friendUid: String?) {
                showConfirmDeleteDialog(friendUid)
            }

            override fun onItemReportClick(friendUid: String?) {
                showReportDialog(friendUid)
            }
        })
    }

    private fun showConfirmDeleteDialog(friendUid: String?) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage(R.string.mypage_friends_msg_delete_friend)
        builder.setPositiveButton(R.string.common_positive) { _, _ ->
            viewmodel.deleteFriend(friendUid)
        }
        builder.setNegativeButton(R.string.common_negative) { _, _ -> }
        builder.show()
    }

    private fun showReportDialog(friendUid: String?) {
        val dialogBinding = DialogReportBinding.inflate(LayoutInflater.from(requireContext()))

        val reportDialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .create()

        dialogBinding.rgReport.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rb_report_reason6) {
                dialogBinding.etReportReason6.visibility = View.VISIBLE
            } else {
                dialogBinding.etReportReason6.visibility = View.GONE
            }
        }

        dialogBinding.ivReportExit.setOnClickListener {
            reportDialog.dismiss()
        }

        dialogBinding.btnReport.setOnClickListener {
            val selectedOptionId = dialogBinding.rgReport.checkedRadioButtonId
            val reportContent = when (selectedOptionId) {
                R.id.rb_report_reason1 -> getString(R.string.mypage_friend_report_reason1)
                R.id.rb_report_reason2 -> getString(R.string.mypage_friend_report_reason2)
                R.id.rb_report_reason3 -> getString(R.string.mypage_friend_report_reason3)
                R.id.rb_report_reason4 -> getString(R.string.mypage_friend_report_reason4)
                R.id.rb_report_reason5 -> getString(R.string.mypage_friend_report_reason5)
                R.id.rb_report_reason6 -> dialogBinding.etReportReason6.text.toString()
                else -> null
            }

            if (!reportContent.isNullOrBlank()) {
                viewmodel.reportFriend(friendUid, reportContent)
                reportDialog.dismiss()
            } else {
                ToastMaker.make(requireContext(), R.string.mypage_friends_msg_report_fill)
            }
        }

        reportDialog.show()
    }

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
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        val rootView = binding.root
        KeyboardCleaner.setup(rootView, this)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
        setupObserver()
    }

    private fun setupUi() = with(binding) {
        rvMyFriendsList.adapter = adapter
    }

    private fun setupListener() = with(binding) {
        ivMyFriendsBack.setOnClickListener {
            goBack()
        }

        llMyFriendsUrl.setOnClickListener {
            val args: FriendsFragmentArgs by navArgs()
            val myCode = args.argsMyCode

            val clipboardManager =
                context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(CLIP_LABEL, myCode)
            clipboardManager.setPrimaryClip(clipData)

            ToastMaker.make(requireContext(), R.string.mypage_friends_msg_copy_my_code)
        }

        btnMyFriendsAdd.setOnClickListener {
            val friendUid = etMyFriendsEnter.text.toString()
            viewmodel.addFriend(friendUid)
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getFriendsEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getFriends.flowWithLifecycle(lifecycle).collectLatest { friends ->
                val list = friends?.friends ?: emptyList()
                adapter.submitList(list)

                if (list.isEmpty()) {
                    binding.rvMyFriendsList.visibility = View.INVISIBLE
                    binding.tvFriendsNoFriends.visibility = View.VISIBLE
                } else {
                    binding.rvMyFriendsList.visibility = View.VISIBLE
                    binding.tvFriendsNoFriends.visibility = View.INVISIBLE
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.addFriendEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        viewmodel.getFriends()
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.deleteFriendEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        viewmodel.getFriends()
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.reportFriendEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
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

    private fun goBack() {
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}