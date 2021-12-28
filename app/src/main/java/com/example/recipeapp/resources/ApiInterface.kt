package com.example.recipeapp.resources

import com.example.recipeapp.model.Recipe
import com.example.recipeapp.model.RecipeModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("recipes/")
    fun getRecipes(): retrofit2.Call<RecipeModel>

    @POST("recipes/")
    fun addRecipe(@Body recipe: Recipe): Call<Recipe>
}