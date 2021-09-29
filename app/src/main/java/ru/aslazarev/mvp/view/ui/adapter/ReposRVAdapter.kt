package ru.aslazarev.mvp.view.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.aslazarev.mvp.R
import ru.aslazarev.mvp.databinding.ItemRepoBinding
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.presentation.IRepoListPresenter

class ReposRVAdapter(val presenter: IRepoListPresenter): RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(val vb: ItemRepoBinding): RecyclerView.ViewHolder(vb.root),
    RepoItemView{

        override var pos = -1

        override fun setRepo(name: String) = with(vb){
            repoName.text = name
        }

        override fun setFork(fork: String) = with(vb){
            forkCount.text = "forks: $fork"
        }
    }
}