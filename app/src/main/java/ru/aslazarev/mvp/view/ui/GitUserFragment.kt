package ru.aslazarev.mvp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.aslazarev.mvp.App
import ru.aslazarev.mvp.databinding.FragmentGitUserBinding
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.model.db.Database
import ru.aslazarev.mvp.model.remote.GitHubRepositoryRepo
import ru.aslazarev.mvp.model.remote.GitHubUsersRepo
import ru.aslazarev.mvp.presentation.GitUserPresenter
import ru.aslazarev.mvp.navigation.BackButtonListener
import ru.aslazarev.mvp.utils.AndroidNetworkStatus
import ru.aslazarev.mvp.view.images.GlideImageLoader
import ru.aslazarev.mvp.view.ui.adapter.ReposRVAdapter

class GitUserFragment: MvpAppCompatFragment(), GitUserView, BackButtonListener {

    var binding: FragmentGitUserBinding? = null
    val presenter: GitUserPresenter by moxyPresenter { GitUserPresenter(
        GitHubRepositoryRepo(
            AndroidNetworkStatus(requireContext()),
            Database.getInstance(),
            arguments?.getParcelable(KEY_ARG)!!
        ),
                App.instance.router) }
    var adapter: ReposRVAdapter? = null
    var gitUser: GitHubUser? = null

    companion object{
        private const val KEY_ARG = "USER_GIT"
        fun newInstance(user: GitHubUser): GitUserFragment {
            return GitUserFragment().apply {
                arguments = bundleOf(KEY_ARG to user)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentGitUserBinding.inflate(inflater, container, false).also { binding = it }.root


    override fun init(){
        gitUser = arguments?.getParcelable(KEY_ARG)
        presenter.loadData()
        binding?.userName?.text = gitUser?.login
        binding?.userAvatar?.let { GlideImageLoader().loadInto(gitUser!!.avatarUrl!!, it) }
        binding?.userRepos?.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.repoListPresenter)
        binding?.userRepos?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

}