package ru.aslazarev.mvp

class MainPresenter () {
    val model = CountersModel()

    fun counterClick(id: Int) = model.next(id)
}