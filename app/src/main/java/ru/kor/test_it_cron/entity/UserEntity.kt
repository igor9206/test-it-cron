package ru.kor.test_it_cron.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kor.test_it_cron.dto.User

@Entity
data class UserEntity(
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
)

fun User.toEntity(): UserEntity = UserEntity(
    avatar_url,
    events_url,
    followers_url,
    following_url,
    gists_url,
    gravatar_id,
    html_url,
    id,
    login,
    node_id,
    organizations_url,
    received_events_url,
    repos_url,
    site_admin,
    starred_url,
    subscriptions_url,
    type,
    url
)

fun List<User>.toEntity(): List<UserEntity> = map {
    it.toEntity()
}

fun UserEntity.toDto(): User = User(
    avatar_url,
    events_url,
    followers_url,
    following_url,
    gists_url,
    gravatar_id,
    html_url,
    id,
    login,
    node_id,
    organizations_url,
    received_events_url,
    repos_url,
    site_admin,
    starred_url,
    subscriptions_url,
    type,
    url
)
