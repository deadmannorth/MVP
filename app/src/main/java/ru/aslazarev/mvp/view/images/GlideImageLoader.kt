package ru.aslazarev.mvp.view.images

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class GlideImageLoader: IImageLoader<ImageView>  {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(container)
    }
}