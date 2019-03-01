package sample.test.ui.album

import android.view.View
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import kotlinx.android.synthetic.main.item_album.view.*
import sample.test.R
import sample.test.models.Album
import sample.test.utils.Adapter

class AlbumAdapter(val listener: Listener) : Adapter(){
    private var generator = ColorGenerator.MATERIAL
    private var mAlbums = mutableListOf<Album>()

    fun setAlbums(albums:MutableList<Album>){
        mAlbums = albums
        notifyDataSetChanged()
    }

    override fun getLayoutId(): Int  = R.layout.item_album

    override fun getItemSize(): Int  = mAlbums.size

    override fun bindView(itemView: View, position: Int) {

        val letter = mAlbums[position].title?.get(0).toString()
        val drawable = TextDrawable.builder().buildRound(letter, generator.randomColor)
        itemView.imageView.setImageDrawable(drawable)
        itemView.titleView.text = mAlbums[position].title

        itemView.tag = position
        itemView.setOnClickListener { v ->
            val index = v.tag as Int
            listener.onItemClickedAt(mAlbums.get(index))
        }

    }

    interface Listener{
        fun onItemClickedAt(album: Album)
    }

}