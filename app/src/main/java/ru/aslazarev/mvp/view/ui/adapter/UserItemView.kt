package ru.aslazarev.mvp.view.ui.adapter

import ru.aslazarev.mvp.view.items.IItemView

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}