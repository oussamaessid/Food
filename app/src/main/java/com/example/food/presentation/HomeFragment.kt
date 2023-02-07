package com.example.food.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.easyfood.adapters.CategoriesRecyclerAdapter
import com.example.easyfood.adapters.MostPopularRecyclerAdapter
import com.example.easyfood.data.Meal
import com.example.easyfood.data.MealsByCategory
import com.example.easyfood.data.MealsByCategoryDto
import com.example.food.MealActivity
import com.example.food.R
import com.example.food.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var randomMeal : Meal
    private lateinit var homeMvvm : HomeViewModel
    private lateinit var popularItemsAdapter : MostPopularRecyclerAdapter
    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter

    companion object {
        const val MEAL_ID = "com.example.easyfood.fragments.idMeal"
        const val MEAL_STR = "com.example.easyfood.fragments.strMeal"
        const val MEAL_THUMB = "com.example.easyfood.fragments.strMealThumb"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]

        popularItemsAdapter = MostPopularRecyclerAdapter()
        categoriesAdapter = CategoriesRecyclerAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareCategoryRecyclerView()

        homeMvvm.getRandomMeal()
        observerRandomMeal()
        onRndomMealClick()

        homeMvvm.getPopularItems()
        observePopularItemsLiveData()
        onPopularItemClick()

        homeMvvm.getCategories()
        observeCategoriesLiveData()
        prepareCategoryGridView()

//        binding.randomMeal.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
//
//        }

    }

    private fun observeCategoriesLiveData() {
        homeMvvm.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer {categories ->
            categoriesAdapter.setCategoryList(categories)
        })
    }

    private fun prepareCategoryGridView() {
        binding.recViewMealsPopular.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick = { meal ->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_STR, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun prepareCategoryRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
    }

    private fun observePopularItemsLiveData() {
        homeMvvm.observePopularItemsLiveData().observe(viewLifecycleOwner,
            { mealList ->
                popularItemsAdapter.setMealList(mealsList = mealList as ArrayList<MealsByCategoryDto>)
            })
    }

    private fun onRndomMealClick() {
        binding.randomMeal.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
//            intent.putExtra(MEAL_ID, randomMeal.idMeal)
//            intent.putExtra(MEAL_STR, randomMeal.strMeal)
//            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)

        }
    }
    private fun observerRandomMeal(){
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner,
            { meal ->
                Glide.with(this@HomeFragment)
                    .load(meal!!.strMealThumb)
                    .into(binding.imgRandomMeal)
            })
    }
}