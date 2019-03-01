package sample.test.ui.publication

import android.view.View
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import kotlinx.android.synthetic.main.item_post.view.*
import sample.test.R
import sample.test.models.Post
import sample.test.utils.Adapter

class PublicationAdapter(private val listener:Listener) : Adapter(){
    private val generator = ColorGenerator.MATERIAL
    private var mPosts = mutableListOf<Post>()

    fun setPosts(posts:MutableList<Post>){
        mPosts = posts
        notifyDataSetChanged()
    }
    override fun getLayoutId(): Int = R.layout.item_post

    override fun getItemSize(): Int = mPosts.size

    override fun bindView(itemView: View, position: Int) {
        itemView.mainView.setBackgroundColor(0)
        val letter = mPosts[position].title?.get(0).toString()
        val drawable = TextDrawable.builder().buildRound(letter, generator.randomColor)
        itemView.imageView.setImageDrawable(drawable)
        itemView.titleView.text = mPosts[position].title
        itemView.descriptionView.text = mPosts[position].body

        itemView.tag = mPosts[position]
        itemView.setOnClickListener { v ->
            val mPost = v.tag as Post
            listener.onPostClicked(mPost)
        }
    }
    interface Listener{
        fun onPostClicked(post:Post)
    }

}