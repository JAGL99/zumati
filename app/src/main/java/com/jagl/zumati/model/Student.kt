package com.jagl.zumati.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val actor: String,
    val alive: Boolean,
    val alternate_actors: List<String>,
    val alternate_names: List<String>,
    val ancestry: String,
    val dateOfBirth: String,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val image: String,
    val name: String,
    val patronus: String,
    val species: String,
    val wand: Wand,
    val wizard: Boolean,
    val yearOfBirth: String
): Parcelable

@Parcelize
data class Wand(
    val core: String,
    val length: String,
    val wood: String
): Parcelable