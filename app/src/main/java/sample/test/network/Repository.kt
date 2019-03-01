package sample.test.network

import dagger.internal.GenerationOptions
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import sample.test.models.Album
import sample.test.models.Comment
import sample.test.models.Photos
import sample.test.models.Post
import sample.test.utils.Const

interface Repository {

    @GET
    fun getAlbums(@Url url:String) : Observable<MutableList<Album>>

    @GET("albums/{albumId}/photos")
    fun getAlbumPhotos(@Path("albumId") albumId:Int):Observable<MutableList<Photos>>

    @GET
    fun getPosts(@Url url:String) : Observable<MutableList<Post>>

    @GET("posts/{postId}/comments")
    fun loadPostComments(@Path("postId") postid: Int): Observable<MutableList<Comment>>


}