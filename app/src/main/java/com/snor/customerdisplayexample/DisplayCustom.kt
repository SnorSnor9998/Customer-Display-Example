package com.snor.customerdisplayexample

import android.app.Activity
import android.app.Presentation
import android.content.Context
import android.os.Bundle
import android.view.Display
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.snor.customerdisplayexample.databinding.DisplayCustomBinding

class DisplayCustom(
    private val mContext: Context,
    display: Display,
    private val list: MutableLiveData<ArrayList<String>>
) : Presentation(mContext, display) {

    private lateinit var binding : DisplayCustomBinding
    private val adapter = RvAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DisplayCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter

        list.observe(mContext as LifecycleOwner, Observer {
            adapter.setData(it)
            binding.recyclerView.scrollToPosition(it.size-1)
        })

    }


}