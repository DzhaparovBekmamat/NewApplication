package com.chihuahua.pokereducation.ui.fragments.cases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.template.newapplication.databinding.NoteCasesBinding

class CasesAdapter(
    private val casesList: List<CasesModel>, private val listener: OnItemClickListener
) : RecyclerView.Adapter<CasesAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(private val binding: NoteCasesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(casesItem: CasesModel) {
            binding.apply {
                textView.text = casesItem.title
                textView2.text = casesItem.description
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
        val binding = NoteCasesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = casesList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = casesList.size

    fun getItemAtPosition(position: Int): CasesModel {
        return casesList[position]
    }
}
