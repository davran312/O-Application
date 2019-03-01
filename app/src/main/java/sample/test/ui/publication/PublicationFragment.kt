package sample.test.ui.publication

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_posts.*
import sample.test.BaseFragment
import sample.test.R
import sample.test.models.Post
import sample.test.ui.post_comments.CommentFragment
import sample.test.utils.Const

class PublicationFragment : BaseFragment(),PublicationAdapter.Listener{

    override fun getLayoutResId(): Int = R.layout.fragment_posts
    private lateinit var mAdapter:PublicationAdapter
    private lateinit var mPostsObserver:Observer<MutableList<Post>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        initAdapter()
        initObservers()
        initViewModel()
        initRefreshLayout()

    }

    private fun initRefreshLayout() {
        swipeRefresh.setOnRefreshListener {
            mMainViewModel.getPostResponse()
        }
    }

    private fun initObservers(){
        mPostsObserver = Observer {
            mAdapter.setPosts(it!!)
        }
    }
    private fun initViewModel(){
        mMainViewModel.getPosts().observe(this,mPostsObserver)
        mMainViewModel.getPostResponse()

    }

    private fun initAdapter() {
        mAdapter = PublicationAdapter(this)
        recyclerView.adapter = mAdapter
    }

    override fun onPostClicked(post: Post) {
        val bundle = Bundle()
        bundle.putParcelable(Const.EXTRA_POST,post)
        getTransactionManager().replaceFragmentWithArguments(CommentFragment(),R.id.frameContainer,bundle,true)
    }

}