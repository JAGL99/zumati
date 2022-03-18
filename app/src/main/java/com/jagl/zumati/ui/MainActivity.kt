package com.jagl.zumati.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jagl.zumati.Constants.GRYFFINDOR_IMAGE_URL
import com.jagl.zumati.Constants.HAFFLEPUFF_IMAGE_URL
import com.jagl.zumati.Constants.RAVENCLAW_IMAGE_URL
import com.jagl.zumati.Constants.SLYTHERIN_IMAGE_URL
import com.jagl.zumati.databinding.ActivityMainBinding
import com.jagl.zumati.loadUrl
import com.jagl.zumati.ui.StudentActivity.Companion.EXTRA_HOUSE

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setImages()
        setOnClickListener()

    }

    private fun setImages() {
        with(binding) {
            btnGryffindor.loadUrl(GRYFFINDOR_IMAGE_URL)
            btnRavenclaw.loadUrl(RAVENCLAW_IMAGE_URL)
            btnHafflepuff.loadUrl(HAFFLEPUFF_IMAGE_URL)
            btnSlytherin.loadUrl(SLYTHERIN_IMAGE_URL)
        }
    }

    private fun setOnClickListener() {
        with(binding){
            btnGryffindor.setOnClickListener { goToActivity("gryffindor") }
            btnRavenclaw.setOnClickListener { goToActivity("ravenclaw") }
            btnHafflepuff.setOnClickListener { goToActivity("hafflepuff") }
            btnSlytherin.setOnClickListener { goToActivity("slytherin") }
        }
    }

    private fun goToActivity(house: String){
        Log.d(TAG,"The house ${house.uppercase()} was select")
        val intent = Intent(this, StudentActivity::class.java)
            .putExtra(EXTRA_HOUSE,house)
        Log.d(TAG,"The house is in the intent")
        Log.d(TAG,"Start StudentActivity")
        startActivity(intent)
    }


}