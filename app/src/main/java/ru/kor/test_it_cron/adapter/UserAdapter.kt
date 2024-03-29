package ru.kor.test_it_cron.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.kor.test_it_cron.databinding.CardUserBinding
import ru.kor.test_it_cron.dto.User
import ru.kor.test_it_cron.extension.loadAvatar

interface OnInteractionListener {
    fun openCard(userLogin: String)
}

class UserAdapter(
    private val onInteractionListener: OnInteractionListener
) : PagingDataAdapter<User, UserVH>(UserCB()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        val binding = CardUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserVH(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }
}

class UserVH(
    private val binding: CardUserBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        with(binding) {
            tvLogin.text = user.login
            tvId.text = user.id.toString()

            ivAvatar.loadAvatar(user.avatar_url)

            cardUser.setOnClickListener {
                onInteractionListener.openCard(user.login)
            }
        }
    }
}


class UserCB : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.login == newItem.login
    }

}
