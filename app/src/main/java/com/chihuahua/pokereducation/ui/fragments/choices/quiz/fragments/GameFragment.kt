package com.chihuahua.pokereducation.ui.fragments.choices.quiz.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chihuahua.pokereducation.ui.fragments.choices.quiz.model.Constants
import com.chihuahua.pokereducation.ui.fragments.choices.quiz.model.QuizModel
import com.template.newapplication.R
import com.template.newapplication.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var currentQuiz: QuizModel
    private var currentQuestionIndex = 0
    private var correctAnswers = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadQuestion()
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setOnClickListener {
            navigateBackToChoiceFragment()
        }
    }

    private fun loadQuestion() {
        val questionList = Constants.getQuestion()
        if (currentQuestionIndex < questionList.size) {
            currentQuiz = questionList[currentQuestionIndex]
            setupQuizUI()
        } else {
            navigateToScoreFragment()
        }
    }

    private fun setupQuizUI() {
        binding.apply {
            tvLevel.text = currentQuiz.level
            tvDescription.text = currentQuiz.description
            tvImage.setImageResource(currentQuiz.image)
            tvQuestion.text = currentQuiz.question
            tvOptionOne.text = currentQuiz.optionOne
            tvOptionTwo.text = currentQuiz.optionTwo
            tvOptionOne.setOnClickListener { checkAnswer(1) }
            tvOptionTwo.setOnClickListener { checkAnswer(2) }
        }
    }

    private fun checkAnswer(selectedAnswer: Int) {
        val correctAnswer = currentQuiz.correctAnswer
        val isCorrect = selectedAnswer == correctAnswer

        if (isCorrect) {
            correctAnswers++
        }

        val title = if (isCorrect) "Правильно!" else "Неправильно!"
        val message =
            if (isCorrect) "Поздравляем! Это правильный ответ." else "Это неправильный ответ."
        showAlertDialog(title, message, isCorrect)
    }

    private fun showAlertDialog(title: String, message: String, isCorrect: Boolean) {
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(
            if (isCorrect) R.layout.custom_dialog_win else R.layout.custom_dialog_lose, null
        )
        alertDialog.setView(dialogView)
        val description1 = dialogView.findViewById<TextView>(R.id.descriptionWin)
        val description2 = dialogView.findViewById<TextView>(R.id.descriptionLose)
        if (description1 != null && description2 != null) {
            when (currentQuestionIndex) {
                0 -> {
                    description1.text = "Opponent folds, and you win a large pot!"
                    description2.text = "Opponent reveals a higher pair and takes \u2028the pot"
                }

                1 -> {
                    description1.text =
                        "Opponent folds, and you win the pot \u2028with a high card!"
                    description2.text = "Opponent calls and reveals a stronger hand, taking the pot"
                }

                2 -> {
                    description1.text =
                        "You hit the flush on the river, and \u2028your opponent pays off your odd!"
                    description2.text =
                        "The flush draw doesn’t hit, and your opponent's odd takes the pot"
                }

                3 -> {
                    description1.text =
                        "Opponent folds, and you win the pot, successfully bluffing your opponent!"
                    description2.text =
                        "Opponent calls, revealing a higher straight, and takes the pot"
                }

                4 -> {
                    description1.text =
                        "Opponent bluffs and goes all-in; you call and win a significant pot with your strong hand"
                    description2.text =
                        "Opponent shows a strong hand after your fold, revealing you made the right decision"
                }

                5 -> {
                    description1.text = "Your Aces hold up, and you \nwin a massive pot"
                    description2.text =
                        "Opponent hits a straight on the river, beating your pair of Aces"
                }

                6 -> {
                    description1.text =
                        "Opponent folds, and you win the pot. \n" + "Your straight draw didn’t hit, but your aggression paid off!"
                    description2.text =
                        "Opponent calls, and your straight draw doesn’t complete, leading to your opponent winning the pot"
                }

                7 -> {
                    description1.text =
                        "Your low pair holds up against a bluff, \nand you win the pot"
                    description2.text =
                        "Opponent reveals a higher pair, taking \nthe pot and confirming your suspicion \nof a strong hand"
                }

                else -> null
            }
        }

        val continueButton = dialogView.findViewById<Button>(
            if (isCorrect) R.id.btn else R.id.button3
        )
        continueButton.setOnClickListener {
            alertDialog.dismiss()
            if (isCorrect) {
                currentQuestionIndex++
            } else {
                currentQuestionIndex = 0
            }
            loadQuestion()
        }

        if (!isCorrect) {
            val tryAgainButton = dialogView.findViewById<Button>(R.id.button1)
            tryAgainButton.setOnClickListener {
                alertDialog.dismiss()
                currentQuestionIndex++
                loadQuestion()
            }
        }

        alertDialog.setCancelable(false)
        alertDialog.show()
    }



    private fun navigateToScoreFragment() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(correctAnswers)
        findNavController().navigate(action)
    }

    private fun navigateBackToChoiceFragment() {
        findNavController().navigateUp()
    }
}
