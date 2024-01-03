package com.chihuahua.pokereducation.ui.fragments.choices.quiz.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chihuahua.pokereducation.ui.fragments.choices.quiz.model.QuizModel
import com.chihuahua.pokereducation.ui.fragments.choices.quiz.model.Constants
import com.template.newapplication.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var currentQuiz: QuizModel
    private var currentQuestionIndex = 0
    private lateinit var alertDialog: AlertDialog.Builder
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadQuestion()
    }

    private fun loadQuestion() {
        val questionList = Constants.getQuestion()

        if (currentQuestionIndex < questionList.size) {
            currentQuiz = questionList[currentQuestionIndex]
            binding.tvQuestion.text = currentQuiz.question
            binding.tvOptionOne.text = currentQuiz.optionOne
            binding.tvOptionTwo.text = currentQuiz.optionTwo

            // Set click listeners for options
            binding.tvOptionOne.setOnClickListener { checkAnswer(1) }
            binding.tvOptionTwo.setOnClickListener { checkAnswer(2) }
            // Add listeners for other options if needed
        } else {
            // All questions displayed, perform actions accordingly
            // navigateToScoreFragment()
        }
    }

    private fun checkAnswer(selectedAnswer: Int) {
        val correctAnswer = currentQuiz.correctAnswer

        val title: String
        val message: String
        val isCorrect: Boolean

        if (selectedAnswer == correctAnswer) {
            title = "Правильно!"
            message = "Поздравляем! Это правильный ответ."
            isCorrect = true
        } else {
            title = "Неправильно!"
            message = "Это неправильный ответ."
            isCorrect = false
        }

        showAlertDialog(title, message, isCorrect)
    }

    private fun showAlertDialog(title: String, message: String, isCorrect: Boolean) {
        alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.setPositiveButton("Продолжить") { _, _ ->
            if (isCorrect) {
                currentQuestionIndex++
                loadQuestion()
            } else {
                loadQuestion()
            }
        }

        if (!isCorrect) {
            alertDialog.setNegativeButton("Начать заново") { _, _ ->
                currentQuestionIndex = 0
                loadQuestion()
            }
        }

        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}
