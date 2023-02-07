package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food.data.model.CategoryDto
import com.example.food.databinding.CategoryCardBinding


class CategoriesRecyclerAdapter : RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoryViewHolder>() {
    private var categoryList = ArrayList<CategoryDto>()
   // private lateinit var onItemClick: OnItemCategoryClicked
   // private lateinit var onLongCategoryClick:OnLongCategoryClick

    fun setCategoryList(categoryList: List<CategoryDto>){
        this.categoryList = categoryList as ArrayList<CategoryDto>
        notifyDataSetChanged()
    }


    class CategoryViewHolder(val binding: CategoryCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            Glide.with(holder.itemView)
                .load(categoryList[position].strCategoryThumb)
                .into(holder.binding.imgCategory)
             holder.binding.tvCategoryName.text = categoryList[position].strCategory

//        holder.itemView.setOnClickListener {
//            onItemClick.onClickListener(categoryList[position])
//        }


    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


}