package com.sold.users.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sold.R
import com.sold.users.data.model.User
import com.sold.users.domain.FetchUsersUsecase


class UserViewModel constructor(private val fetchUsersUsecase: FetchUsersUsecase){
    private var users : MutableLiveData<HomeUiState> = MutableLiveData()

    init {
        updateData()
    }

    fun getUsers() : LiveData<HomeUiState>{
        return users
    }

    fun updateData() {
        users.value = HomeUiState(
            salutation = getSalutation("Jyoti Dubey"),
            image = R.drawable.ic_user,
            categories = getCategories(),
            tasks = getTask(),
            onAddTaskToCategory = {

            },
            onCategoryClicked = {

            },
            onAddNewTask = {

            }

        )
    }

    private fun getSalutation(name : String) = "Hello, $name"

    private fun getCategories(): List<Category>{
        val categories =  mutableListOf<Category>()
        categories.add(Category(
            name = "Office",
            taskCount = "5 Tasks",
            image = R.drawable.ic_office,
            backgroundColor = "#fe3966"
        ))
        categories.add(Category(
            name = "Personal",
            taskCount = "4 Tasks",
            image = R.drawable.ic_personal,
            backgroundColor = "#057dff"

        ))
        categories.add(Category(
            name = "Daily",
            taskCount = "3 Tasks",
            image = R.drawable.ic_daily,
            backgroundColor = "#fea623"

        ))
        categories.add(Category(
            name = "Learning",
            taskCount = "2 Tasks",
            image = R.drawable.ic_learning,
            backgroundColor = "#9345cd"

        ))
        return categories
    }

    private fun getTask(): List<Task>{
        val tasks = mutableListOf<Task>()
        tasks.add(Task(
            title = "Drawing",
            completedCount = "2 of 2 Completed",
            totalCount = "4",
            tint = "#03fc8c"

        ))
        tasks.add(Task(
            title = "Work manage",
            completedCount = "1 of 2 Completed",
            totalCount = "3",
            tint = "#fcad03"
        ))
        return tasks
    }


}
