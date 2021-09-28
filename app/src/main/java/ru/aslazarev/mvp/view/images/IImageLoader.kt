package ru.aslazarev.mvp.view.images

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}