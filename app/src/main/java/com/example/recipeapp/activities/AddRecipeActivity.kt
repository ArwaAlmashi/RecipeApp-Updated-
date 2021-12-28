package com.example.recipeapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.recipeapp.databinding.ActivityAddRecipeBinding
import com.example.recipeapp.model.Recipe
import com.example.recipeapp.resources.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeBinding

    private lateinit var newRecipe: Recipe
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.addRecipeButton.setOnClickListener {
            val userTitle = binding.titleEt.text
            val userAuthor = binding.authorEt.text
            val userIngredientsEt = binding.ingredientsEt.text
            val userInstructionsEt = binding.instructionsEt.text



            if (userTitle != null || userAuthor != null || userIngredientsEt != null || userInstructionsEt != null) {
                val newRecipe = Recipe(
                    userTitle.toString(),
                    userAuthor.toString(),
                    userIngredientsEt.toString(),
                    userInstructionsEt.toString()
                )
                postRecipeToAPI(newRecipe)
            } else {
                Toast.makeText(this, "Please fill all text fields", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun postRecipeToAPI(newRecipe: Recipe) {

        val recipesData = ApiClient().retrofitBuilder().addRecipe(newRecipe)
        recipesData.enqueue(object : Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                recipes.add(newRecipe)
                runOnUiThread {
                    val intent = Intent(this@AddRecipeActivity, MainActivity::class.java)
                    startActivity(intent)
                }

            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Log.d("MainActivity", "POST error: ${t.message}")
            }
        })

    }
}