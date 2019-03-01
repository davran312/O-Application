package sample.test.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sample.test.BaseActivity
import sample.test.MainActivity
import sample.test.ui.album.AlbumFragment
import sample.test.ui.album_photos.FullscreenPhoto
import sample.test.ui.album_photos.PhotoFragment
import sample.test.ui.post_comments.CommentFragment
import sample.test.ui.publication.PublicationFragment

@Module
abstract  class ViewBuilder{

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindBaseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun bindAlbumFragment():AlbumFragment

    @ContributesAndroidInjector
    abstract fun bindPhotosFragment():PhotoFragment

    @ContributesAndroidInjector
    abstract fun bindFullscreenActivity():FullscreenPhoto

    @ContributesAndroidInjector
    abstract fun bindPublicationFragment():PublicationFragment

    @ContributesAndroidInjector
    abstract fun bindCommentFragment(): CommentFragment
}