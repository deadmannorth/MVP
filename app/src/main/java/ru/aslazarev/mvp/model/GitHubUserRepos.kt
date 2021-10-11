package ru.aslazarev.mvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUserRepos(
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val forks: String? = null,
): Parcelable
