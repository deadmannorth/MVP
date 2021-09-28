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
import ru.aslazarev.mvp.presentation.GitUserPresenter
import ru.aslazarev.mvp.navigation.BackButtonListener
import ru.aslazarev.mvp.view.images.GlideImageLoader
import ru.aslazarev.mvp.view.ui.adapter.ReposRVAdapter
import ru.aslazarev.mvp.view.ui.adapter.UsersRVAdapter

class GitUserFragment: MvpAppCompatFragment(), GitUserView, BackButtonListener {

    var binding: FragmentGitUserBinding? = null
    val presenter: GitUserPresenter by moxyPresenter { GitUserPresenter(App.instance.router) }
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
        gitUser = arguments?.getParcelable<GitHubUser>(KEY_ARG)
        presenter.loadData(gitUser!!)
        binding?.userName?.text = gitUser?.login
        binding?.userAvatar?.let { GlideImageLoader().loadInto(gitUser!!.avatarUrl!!, it) }
    }

    override fun initRepos() {
        if (gitUser!!.ReposList != null) {
            binding?.userRepos?.layoutManager = LinearLayoutManager(context)
            adapter = ReposRVAdapter(gitUser!!)
            binding?.userRepos?.adapter = adapter
        }
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