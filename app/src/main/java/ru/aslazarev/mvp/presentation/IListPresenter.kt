package ru.aslazarev.mvp.presentation

import ru.aslazarev.mvp.view.IItemView
import ru.aslazarev.mvp.view.UserItemView

interface IListPresenter <V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int

}

interface IUserListPresenter: IListPresenter<UserItemView> {

}