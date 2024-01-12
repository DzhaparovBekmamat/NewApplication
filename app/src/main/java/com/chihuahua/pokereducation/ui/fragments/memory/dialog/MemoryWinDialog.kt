package com.chihuahua.pokereducation.ui.fragments.memory.dialog

import android.app.AlertDialog
import android.content.Context
import com.template.newapplication.R
import com.template.newapplication.databinding.DialogMemoryWinBinding

class MemoryWinDialog(context: Context, isEndGame: Boolean, callback: () -> Unit) :
    AlertDialog(context) {
    var dialog: AlertDialog? = null
    private var binding: DialogMemoryWinBinding? = null

    init {
        binding = DialogMemoryWinBinding.inflate(layoutInflater)
        if (isEndGame) binding?.memoryGameContinueBtn?.text =
            context.getString(R.string.see_results)
        binding?.memoryGameContinueBtn?.setOnClickListener {
            callback()
            dialog?.cancel()
        }
        dialog = Builder(context).setView(binding!!.root).create()
        dialog?.show()
    }
}