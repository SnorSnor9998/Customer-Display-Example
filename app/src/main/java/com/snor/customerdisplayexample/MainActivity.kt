package com.snor.customerdisplayexample

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.snor.customerdisplayexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val list = arrayListOf<String>("1","2")
    private val liveData = MutableLiveData<ArrayList<String>>(list)

    private val cd = CustomerDisplay()

    override fun onStart() {
        super.onStart()

        cd.init(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Image Slider
        binding.btnDemo1.setOnClickListener {
            val imageList = listOf(R.drawable.one,R.drawable.two,R.drawable.three)
            cd.displayPicture(imageList)
        }

        //Play Video
        binding.btnDemo2.setOnClickListener {
            cd.displayVideo(R.raw.example)
        }

        //Custom
        binding.btnDemo3.setOnClickListener {
            cd.displayCustom(liveData)
        }

        //Item +1
        binding.btnDemo4.setOnClickListener {
            list.add((list.size+1).toString())
            liveData.value = list
        }


        binding.btnDisplay.setOnClickListener {
            selectDisplay()
        }

    }

    private fun selectDisplay(){
        val list = cd.getDisplay()
        if(list.size >1){
            val name = arrayListOf<String>()

            list.forEach {
                name.add(it.name)
            }

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Display")
            builder.setSingleChoiceItems(name.toTypedArray(),1) { dialog, index ->
                cd.setDisplay(index)
                dialog.dismiss()
            }
            builder.create()
            builder.show()
        }else{
            Toast.makeText(applicationContext,"No Extra Screen Found", Toast.LENGTH_SHORT).show()
        }
    }

}