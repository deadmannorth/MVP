package ru.aslazarev.mvp.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.aslazarev.mvp.databinding.ItemUserBinding
import ru.aslazarev.mvp.presentation.IUserListPresenter
import ru.aslazarev.mvp.view.UserItemView

class UsersRVAdapter (val presenter: IUserListPresenter)
    :RecyclerView.Adapter<UsersRVAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root), UserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(vb){
            tvLogin.text = text
        }
    }
}