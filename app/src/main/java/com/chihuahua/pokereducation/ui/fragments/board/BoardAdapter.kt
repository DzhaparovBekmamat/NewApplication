package com.chihuahua.pokereducation.ui.fragments.board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.template.newapplication.R
import com.template.newapplication.databinding.ItemBoardBinding

class BoardAdapter() : RecyclerView.Adapter<BoardAdapter.OnBoardingViewHolder>() {
    private val list = listOf(
        BoardModel(
            "The Rich Tapestry of Poker: \nA Journey through Its Storied History", R.drawable.a1
        ), BoardModel(
            "Mastering Poker Math: \n" + "Unveiling the Tricks Behind the Cards", R.drawable.a2
        ), BoardModel(
            "Observe cases & WInning paths", R.drawable.a3
        ), BoardModel(
            "Test your poker skills in eight intense \nsituations. Your goal: make the right calls \nand outsmart your opponents. ",
            R.drawable.a4
        ), BoardModel(
            "Welcome to Poker Card Match, where \nmemory meets the cards! Let the \nmatching begin!",
            R.drawable.a5
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemBoardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
    inner class OnBoardingViewHolder(private val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(boardModel: BoardModel) {
            with(binding) {
                descriptionItemBoard.text = boardModel.description
                lottieAnimation.setImageResource(boardModel.image)
            }
        }
    }
}
