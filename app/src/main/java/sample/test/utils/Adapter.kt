package sample.test.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(getLayoutId(),p0,false))
    }
    override fun getItemCount(): Int {
        return getItemSize()
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as ViewHolder).bind(position = p1)
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(position:Int){
            bindView(itemView,position)
        }
    }
    abstract fun getLayoutId():Int
    abstract fun getItemSize():Int
    abstract fun bindView(view:View,position:Int)
}