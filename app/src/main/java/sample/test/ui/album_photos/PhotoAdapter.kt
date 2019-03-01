package sample.test.ui.album_photos

import android.view.View
import android.widget.ImageView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_photo.view.*
import sample.test.R
import sample.test.models.Photos
import sample.test.utils.Adapter

class PhotoAdapter(private val listener:Listener) : Adapter(){
    private val generator = ColorGenerator.MATERIAL
    private var mPhotos = mutableListOf<Photos>()

    fun setPhotos(photos:MutableList<Photos>){
        mPhotos = photos
        notifyDataSetChanged()
    }

    override fun getLayoutId(): Int  = R.layout.item_photo

    override fun getItemSize(): Int = mPhotos.size

    override fun bindView(view: View, position: Int) {
        val letter = mPhotos[position].title?.get(0).toString()
        val drawable = TextDrawable.builder().buildRound(letter, generator.randomColor)
        Glide.with(view.context).load(mPhotos[position].thumbnailUrl).error(drawable).into(view.imageView)
        view.titleView.text = mPhotos[position].title
        view.tag = position
        view.setOnClickListener { v ->
            val index = v.tag as Int
            listener.onItemClickedAt(view.imageView,mPhotos[index])
        }

    }
    interface Listener{
        fun onItemClickedAt(sample: ImageView, photo:Photos)
    }
}