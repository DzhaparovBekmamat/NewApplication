package com.chihuahua.pokereducation.ui.fragments.choices.quiz.model

/**
 * Author: Dzhaparov Bekmamat
 */
data class QuizModel(
    val id: Int,
    val level: String,
    val description: String,
    val image: Int,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val correctAnswer: Int
)