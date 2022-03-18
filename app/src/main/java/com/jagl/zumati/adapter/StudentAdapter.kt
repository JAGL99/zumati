package com.jagl.zumati.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jagl.zumati.databinding.LayoutStudentBinding
import com.jagl.zumati.loadUrl
import com.jagl.zumati.model.Student
import com.jagl.zumati.ui.DetailActivity

class StudentAdapter (var students: List<Student>):
    RecyclerView.Adapter<StudentAdapter.ViewHolder>(){

    private lateinit var context: Context
    private val TAG = StudentAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = LayoutStudentBinding.inflate(
            LayoutInflater
                .from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.itemView.setOnClickListener { goToActivity(context,student) }
        holder.bind(student)
    }

    override fun getItemCount() = students.size

    private fun goToActivity(context: Context, student: Student){
        Log.d(TAG,"Student ${student.name} was selected\n" +
                "Student: $student")
        val intent = Intent(context, DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_STUDENT,student)
        Log.d(TAG,"The student is in the intent")
        Log.d(TAG,"Start DetailActivity")
        context.startActivity(intent)
    }

    inner class ViewHolder(private val binding: LayoutStudentBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            with(binding){
                studentName.text = "Name: ${student.name}"
                studentGender.text = "Gender: ${student.gender}"
                studentImage.loadUrl(student.image)
            }
        }

    }
}