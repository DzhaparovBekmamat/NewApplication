package com.chihuahua.pokereducation.ui.fragments.maths

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.newapplication.R
import com.template.newapplication.databinding.FragmentMathsBinding

class MathsFragment : Fragment() {
    private var _binding: FragmentMathsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mathsAdapter: MathsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMathsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val mathsList = listOf(
            MathsModel(
                imageView = R.drawable.d1,
                name = "Probability and Odds",
                description = "Poker, a card game that combines skill, strategy, and "
            ), MathsModel(
                imageView = R.drawable.d2,
                name = "Pot Odds. Expected Value",
                description = "Calculate your pot odds to find out expected value"
            ), MathsModel(
                imageView = R.drawable.d3,
                name = "Implied Odds",
                description = "Find out how to factor in your future odds"
            ), MathsModel(
                imageView = R.drawable.d4,
                name = "The Rule of 2 and 4",
                description = "Find out what id a 2 and 4 rule and how \nto imply it in a game"
            ), MathsModel(
                imageView = R.drawable.d5,
                name = "Bluffing with Maths",
                description = "Learn bluffing from practical example"
            ), MathsModel(
                imageView = R.drawable.d6,
                name = "Equity & Hand Strength",
                description = "Read how to evaluate your position"
            ), MathsModel(
                imageView = R.drawable.d7,
                name = "Expected Value in Odds",
                description = "Observe optimal odds strategies"
            ), MathsModel(
                imageView = R.drawable.d8,
                name = "Applying Psychological Maths",
                description = "Reverse tells and deciphering opponents"
            )
        )
        mathsAdapter = MathsAdapter(mathsList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = mathsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
