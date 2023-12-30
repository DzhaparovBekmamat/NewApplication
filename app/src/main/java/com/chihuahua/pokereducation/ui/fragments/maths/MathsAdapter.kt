package com.chihuahua.pokereducation.ui.fragments.maths

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.template.newapplication.databinding.NoteMathsBinding

class MathsAdapter(
    private val dataList: List<MathsModel>,
) : RecyclerView.Adapter<MathsAdapter.MathsViewHolder>() {

    inner class MathsViewHolder(private val binding: NoteMathsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: MathsModel) {
            binding.textView1.text = model.name
            binding.textView2.text = model.description
            model.imageView?.let { binding.imageView1.setImageResource(it) }
            Log.d("MathsAdapter", "Data bound for position: $adapterPosition")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MathsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteMathsBinding.inflate(inflater, parent, false)
        return MathsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MathsViewHolder, position: Int) {
        val model = dataList[position]
        holder.onBind(model)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
