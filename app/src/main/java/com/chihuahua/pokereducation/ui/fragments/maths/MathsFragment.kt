package com.chihuahua.pokereducation.ui.fragments.maths

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.template.newapplication.R
import com.template.newapplication.databinding.FragmentMathsBinding

class MathsFragment : Fragment(), MathsAdapter.OnItemClickListener {
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
        setRecyclerViewScrollListener()
    }

    private fun setupRecyclerView() {
        val mathsList = generateMathsList()
        mathsAdapter = MathsAdapter(mathsList, this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mathsAdapter
        }
    }

    private fun generateMathsList(): List<MathsModel> {
        return listOf(
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
    }

    private fun setRecyclerViewScrollListener() {
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val recyclerView = binding.recyclerView

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && bottomNavigationView.isShown) {
                    bottomNavigationView.visibility = View.GONE
                } else if (dy < 0 && !bottomNavigationView.isShown) {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        val clickedItem = mathsAdapter.getItemAtPosition(position)
        val itemName = clickedItem.name
        when (itemName) {
            "Probability and Odds" -> findNavController().navigate(R.id.probabilityAndOddsFragment)
            "Pot Odds. Expected Value" -> findNavController().navigate(R.id.potOddsFragment)
            "Implied Odds" -> findNavController().navigate(R.id.impliedOddsFragment)
            "The Rule of 2 and 4" -> findNavController().navigate(R.id.ruleOf2And4Fragment)
            "Bluffing with Maths" -> findNavController().navigate(R.id.bluffingWithMathsFragment)
            "Equity & Hand Strength" -> findNavController().navigate(R.id.equityAndHandStrengthFragment)
            "Expected Value in Odds" -> findNavController().navigate(R.id.expectedValueInOddsFragment)
            "Applying Psychological Maths" -> findNavController().navigate(R.id.applyingPsychologicalMathsFragment)
            else -> null
        }
    }
}