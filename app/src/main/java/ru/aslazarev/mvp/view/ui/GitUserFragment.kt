package ru.aslazarev.mvp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.aslazarev.mvp.App
import ru.aslazarev.mvp.databinding.FragmentGitUserBinding
import ru.aslazarev.mvp.model.GitHubUser
import ru.aslazarev.mvp.presentation.GitUserPresenter
import ru.aslazarev.mvp.view.BackButtonListener

class GitUserFragment: MvpAppCompatFragment(), GitUserView, BackButtonListener {

    var binding: FragmentGitUserBinding? = null
    val presenter: GitUserPresenter by moxyPresenter { GitUserPresenter(App.instance.router) }

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
        val gitUser = arguments?.getParcelable<GitHubUser>(KEY_ARG)
        binding?.userName?.text = gitUser?.login
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed() = presenter.backPressed()

}