package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.data.MealsByCategory
import com.example.easyfood.data.MealsByCategoryDto
import com.example.food.databinding.MostPopularCardBinding

class MostPopularRecyclerAdapter : RecyclerView.Adapter<MostPopularRecyclerAdapter.MostPopularMealViewHolder>(){
    private var mealsList = ArrayList<MealsByCategoryDto>()
    lateinit var onItemClick : ((MealsByCategoryDto)->Unit)
    fun setMealList(mealsList: ArrayList<MealsByCategoryDto>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }


    class MostPopularMealViewHolder(val binding: MostPopularCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularMealViewHolder {
        return MostPopularMealViewHolder(MostPopularCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MostPopularMealViewHolder, position: Int) {

            Glide.with(holder.itemView)
                .load(mealsList[position].strMealThumb)
                .into(holder.binding.imgPopularMeal)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}



