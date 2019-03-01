package sample.test

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sample.test.models.Album
import sample.test.models.Comment
import sample.test.models.Photos
import sample.test.models.Post
import sample.test.network.Repository
import sample.test.utils.ConnectionsManager
import sample.test.utils.Const
import sample.test.utils.Observer
import javax.inject.Inject

enum class ProgressStatus{
    SUCCESS,
    LOADING,
    ERROR,
}

const val TAG = "MainViewModel"
class MainViewModel @Inject constructor() : ViewModel(){
    @Inject
    lateinit var mService:Repository

    private val mMessagesLiveData = MutableLiveData<String>()
    private val mAlbumLiveData = MutableLiveData<MutableList<Album>>()
    private val mStatusLiveData = MutableLiveData<ProgressStatus>()
    private val mAlbumPhotoLiveData = MutableLiveData<MutableList<Photos>>()
    private val mPostLiveData = MutableLiveData<MutableList<Post>>()
    private val mCommentsLiveData = MutableLiveData<MutableList<Comment>>()

    @SuppressLint("CheckResult")
    fun getAlbumsResponse(){
        if(!ConnectionsManager.isNetworkOnline()){
            setMessage(BaseApplication.INSTANCE.getString(R.string.no_network))
        }
        else {
            setProgress(ProgressStatus.LOADING)
            mService.getAlbums(Const.albums).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:Observer<MutableList<Album>>(){
                    override fun next(t: MutableList<Album>) {
                        setAlbums(t)
                        setProgress(ProgressStatus.SUCCESS)
                    }

                    override fun error(e: Throwable) {
                        setMessage(e.localizedMessage)
                        setProgress(ProgressStatus.ERROR)
                    }

                })
        }
    }
    @SuppressLint("CheckResult")
    fun getAlbumPhotosResponse(albumId:Int){
        if(!ConnectionsManager.isNetworkOnline()){
            setMessage(BaseApplication.INSTANCE.getString(R.string.no_network))
        }
        else {
            setProgress(ProgressStatus.LOADING)
            mService.getAlbumPhotos(albumId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:Observer<MutableList<Photos>>(){
                    override fun next(t: MutableList<Photos>) {
                        setAlbumPhotos(photos = t)
                        setProgress(ProgressStatus.SUCCESS)
                    }

                    override fun error(e: Throwable) {
                        setMessage(e.localizedMessage)
                        setProgress(ProgressStatus.ERROR)
                    }

                })
        }
    }

    @SuppressLint("CheckResult")
    fun getPostResponse(){
        if(!ConnectionsManager.isNetworkOnline()){
            setMessage(BaseApplication.INSTANCE.getString(R.string.no_network))
        }
        else {
            setProgress(ProgressStatus.LOADING)
            mService.getPosts(Const.posts).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:Observer<MutableList<Post>>(){
                    override fun next(t: MutableList<Post>) {
                        setPosts(posts = t)
                        setProgress(ProgressStatus.SUCCESS)
                    }

                    override fun error(e: Throwable) {
                        setMessage(e.localizedMessage)
                        setProgress(ProgressStatus.ERROR)
                    }

                })
        }
    }
    @SuppressLint("CheckResult")
    fun getCommentsResponse(postId:Int){
        if(!ConnectionsManager.isNetworkOnline()){
            setMessage(BaseApplication.INSTANCE.getString(R.string.no_network))
        }
        else {
            setProgress(ProgressStatus.LOADING)
            mService.loadPostComments(postId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:Observer<MutableList<Comment>>(){
                    override fun next(t: MutableList<Comment>) {
                        setComments(comments =  t)
                        setProgress(ProgressStatus.SUCCESS)
                    }

                    override fun error(e: Throwable) {
                        setMessage(e.localizedMessage)
                        setProgress(ProgressStatus.ERROR)
                    }

                })
        }
    }





    private fun setMessage(message:String){
        mMessagesLiveData.postValue(message)
    }
    fun getMessages():LiveData<String> = mMessagesLiveData

    private fun setAlbums(albums:MutableList<Album>){
        mAlbumLiveData.postValue(albums)
    }
    fun getAlbums():LiveData<MutableList<Album>> = mAlbumLiveData

    private fun setProgress(status:ProgressStatus){
        mStatusLiveData.postValue(status)
    }
    fun getProgressStatus():LiveData<ProgressStatus> = mStatusLiveData

    private fun setAlbumPhotos(photos:MutableList<Photos>){
        mAlbumPhotoLiveData.postValue(photos)
    }
    fun getAlbumPhotos():LiveData<MutableList<Photos>> = mAlbumPhotoLiveData

    private fun setPosts(posts:MutableList<Post>){
        mPostLiveData.postValue(posts)
    }
    fun getPosts():LiveData<MutableList<Post>> = mPostLiveData

    private fun setComments(comments:MutableList<Comment>){
        mCommentsLiveData.postValue(comments)
    }
    fun getComments():LiveData<MutableList<Comment>> = mCommentsLiveData

}