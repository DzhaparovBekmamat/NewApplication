package com.chihuahua.pokereducation.ui.fragments.memory

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.template.newapplication.R
import com.template.newapplication.databinding.ActivityMemoryGameBinding

class MemoryGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoryGameBinding
    val viewModel: MemoryGameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_game)
    }
}