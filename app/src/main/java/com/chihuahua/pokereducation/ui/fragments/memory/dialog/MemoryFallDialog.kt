package com.chihuahua.pokereducation.ui.fragments.memory.dialog

import android.app.AlertDialog
import android.content.Context
import com.template.newapplication.R
import com.template.newapplication.databinding.DialogMemoryFallBinding

class MemoryFallDialog(
    context: Context, isEndGame: Boolean, isTimeOut: Boolean, callback: (isRestart: Boolean) -> Unit
) : AlertDialog(context) {
    var dialog: AlertDialog? = null
    private var binding: DialogMemoryFallBinding? = null

    init {
        binding = DialogMemoryFallBinding.inflate(layoutInflater)
        if (isEndGame) binding?.memoryGameContinueBtn?.text =
            context.getString(R.string.see_results)
        if (isTimeOut) binding?.memoryFallTitle?.text = context.getString(R.string.timeup)
        binding?.memoryGameContinueBtn?.setOnClickListener {
            callback(false)
            dialog?.cancel()
        }
        binding?.memoryGameAgainBtn?.setOnClickListener {
            callback(true)
            dialog?.cancel()
        }
        dialog = Builder(context).setView(binding!!.root).create()
        dialog?.show()
    }
}