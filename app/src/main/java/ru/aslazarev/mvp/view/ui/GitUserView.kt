package ru.aslazarev.mvp.view.ui

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface GitUserView: MvpView {
    fun init()
    fun updateList()
}