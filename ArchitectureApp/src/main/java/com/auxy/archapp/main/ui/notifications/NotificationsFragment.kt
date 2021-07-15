package com.auxy.archapp.main.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.auxy.archapp.databinding.FragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment() {
    private val notificationsViewModel: NotificationsViewModel by viewModels()

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationsViewModel.text.observe(viewLifecycleOwner, {
            binding.textNotifications.text = it
        })
        notificationsViewModel.isBusy.observe(viewLifecycleOwner, { isBusy ->
            if (isBusy) {
                binding.progressBar.show()
            } else {
                binding.progressBar.hide()
            }
        })
        binding.refresh.setOnClickListener {
            notificationsViewModel.refresh()
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
