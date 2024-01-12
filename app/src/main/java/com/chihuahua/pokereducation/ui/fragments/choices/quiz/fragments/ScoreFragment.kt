package com.chihuahua.pokereducation.ui.fragments.choices.quiz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.template.newapplication.R
import com.template.newapplication.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    private lateinit var binding: FragmentScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupScoreText()
        setupButtonClick()
    }

    private fun setupScoreText() {
        val args = ScoreFragmentArgs.fromBundle(requireArguments())
        val correctAnswers = args.correctAnswers
        val totalQuestions = 8
        val scoreText = "You've passed $correctAnswers/$totalQuestions levels"
        binding.tv1.text = "Results"
        binding.tv2.text = scoreText
    }

    private fun setupButtonClick() {
        binding.btn.setOnClickListener {
            findNavController().navigate(R.id.choicesFragment)
        }
    }
}
