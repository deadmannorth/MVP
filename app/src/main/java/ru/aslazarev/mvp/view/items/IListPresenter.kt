package ru.aslazarev.mvp.presentation

import ru.aslazarev.mvp.view.items.IItemView
import ru.aslazarev.mvp.view.ui.adapter.UserItemView

interface IListPresenter <V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int

}

interface IUserListPresenter: IListPresenter<UserItemView> {

}