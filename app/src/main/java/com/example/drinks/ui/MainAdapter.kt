package com.example.drinks.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drinks.R
import com.example.drinks.base.BaseViewHolder
import com.example.drinks.data.model.Drink
import com.example.drinks.databinding.DrinkRowBinding

class MainAdapter(private val context: Context, private val drinkList: List<Drink>,
        private val itemClickListener:ItemClickListener
                  ) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface ItemClickListener{
        fun onClickDrink(drink: Drink, position: Int,itemView: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.drink_row, parent, false)
        )
    }

    override fun getItemCount(): Int = drinkList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(drinkList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {

        private val binding = DrinkRowBinding.bind(itemView)

        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(binding.imageView)
            binding.tvTitle.text = item.name
            binding.tvDescription.text = item.description



            binding.imageView.setOnClickListener {
                itemClickListener.onClickDrink(item, position, itemView)
            }
            Log.d("CLICK", "CLICKING")
        }

    }

}