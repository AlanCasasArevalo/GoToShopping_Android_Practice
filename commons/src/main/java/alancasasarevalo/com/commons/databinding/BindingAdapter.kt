package alancasasarevalo.com.commons.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("imageURL")

fun loadImageFromInternet(imageView: ImageView, url:String){

    Glide
            .with(imageView)
            .load(url)
            .into(imageView)

}









