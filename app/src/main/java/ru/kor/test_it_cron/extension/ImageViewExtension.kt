package ru.kor.test_it_cron.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.kor.test_it_cron.R

fun ImageView.loadAvatar(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_person_24)
        .error(R.drawable.ic_person_24)
        .timeout(10_000)
        .circleCrop()
        .into(this)
}