package com.chihuahua.pokereducation.ui.fragments.memory

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import androidx.activity.OnBackPressedCallback
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.chihuahua.pokereducation.ui.fragments.memory.card.Card
import com.chihuahua.pokereducation.ui.fragments.memory.card.Cards
import com.chihuahua.pokereducation.ui.fragments.memory.dialog.MemoryFallDialog
import com.chihuahua.pokereducation.ui.fragments.memory.dialog.MemoryWinDialog
import com.chihuahua.pokereducation.ui.fragments.memory.task.MemoryGamesTasks
import com.template.newapplication.R
import com.template.newapplication.databinding.FragmentMemoryGameBinding
import kotlin.random.Random

class MemoryGameFragment : Fragment() {
    private var _binding: FragmentMemoryGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MemoryGameViewModel
    private var timer: CountDownTimer? = null
    private var levelCompleted = true
    private var memorizedCardIndexes = arrayOf(-1, -1)
    private var selectedCardIndexes = arrayOf(-1, -1)
    private val cards = ArrayList<Card>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoryGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
        viewModel = (requireActivity() as MemoryGameActivity).viewModel
        binding.titleMemoryGameTV.text = requireContext().getString(R.string.level, "1")
        binding.memoryTimerTV.text = requireContext().getString(R.string.memory_timer, "15")
        setNewTasks()
        binding.memoryGameBtn.setOnClickListener {
            if (viewModel.isTaskChecked) {
                timer?.cancel()
                openCard(false)
            } else {
                timer?.cancel()
                hideCards()
            }
        }
        binding.titleMemoryGameTV.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(15 * 1000, 1000) {
            override fun onTick(milliseconds: Long) {
                binding.memoryTimerTV.text = getString(
                    R.string.memory_timer, String.format("%02d", (milliseconds / 1000))
                )
            }

            override fun onFinish() {
                if (viewModel.isTaskChecked) openCard(true)
                else hideCards()
            }
        }.start()
    }

    private fun setNewTasks() {
        if (!levelCompleted) {
            return
        }
        levelCompleted = false
        viewModel.activeTaskIndex++
        if (viewModel.activeTaskIndex >= MemoryGamesTasks.tasksList.size) {
            toResults()
            return
        }
        viewModel.activeTask = MemoryGamesTasks.tasksList[viewModel.activeTaskIndex]
        binding.memoryGameBtn.visibility = View.VISIBLE
        binding.memoryGameBtn.text = getString(R.string.start)
        binding.titleMemoryGameTV.text =
            getString(R.string.level, (viewModel.activeTaskIndex + 1).toString())
        binding.levelTaskMemoryGameTV.text = getString(
            R.string.memory_task_base,
            getString(MemoryGamesTasks.tasksList[viewModel.activeTaskIndex].titleRes)
        )
        viewModel.isTaskChecked = false
        memorizedCardIndexes[0] = -1
        memorizedCardIndexes[1] = -1
        selectedCardIndexes[0] = -1
        selectedCardIndexes[1] = -1
        cards.clear()
        val usedIndex = ArrayList<Int>()
        for (i in 0 until viewModel.activeTask.row) {
            val row = binding.memoryCardsTL[i] as TableRow
            row.visibility = View.VISIBLE
            for (j in 0 until viewModel.activeTask.col) {
                val img = row[j] as ImageView
                img.visibility = View.VISIBLE
                var index = -1
                while (index < 0 || usedIndex.contains(index)) {
                    index = Random.nextInt(0, 52)
                }
                usedIndex.add(index)
                cards.add(Cards.cardsList[index])
                img.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources, Cards.cardsList[index].resDraw, null
                    )
                )
                img.foregroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
                img.setOnClickListener { selectCard(it as ImageView) }
            }
        }
        memorizedCardIndexes[0] = Random.nextInt(viewModel.activeTask.row)
        memorizedCardIndexes[1] = Random.nextInt(viewModel.activeTask.col)
        ((binding.memoryCardsTL[memorizedCardIndexes[0]] as TableRow)[memorizedCardIndexes[1]] as ImageView).setImageDrawable(
            ResourcesCompat.getDrawable(
                resources, viewModel.activeTask.resDraw, null
            )
        )
        val index =
            memorizedCardIndexes[0] * viewModel.activeTask.row + memorizedCardIndexes[1] + memorizedCardIndexes[0]
        Cards.cardsList.forEach {
            if (it.resDraw == MemoryGamesTasks.tasksList[viewModel.activeTaskIndex].resDraw) cards[index] =
                it
        }
        startTimer()
    }

    private fun hideCards() {
        binding.memoryGameBtn.visibility = View.INVISIBLE
        binding.memoryGameBtn.text = getString(R.string.check)
        viewModel.isTaskChecked = true
        selectedCardIndexes[0] = -1
        selectedCardIndexes[1] = -1
        for (i in 0 until viewModel.activeTask.row) {
            val row = binding.memoryCardsTL[i] as TableRow
            for (j in 0 until viewModel.activeTask.col) {
                val img = row[j] as ImageView
                img.setImageDrawable(
                    ResourcesCompat.getDrawable(resources, R.drawable.cards_back, null)
                )
                img.foregroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
            }
        }
        startTimer()
    }

    private fun selectCard(img: ImageView) {
        if (selectedCardIndexes[0] != -1 && selectedCardIndexes[1] != -1) {
            val oldImg =
                ((binding.memoryCardsTL[selectedCardIndexes[0]] as TableRow)[selectedCardIndexes[1]] as ImageView)
            oldImg.foregroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
            if (viewModel.isTaskChecked) oldImg.setImageDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.cards_back, null)
            )
        }
        val tableRow = img.parent as TableRow
        selectedCardIndexes[0] = (tableRow.parent as TableLayout).indexOfChild(tableRow)
        selectedCardIndexes[1] = tableRow.indexOfChild(img)
        img.foregroundTintList = null
        binding.memoryGameBtn.visibility = View.VISIBLE
    }

    private fun checkCard() {
        if (selectedCardIndexes[0] != -1 && selectedCardIndexes[1] != -1) {
            if (selectedCardIndexes[0] == memorizedCardIndexes[0] && selectedCardIndexes[1] == memorizedCardIndexes[1]) {
                viewModel.ball++
                winDialog()
            } else fallDialog(false)
        }
    }

    private fun openCard(isTimeOut: Boolean) {
        val row: Int
        val col: Int
        if (selectedCardIndexes[0] != -1 && selectedCardIndexes[1] != -1) {
            row = selectedCardIndexes[0]
            col = selectedCardIndexes[1]
        } else {
            row = memorizedCardIndexes[0]
            col = memorizedCardIndexes[1]
        }
        val index = row * viewModel.activeTask.row + col + row
        val img = ((binding.memoryCardsTL[row] as TableRow)[col] as ImageView)
        img.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources, cards[index].resDraw, null
            )
        )
        binding.root.postDelayed({
            if (isTimeOut) fallDialog(isTimeOut)
            else checkCard()
            levelCompleted = true
        }, 1 * 1000)
    }

    private fun winDialog() {
        MemoryWinDialog(
            requireContext(), viewModel.activeTaskIndex >= MemoryGamesTasks.tasksList.size
        ) {
            setNewTasks()
        }
    }

    private fun fallDialog(isTimeOut: Boolean) {
        MemoryFallDialog(
            requireContext(),
            viewModel.activeTaskIndex >= MemoryGamesTasks.tasksList.size,
            isTimeOut
        ) { isRestart ->
            if (isRestart) viewModel.activeTaskIndex--
            setNewTasks()
        }
    }

    private fun toResults() {
        safeNavigate(
            findNavController(), MemoryGameFragmentDirections.gameToResult()
        )
    }

    private fun safeNavigate(navController: NavController, direction: NavDirections) {
        val action = navController.currentDestination?.getAction(direction.actionId)
        action.run {
            navController.navigate(direction)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
