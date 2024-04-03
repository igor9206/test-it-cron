package ru.kor.test_it_cron.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.kor.test_it_cron.R
import ru.kor.test_it_cron.adapter.OnInteractionListener
import ru.kor.test_it_cron.adapter.UserAdapter
import ru.kor.test_it_cron.databinding.FragmentMainBinding
import ru.kor.test_it_cron.viewmodel.UserViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        val adapter = UserAdapter(object : OnInteractionListener {
            override fun openCard(userLogin: String) {
                findNavController().navigate(
                    R.id.action_mainFragment_to_userDetailsFragment,
                    bundleOf("login" to userLogin)
                )
            }

        })
        binding.rvUsers.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.userData.collectLatest {
                adapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                binding.srl.isRefreshing = it.refresh is LoadState.Loading
            }
        }

        binding.srl.setOnRefreshListener {
            adapter.refresh()
        }

        return binding.root
    }

}