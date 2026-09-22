package com.example.weekendwize

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subject(
    val name: String,
    val grade: String,
    val topics: List<Topic>,
    val theory: String = "",
    val notes: String = "",
    val revision: String = ""
) : Parcelable

@Parcelize
data class Topic(
    val name: String,
    val subTopics: List<SubTopic>,
    val theory: String = "",
    val notes: String = ""
) : Parcelable

// All nested classes must also implement Parcelable for the parent to be Parcelable
@Parcelize
data class SubTopic(
    val name: String,
    val quiz: Quiz,
    val theory: String = "",
    val notes: String = ""
) : Parcelable

@Parcelize
data class Quiz(
    val questions: List<Question>
) : Parcelable

@Parcelize
data class Question(
    val text: String,
    val options: List<String>,
    val correctOptionIndex: Int
) : Parcelable