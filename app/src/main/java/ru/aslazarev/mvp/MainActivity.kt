package ru.aslazarev.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.aslazarev.mvp.databinding.ActivityMainBinding
import ru.aslazarev.mvp.model.GitHubUsersRepo
import ru.aslazarev.mvp.view.ui.UsersRVAdapter

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(GitHubUsersRepo()) }
    private lateinit var adapter: UsersRVAdapter



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(presenter.userListPresenter)
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }
}