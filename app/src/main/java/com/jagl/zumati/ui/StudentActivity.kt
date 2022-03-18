package com.jagl.zumati.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jagl.zumati.Constants.API_BASE_URL
import com.jagl.zumati.adapter.StudentAdapter
import com.jagl.zumati.databinding.ActivityStudentBinding
import com.jagl.zumati.provider.Heroku
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class StudentActivity : AppCompatActivity() {

    private val TAG = StudentActivity::class.java.simpleName
    private lateinit var binding: ActivityStudentBinding
    private val adapter = StudentAdapter(emptyList())

    companion object {
        const val EXTRA_HOUSE = "StudentActivity:house"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val house = intent.getStringExtra(EXTRA_HOUSE) ?: "default"
        Log.d(TAG,"The house selectes was $house")
        Log.d(TAG,"Init adapter")
        binding.recycler.adapter = adapter
        Log.d(TAG,"Make request to the server")
        doRequestOfStudents(house)
    }

    private fun doRequestOfStudents(house: String){
        lifecycleScope.launch {
                val url = "$API_BASE_URL"+"$house"
                Log.d(TAG,"Setting the url \n" +
                        "URL: $url")
                val students = withContext(Dispatchers.IO){ Heroku.service.getStudens(url) ?: emptyList() }
                Log.d(TAG,"Response\n" +
                        "Size: ${students.size}")
                if (students.isNotEmpty()){
                    adapter.students = students
                    Log.d(TAG,"Notifing the adapter")
                    adapter.notifyDataSetChanged()
                }else{
                    AlertDialog
                        .Builder(this@StudentActivity)
                        .setTitle("Students not found")
                        .setMessage("No students from this house were found")
                        .setOnCancelListener { onBackPressed() }
                        .show()
                }
            binding.progressBar.visibility = View.GONE
        }
    }
}
