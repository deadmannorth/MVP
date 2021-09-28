package ru.aslazarev.mvp.view.ui.adapter

import ru.aslazarev.mvp.view.items.IItemView

interface RepoItemView: IItemView {
    fun setRepo(name: String)
    fun setFork(fork: String)
}