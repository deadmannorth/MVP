package ru.aslazarev.mvp.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.aslazarev.mvp.R
import ru.aslazarev.mvp.model.GitHubUser

class ReposRVAdapter(user: GitHubUser): RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    val list = user.ReposList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount() = list!!.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(repos: GitHubUser.GitHubUserRepos) {
            itemView.apply {
                findViewById<TextView>(R.id.repo_name).text = repos.name
                findViewById<TextView>(R.id.fork_count).text = repos.forks.toString()
            }
        }


    }
}