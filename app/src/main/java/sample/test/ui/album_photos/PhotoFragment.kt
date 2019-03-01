package sample.test.ui.album_photos

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_photos.*
import sample.test.BaseFragment
import sample.test.R
import sample.test.models.Album
import sample.test.models.Photos
import sample.test.utils.Const

const val TAG = "PhotoFragment"
class PhotoFragment : BaseFragment(),PhotoAdapter.Listener{

    companion object {
        fun getInstance(album:Album):PhotoFragment{
            val framgent = PhotoFragment()
            val bundle = Bundle()
            bundle.putParcelable(Const.EXTRA_ALBUM,album)
            framgent.arguments = bundle
            return framgent
        }
    }


    override fun getLayoutResId(): Int = R.layout.fragment_photos
    private lateinit var mAdapter: PhotoAdapter
    private lateinit var mAlbumPhotoObserver: Observer<MutableList<Photos>>
    lateinit var mAlbum:Album

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAlbum = arguments?.getParcelable(Const.EXTRA_ALBUM)!!
        init()
    }
    private fun init(){
        initAdapter()
        initAlbumObserver()
        initViewModel()
        initRefresh()

    }

    private fun initRefresh() {
        swipeRefresh.setOnRefreshListener {
            mMainViewModel.getAlbumPhotosResponse(mAlbum.id!!)
        }
    }

    private fun initViewModel() {
        mMainViewModel.getAlbumPhotos().observe(this,mAlbumPhotoObserver)
        mMainViewModel.getAlbumPhotosResponse(mAlbum.id!!)
    }

    private fun initAlbumObserver() {
        mAlbumPhotoObserver = Observer {
            toast("Hello")
            mAdapter.setPhotos(it!!)
        }
    }

    private fun initAdapter() {
        mAdapter = PhotoAdapter(this)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = GridLayoutManager(this.requireContext(),3)
    }



    override fun onItemClickedAt(sample: ImageView, photo: Photos) {
        val intent = Intent(this.requireContext(), FullscreenPhoto::class.java)
        intent.putExtra(Const.EXTRA_LINK,photo.url)
        val picturePair = Pair.create(sample as View,"sample")
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this.requireActivity(),picturePair)
        ActivityCompat.startActivity(this.requireContext(),intent,options.toBundle())
    }

}