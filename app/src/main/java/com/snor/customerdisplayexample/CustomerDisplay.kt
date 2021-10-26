package com.snor.customerdisplayexample

import android.content.Context
import android.content.Context.DISPLAY_SERVICE
import android.hardware.display.DisplayManager
import android.util.Log
import android.view.Display
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

class CustomerDisplay() {

    private lateinit var mContext: Context

    private var mDisplayManager: DisplayManager? = null
    private lateinit var displays: Array<Display>
    private var selectedDisplay: Display? = null


    private var displayType: DisplayType? = null
    private var displayPicture: DisplayPicture? = null
    private var displayVideo: DisplayVideo? = null

    //Custom
    private var displayCustom : DisplayCustom?=null


    fun init(context:Context) {
        this.mContext = context

        mDisplayManager = mContext.getSystemService(DISPLAY_SERVICE) as DisplayManager
        displays = mDisplayManager!!.getDisplays(null)

        displays.forEach {
            Log.d("SNOR","$it")
        }

        if (displays.size > 1) {
            selectedDisplay = displays[1]
        }else{
            Toast.makeText(context,"No Extra Screen Found",Toast.LENGTH_SHORT).show()
            return
        }

    }

    fun getDisplay(): Array<Display> {
        return displays
    }

    fun setDisplay(index: Int) {
        if(index == 0){
            Toast.makeText(mContext,"Nope :)",Toast.LENGTH_SHORT).show()
            return
        }

        try {
            selectedDisplay = displays[index]
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun displayPicture(imageList: List<Int>){
        if (selectedDisplay != null){
            closePanel()
            displayType = DisplayType.PICTURE
            displayPicture = DisplayPicture(mContext,selectedDisplay!!,imageList)
            displayPicture!!.show()
        }
    }

    fun displayVideo(video : Int){
        if (selectedDisplay != null){
            closePanel()
            displayType = DisplayType.VIDEO
            displayVideo = DisplayVideo(mContext,selectedDisplay!!,video)
            displayVideo!!.show()
        }
    }

    fun displayCustom(liveData: MutableLiveData<ArrayList<String>>) {
        if (selectedDisplay != null){
            closePanel()
            displayType = DisplayType.CUSTOM
            displayCustom = DisplayCustom(mContext,selectedDisplay!!,liveData)
            displayCustom!!.show()
        }
    }


   private fun closePanel(){
       when(displayType){
           DisplayType.VIDEO ->{
               displayVideo!!.dismiss()
               displayVideo = null
           }
           DisplayType.PICTURE ->{
               displayPicture!!.dismiss()
               displayPicture = null
           }
           DisplayType.CUSTOM ->{
               displayCustom!!.dismiss()
               displayCustom = null
           }
           else -> {

           }
       }


   }


    private enum class DisplayType {
        PICTURE,
        VIDEO,
        CUSTOM
    }


}