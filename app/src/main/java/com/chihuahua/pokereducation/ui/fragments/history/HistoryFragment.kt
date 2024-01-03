package com.chihuahua.pokereducation.ui.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.template.newapplication.R

class HistoryFragment : Fragment() {
    private var isScrollingUp = false
    private var scrollPosition = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val scrollView = view.findViewById<ScrollView>(R.id.scrollView)
        scrollView.viewTreeObserver.addOnScrollChangedListener {
            val currentPosition = scrollView.scrollY
            if (currentPosition > scrollPosition) {
                if (!isScrollingUp) {
                    isScrollingUp = true
                    bottomNavigationView.visibility = View.VISIBLE
                }
            } else {
                if (isScrollingUp) {
                    isScrollingUp = false
                    bottomNavigationView.visibility = View.INVISIBLE
                }
            }
            scrollPosition = currentPosition
        }
        return view
    }
}
