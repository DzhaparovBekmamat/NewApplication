package com.chihuahua.pokereducation.ui.fragments.memory

import androidx.lifecycle.ViewModel
import com.chihuahua.pokereducation.ui.fragments.memory.task.MemoryGamesTask
import com.chihuahua.pokereducation.ui.fragments.memory.task.MemoryGamesTasks

class MemoryGameViewModel : ViewModel() {
    var activeTaskIndex = -1
    var activeTask: MemoryGamesTask = MemoryGamesTasks.tasksList[0]
    var isTaskChecked: Boolean = false
    var ball = 0
}