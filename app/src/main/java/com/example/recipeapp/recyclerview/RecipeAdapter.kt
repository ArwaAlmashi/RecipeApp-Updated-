package com.example.recipeapp.recyclerview

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.activities.MainActivity
import com.example.recipeapp.databinding.RowBinding
import com.example.recipeapp.model.Recipe

class RecipeAdapter (val recipes: ArrayList<Recipe> ): RecyclerView.Adapter<RecipeAdapter.RecipeHolder>() {


    class RecipeHolder(val binding: RowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        return RecipeHolder(
            RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        val recipe = recipes[position]
        holder.binding.apply {
            titleTv.text = recipe.title
            authorTv.text = recipe.author
            ingredientsTv.text = recipe.ingredients
            instructionsTv.text = recipe.instructions
        }
    }

    override fun getItemCount(): Int = recipes.size

    private fun setNewItem(newRecipesList: ArrayList<Recipe>) {
        val myDiffUtil = MyDiffUtil(com.example.recipeapp.activities.recipes, newRecipesList)
        val diffResult = DiffUtil.calculateDiff(myDiffUtil)
        com.example.recipeapp.activities.recipes = newRecipesList
        diffResult.dispatchUpdatesTo(this)
    }


}