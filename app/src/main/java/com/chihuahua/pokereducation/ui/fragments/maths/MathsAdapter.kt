package com.chihuahua.pokereducation.ui.fragments.maths

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.template.newapplication.databinding.NoteMathsBinding

class MathsAdapter(
    private val mathsList: List<MathsModel>, private val listener: OnItemClickListener
) : RecyclerView.Adapter<MathsAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(private val binding: NoteMathsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mathsItem: MathsModel) {
            binding.apply {
                mathsItem.imageView?.let { imageView1.setImageResource(it) }
                textView1.text = mathsItem.name
                textView2.text = mathsItem.description

                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoteMathsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = mathsList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = mathsList.size

    fun getItemAtPosition(position: Int): MathsModel {
        return mathsList[position]
    }
}