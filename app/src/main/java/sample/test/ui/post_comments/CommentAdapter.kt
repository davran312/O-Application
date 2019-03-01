package sample.test.ui.post_comments

import android.view.View
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import kotlinx.android.synthetic.main.item_comment.view.*
import sample.test.R
import sample.test.models.Comment
import sample.test.utils.Adapter

class CommentAdapter:Adapter(){
    private val generator = ColorGenerator.MATERIAL
    private var mComments = mutableListOf<Comment>()

    fun setComments(comments:MutableList<Comment>){
        mComments = comments
        notifyDataSetChanged()
    }
    override fun getLayoutId(): Int  = R.layout.item_comment

    override fun getItemSize(): Int = mComments.size

    override fun bindView(itemView: View, position: Int) {

        val letter = mComments[position].name?.get(0).toString()
        // Create a new TextDrawable for our image's background
        val drawable = TextDrawable.builder().buildRound(letter, generator.randomColor)
        itemView.imageView.setImageDrawable(drawable)
        itemView.nameView?.text = mComments[position].name
        itemView.emailView?.text = mComments[position].email
        itemView.bodyView?.text = mComments[position].body
    }



}
