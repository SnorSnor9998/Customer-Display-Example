package com.snor.customerdisplayexample

import android.app.Presentation
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.Display
import android.view.SurfaceHolder
import com.snor.customerdisplayexample.databinding.DisplayVideoBinding
import java.io.IOException

class DisplayVideo(context: Context, display: Display, private val video: Int) :
    Presentation(context, display) {

    private lateinit var binding: DisplayVideoBinding

    private var mPlayer: MediaPlayer? = null
    private var surfaceHolder: SurfaceHolder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DisplayVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        surfaceHolder = binding.surfaceView.holder
        surfaceHolder!!.addCallback(object : SurfaceHolder.Callback2 {
            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {}
            override fun surfaceDestroyed(p0: SurfaceHolder) {}
            override fun surfaceRedrawNeeded(p0: SurfaceHolder) {}
            override fun surfaceCreated(p0: SurfaceHolder) {
                mPlayer!!.setDisplay(surfaceHolder)
                mPlayer!!.start()
            }
        })

        mPlayer = MediaPlayer()
        prepareDataSource()

        // Loop
        mPlayer!!.setOnCompletionListener {
            mPlayer!!.reset()
            prepareDataSource()
            mPlayer!!.start()
        }


    }


    private fun prepareDataSource(){
        try {
            val applicationId : String = BuildConfig.APPLICATION_ID
            mPlayer!!.setDataSource(
                context,
                Uri.parse("android.resource://$applicationId/$video")
            )
            mPlayer!!.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mPlayer!!.isPlaying){
            mPlayer!!.stop()
            mPlayer!!.release()
        }
    }


}