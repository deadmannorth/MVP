package ru.aslazarev.mvp.model.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitHubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE

    )]
)
data class RoomGitHubRepository (
    @PrimaryKey var id: String,
    var name: String,
    var forks: String,
    var userId: String
)