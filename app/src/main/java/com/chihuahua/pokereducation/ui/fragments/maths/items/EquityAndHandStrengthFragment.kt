package com.chihuahua.pokereducation.ui.fragments.maths.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.template.newapplication.databinding.FragmentEquityAndHandStrengthBinding

class EquityAndHandStrengthFragment : Fragment() {

    private var _binding: FragmentEquityAndHandStrengthBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): EquityAndHandStrengthFragment {
            return EquityAndHandStrengthFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquityAndHandStrengthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
