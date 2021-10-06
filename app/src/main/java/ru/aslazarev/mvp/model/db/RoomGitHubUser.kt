package ru.aslazarev.mvp.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubUser (
    @PrimaryKey var id: String,
    var login: String,
    var avatarUrl: String,
    var reposUrl: String
)