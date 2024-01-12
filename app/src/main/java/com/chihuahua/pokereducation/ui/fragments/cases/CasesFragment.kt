package com.chihuahua.pokereducation.ui.fragments.cases

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
import com.template.newapplication.databinding.FragmentCasesBinding

@Suppress("UNUSED_EXPRESSION")
class CasesFragment : Fragment(), CasesAdapter.OnItemClickListener {
    private var _binding: FragmentCasesBinding? = null
    private val binding get() = _binding!!
    private lateinit var casesAdapter: CasesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCasesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setRecyclerViewScrollListener()
    }

    private fun setupRecyclerView() {
        val casesList = generateCasesList()
        casesAdapter = CasesAdapter(casesList, this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = casesAdapter
        }
    }

    private fun generateCasesList(): List<CasesModel> {
        return listOf(
            CasesModel(
                title = "Case Study 1", description = "The Bluff That Worked"
            ), CasesModel(
                title = "Case Study 2", description = "The Hero Call"
            ), CasesModel(
                title = "Case Study 3", description = "The Well-Timed Fold"
            ), CasesModel(
                title = "Case Study 4", description = "Turning a Loser into a Winner"
            ), CasesModel(
                title = "Case Study 5", description = "The Power of Position"
            ), CasesModel(
                title = "Case Study 6", description = "The Strategic Check-Raise"
            ), CasesModel(
                title = "Case Study 7", description = "The Art of Pot Control"
            ), CasesModel(
                title = "Case Study 8", description = "The Game-Changing River Decision"
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

    override fun onItemClick(position: Int) {
        val clickedItem = casesAdapter.getItemAtPosition(position)
        when (clickedItem.title) {
            "Case Study 1" -> findNavController().navigate(R.id.caseStudy1Fragment)
            "Case Study 2" -> findNavController().navigate(R.id.caseStudy2Fragment)
            "Case Study 3" -> findNavController().navigate(R.id.caseStudy3Fragment)
            "Case Study 4" -> findNavController().navigate(R.id.caseStudy4Fragment)
            "Case Study 5" -> findNavController().navigate(R.id.caseStudy5Fragment)
            "Case Study 7" -> findNavController().navigate(R.id.caseStudy7Fragment)
            "Case Study 8" -> findNavController().navigate(R.id.caseStudy8Fragment)
            "Case Study 6" -> findNavController().navigate(R.id.caseStudy6Fragment)
            else -> null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}