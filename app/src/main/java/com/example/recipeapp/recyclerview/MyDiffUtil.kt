package com.example.recipeapp.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.recipeapp.model.Recipe

class MyDiffUtil(private val oldList: ArrayList<Recipe>, private val newList: ArrayList<Recipe>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].title == newList[newItemPosition].title -> false
            oldList[oldItemPosition].author == newList[newItemPosition].author -> false
            oldList[oldItemPosition].ingredients == newList[newItemPosition].ingredients -> false
            oldList[oldItemPosition].instructions == newList[newItemPosition].instructions -> false
            else -> true
        }
    }
}