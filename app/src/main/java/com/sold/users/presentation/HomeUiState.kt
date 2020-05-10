package com.sold.users.presentation

import androidx.annotation.DrawableRes
import com.sold.R

data class HomeUiState(
    val salutation : String,
    @DrawableRes val image : Int = R.drawable.ic_user,
    val categories : List<Category>,
    val tasks : List<Task>,
    val onCategoryClicked : ()-> Unit,
    val onAddTaskToCategory: ()->Unit,
    val onAddNewTask: ()->Unit
)

data class Category(
    val name :String,
    val taskCount : String,
    val backgroundColor : String,
    @DrawableRes val image: Int
)

data class Task(
    @DrawableRes val icon : Int = R.drawable.ic_done,
    val title : String,
    val completedCount : String,
    val totalCount : String,
    val tint : String
)
