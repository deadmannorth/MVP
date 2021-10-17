package ru.aslazarev.mvp.view.ui

import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

interface FragmentInitializer<T : Parcelable> {

    fun newInstance(arg: T): Fragment {
        val declaringClassName = this::class.java.declaringClass?.name
        return FragmentFactory().instantiate(
            this::class.java.declaringClass?.classLoader!!,
            declaringClassName!!
        ).apply {
            arguments = bundleOf(
                declaringClassName to arg
            )
        }
    }
}

fun <T : Parcelable> Fragment.extractInitParams(): T {
    val initParams = requireArguments().getParcelable<T>(this::class.java.name)
    return initParams!!
}

fun <T : Parcelable> Fragment.initParams() = lazy<T> { extractInitParams() }