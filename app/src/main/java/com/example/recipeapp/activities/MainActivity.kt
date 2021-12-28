package com.example.recipeapp.activities

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.resources.ApiClient
import com.example.recipeapp.model.Recipe
import com.example.recipeapp.recyclerview.RecipeAdapter
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.model.RecipeModel
import retrofit2.*

var recipes: ArrayList<Recipe> = arrayListOf()



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvRecipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        getRecipesFromAPI()

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddRecipeActivity::class.java)
            startActivity(intent)
        }


    }


    private fun setRecyclerview() {
        recyclerView = binding.recipeRecyclerview
        rvRecipeAdapter = RecipeAdapter(recipes)
        recyclerView.adapter = rvRecipeAdapter
    }

    private fun getRecipesFromAPI() {

        val recipesData = ApiClient().retrofitBuilder().getRecipes()
        recipesData.enqueue(object : Callback<RecipeModel> {
            override fun onResponse(call: Call<RecipeModel>, response: Response<RecipeModel>) {
                val recipesArray = response.body()
                if (recipesArray != null) {
                    for (recipe in recipesArray) {
                        recipes.add(
                            Recipe(
                                recipe.title,
                                recipe.author,
                                recipe.ingredients,
                                recipe.instructions
                            )
                        )
                    }
                    setRecyclerview()
                }

            }

            override fun onFailure(call: Call<RecipeModel>, t: Throwable) {
                Log.d("MainActivity", "Error: ${t.message}")
            }

        })
    }
}