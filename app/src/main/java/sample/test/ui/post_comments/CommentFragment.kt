package sample.test.ui.post_comments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_comments.*
import sample.test.BaseFragment
import sample.test.R
import sample.test.models.Comment
import sample.test.models.Post
import sample.test.utils.Const

const val TAG = "CommentFragment"
class CommentFragment : BaseFragment(){

    lateinit var mAdapter: CommentAdapter
    lateinit var mCommentObserver:Observer<MutableList<Comment>>
    lateinit var mPost:Post
    override fun getLayoutResId(): Int = R.layout.fragment_comments

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         mPost = arguments!!.getParcelable(Const.EXTRA_POST) as Post
        init()
    }
    private fun init(){
        initTitle()
        initAdapter()
        initObservers()
        initViewModel()
        initRefresh()

    }

    private fun initTitle() {
        postTitle.text = mPost.title
        postDescription.text = mPost.body
    }

    private fun initRefresh() {
        swipeRefresh.setOnRefreshListener {
            mMainViewModel.getCommentsResponse(mPost.id)
        }
    }

    private fun initViewModel(){
        mMainViewModel.getComments().observe(this,mCommentObserver)
        mMainViewModel.getCommentsResponse(mPost.id)
    }
    private fun initObservers(){
        mCommentObserver = Observer {
            mAdapter.setComments(comments = it!!)
        }
    }

    private fun initAdapter() {
        mAdapter = CommentAdapter()
        recyclerView.adapter = mAdapter
    }
}