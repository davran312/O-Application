package sample.test.ui.album

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_album.*
import sample.test.BaseFragment
import sample.test.R
import sample.test.models.Album
import sample.test.ui.album_photos.PhotoFragment

const val TAG = "AlbumFragment"
class AlbumFragment : BaseFragment(),AlbumAdapter.Listener{

    override fun getLayoutResId(): Int = R.layout.fragment_album

    private lateinit var mAdapter: AlbumAdapter

    lateinit var mAlbumsObserver:Observer<MutableList<Album>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        initAdapter()
        initAlbumObserver()
        initViewModel()
        initRefresh()
    }

    private fun initAlbumObserver() {
        mAlbumsObserver = Observer {
            mAdapter.setAlbums(it!!)
        }
    }

    private fun initViewModel() {
        mMainViewModel.getAlbums().observe(this,mAlbumsObserver)

        mMainViewModel.getAlbumsResponse()
    }


    private fun initAdapter() {
        mAdapter = AlbumAdapter(this)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = GridLayoutManager(this.requireContext(),3)
    }

    private fun initRefresh() {
        swipeRefresh.setOnRefreshListener {
            mMainViewModel.getAlbumsResponse()
        }
    }


    override fun onItemClickedAt(album: Album) {
        val fragment = PhotoFragment.getInstance(album)
        getTransactionManager().replaceFragment(fragment,R.id.frameContainer,true)

    }

}