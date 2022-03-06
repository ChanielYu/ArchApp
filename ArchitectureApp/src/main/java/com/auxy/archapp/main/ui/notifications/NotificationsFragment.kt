package com.auxy.archapp.main.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.auxy.archapp.databinding.FragmentNotificationsBinding
import com.auxy.archapp.main.ui.notifications.NotificationContract.ViewState.Shimmer
import com.auxy.archapp.main.ui.notifications.NotificationContract.ViewState.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotificationsFragment : Fragment() {
    private val notificationsViewModel: NotificationsViewModel by viewModels()

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            refresh.setOnClickListener {
                notificationsViewModel.refreshTextA()
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                notificationsViewModel.viewState.collect { viewSate ->
                    when (viewSate) {
                        Shimmer -> Unit
                        is Success -> with(binding) {
                            textNotifications.text = viewSate.text
                            progressBar.hide()
                        }
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentNotificationsBinding.inflate(inflater, container, false).also { fragmentBinding ->
        _binding = fragmentBinding
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
