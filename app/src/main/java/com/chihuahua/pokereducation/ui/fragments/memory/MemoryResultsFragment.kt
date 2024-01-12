package com.chihuahua.pokereducation.ui.fragments.memory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chihuahua.pokereducation.ui.fragments.memory.task.MemoryGamesTasks
import com.template.newapplication.R
import com.template.newapplication.databinding.FragmentMemoryResultsBinding

class MemoryResultsFragment : Fragment() {
    private var _binding: FragmentMemoryResultsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoryResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.memoryResultsTV.text = getString(
            R.string.result_desc,
            (requireActivity() as MemoryGameActivity).viewModel.ball.toString(),
            MemoryGamesTasks.tasksList.size.toString()
        )
        binding.memoryGameResultBtn.setOnClickListener {
            requireActivity().finish()
        }
    }
}