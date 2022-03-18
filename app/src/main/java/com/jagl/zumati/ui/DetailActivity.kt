package com.jagl.zumati.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jagl.zumati.databinding.ActivityDetailBinding
import com.jagl.zumati.loadUrl
import com.jagl.zumati.model.Student

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object{
        const val EXTRA_STUDENT = "DetailActivity:student"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val student: Student? = intent.getParcelableExtra(EXTRA_STUDENT)
        student?.let {
            bind(it)
            checkFields(it)
        }
    }

    private fun checkFields(student: Student) {
        with(binding){
            student.let {
                hideField(it.name, studentName)
                hideField(it.alternate_names, studentAlternateNames)
                hideField(it.gender, studentGender)
                hideField(it.species, studentSpecies)
                hideField(it.dateOfBirth, studentDateOfBirth)
                hideField(it.ancestry, studentAncestry)
                hideField(it.eyeColour, studentEyeColour)
                hideField(it.hairColour, studentHairColour)
                hideField(it.patronus, studentPatronus)
                hideField(it.wand.wood, wandWood)
                hideField(it.wand.length, wandLength)
                hideField(it.wand.core, wandCore)
                if (wandCore.visibility == View.GONE
                    && wandCore.visibility == wandLength.visibility
                    && wandCore.visibility == wandWood.visibility){
                    wandField.visibility = View.GONE
                }else{
                    wandField.setCardBackgroundColor(Color.TRANSPARENT)
                    wandField.cardElevation = 0.0f
                }
            }
        }
    }

    private fun bind(student: Student){
        with(binding){
            studentName.text = "Name: ${student.name}"
            studentAlternateNames.text = "Alternate Names: ${student.alternate_names}"
            studentGender.text = "Gender: ${student.gender}"
            studentSpecies.text = "Species: ${student.species}"
            studentDateOfBirth.text = "Date of birth: ${student.dateOfBirth}"
            studentAncestry.text = "Ancestry: ${student.ancestry}"
            studentEyeColour.text = "Eyes Color: ${student.eyeColour}"
            studentHairColour.text = "Hair Color: ${student.hairColour}"
            studentPatronus.text = "Patronus: ${student.patronus}"
            wandWood.text = "Wood: ${student.wand.wood}"
            wandCore.text = "Core: ${student.wand.core}"
            wandLength.text = "Length: ${student.wand.length}"
            studentImage.loadUrl(student.image)
        }
    }

    private fun isEmptyOrBlank(field: String) = field.isBlank() || field.isBlank()

    private fun hideField(field: String, textView: TextView) {
        if (isEmptyOrBlank(field)){
            textView.visibility = View.GONE
        }
    }
    private fun hideField(field: List<String>, textView: TextView) {
        if (field.isEmpty()){
            textView.visibility = View.GONE
        }
    }
}