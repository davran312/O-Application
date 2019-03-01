package sample.test

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import sample.test.di.modules.ViewModelFactory
import sample.test.ui.album.AlbumFragment
import sample.test.ui.publication.PublicationFragment
import sample.test.utils.FragmentTransaction
import sample.test.utils.FragmentTransactionManager

abstract class BaseActivity : DaggerAppCompatActivity() {
    private lateinit var mTransactionManager: FragmentTransactionManager


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_posts -> {
                if(navigation.selectedItemId != R.id.navigation_posts) {
                    openView(Fragments.PUBLICATION)
                    return@OnNavigationItemSelectedListener true
                }
            }
            R.id.navigation_albums -> {
                if(navigation.selectedItemId != R.id.navigation_albums){

                openView(Fragments.ALBUM)
                return@OnNavigationItemSelectedListener true
            }

            }
        }
        false
    }

    private fun openView(album: Fragments) {
        getTransactionManager().replaceFragment(
            when(album){
                Fragments.ALBUM -> AlbumFragment()
                else -> PublicationFragment()
            }
        ,R.id.frameContainer,true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setupTransactionManager()
        openView(Fragments.PUBLICATION)

    }

    private fun setupTransactionManager() {
        mTransactionManager = FragmentTransaction(supportFragmentManager)
    }

    abstract fun getLayoutResId(): Int

    fun getTransactionManager():FragmentTransactionManager = mTransactionManager
}
