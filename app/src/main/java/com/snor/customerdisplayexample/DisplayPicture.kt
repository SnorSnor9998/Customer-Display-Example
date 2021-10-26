package com.snor.customerdisplayexample

import android.app.Presentation
import android.content.Context
import android.os.Bundle
import android.view.Display
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.snor.customerdisplayexample.databinding.DisplayPictureBinding
import java.util.ArrayList

class DisplayPicture(context: Context, display: Display,private val picture: List<Int>) : Presentation(context, display) {

    private lateinit var binding: DisplayPictureBinding
    private val imageList = ArrayList<SlideModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DisplayPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        picture.forEach {
            imageList.add(SlideModel(it, ScaleTypes.FIT))
        }
        binding.imageSlider.setImageList(imageList)

    }



}