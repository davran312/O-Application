package sample.test.ui.album_photos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_fullscreen.*
import sample.test.BaseActivity
import sample.test.R
import sample.test.utils.Const

class FullscreenPhoto :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        val link  = intent.extras.getString(Const.EXTRA_LINK)
        Glide.with(this).load(link).into(iwFullscreen)
    }

}