package ru.kor.test_it_cron.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.kor.test_it_cron.R
import ru.kor.test_it_cron.databinding.FragmentUserDetailsBinding
import ru.kor.test_it_cron.extension.loadAvatar
import ru.kor.test_it_cron.viewmodel.UserViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class UserDetailsFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserDetailsBinding.inflate(inflater, container, false)

        val arg = arguments?.getString("login")
        if (arg != null) {
            userViewModel.getUser(arg)
        }

        binding.topBar.setNavigationOnClickListener {
            userViewModel.resetDetailUserData()
            findNavController().navigateUp()
        }

        userViewModel.detailUserData.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                with(binding) {
                    ivAvatar.loadAvatar(user.avatar_url)
                    tvName.text = user.name
                    tvEmail.text = user.email ?: getString(R.string.empty_email)

                    follower.text = buildString {
                        append(user.followers)
                        append(" ")
                        append(requireContext().getString(R.string.followers))
                    }

                    following.text = buildString {
                        append(user.following)
                        append(" ")
                        append(requireContext().getString(R.string.following))
                    }


                    organization.text = user.company ?: getString(R.string.empty_company)
                    date.text = OffsetDateTime.parse(user.created_at)
                        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback {
            userViewModel.resetDetailUserData()
            findNavController().navigateUp()
        }

        return binding.root
    }

}