package ru.aslazarev.mvp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.aslazarev.mvp.App
import ru.aslazarev.mvp.databinding.FragmentUsersBinding
import ru.aslazarev.mvp.model.db.Database
import ru.aslazarev.mvp.model.db.RoomGithubUsersCache
import ru.aslazarev.mvp.model.remote.GitHubUsersRepo
import ru.aslazarev.mvp.presentation.UsersPresenter
import ru.aslazarev.mvp.navigation.BackButtonListener
import ru.aslazarev.mvp.utils.AndroidNetworkStatus
import ru.aslazarev.mvp.view.images.GlideImageLoader
import ru.aslazarev.mvp.view.ui.adapter.UsersRVAdapter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter { UsersPresenter(GitHubUsersRepo(
        AndroidNetworkStatus(requireContext()),
        RoomGithubUsersCache(Database.getInstance())
    ), App.instance.router) }
    var adapter: UsersRVAdapter? = null

    private var binding: FragmentUsersBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun init() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}