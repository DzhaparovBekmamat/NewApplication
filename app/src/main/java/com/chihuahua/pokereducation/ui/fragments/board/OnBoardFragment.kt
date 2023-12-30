package com.chihuahua.pokereducation.ui.fragments.board

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.template.newapplication.R
import com.template.newapplication.databinding.FragmentOnBoardBinding

class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding
    private val adapter = BoardAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
        val lastItemIndex = adapter.itemCount - 1
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == lastItemIndex) {
                    binding.nextButton.text = "Start"
                } else {
                    binding.nextButton.text = "Next"
                }
            }
        })

        TabLayoutMediator(binding.wormDotsIndicator, binding.viewPager) { tab, _ ->
            tab.setIcon(R.drawable.tab_indicator)
        }.attach()

        binding.nextButton.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem == lastItemIndex) {
                navigateToHistoryFragment()
            } else {
                binding.viewPager.setCurrentItem(currentItem + 1, true)
            }
        }
    }

    private fun navigateToHistoryFragment() {
        findNavController().navigate(R.id.historyFragment)
    }
}
