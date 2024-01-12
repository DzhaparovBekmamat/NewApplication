package com.chihuahua.pokereducation.ui.fragments.memory.task

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.template.newapplication.R

object MemoryGamesTasks {
    @SuppressLint("SupportAnnotationUsage")
    @StringRes
    @DrawableRes
    val tasksList = listOf(
        MemoryGamesTask(
            R.string.memory_task_level1, 14, 3, 4, R.drawable.suit_clubs_value_14
        ),
        MemoryGamesTask(
            R.string.memory_task_level2, 2, 3, 4, R.drawable.suit_diamonds_value_2
        ),
        MemoryGamesTask(
            R.string.memory_task_level3, 13, 3, 4, R.drawable.suit_hearts_value_13
        ),
        MemoryGamesTask(
            R.string.memory_task_level4, 4, 4, 5, R.drawable.suit_hearts_value_4
        ),
        MemoryGamesTask(
            R.string.memory_task_level5, 8, 4, 5, R.drawable.suit_diamonds_value_8
        ),
        MemoryGamesTask(
            R.string.memory_task_level6, 12, 4, 5, R.drawable.suit_spades_value_12
        ),
        MemoryGamesTask(
            R.string.memory_task_level7, 13, 4, 6, R.drawable.suit_diamonds_value_13
        ),
        MemoryGamesTask(
            R.string.memory_task_level8, 2, 4, 6, R.drawable.suit_clubs_value_2
        ),
    )
}