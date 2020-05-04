package com.sold.users.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sold.users.data.model.User
import com.sold.users.domain.FetchUsersUsecase


class UserViewModel constructor(private val fetchUsersUsecase: FetchUsersUsecase){
    private var users : MutableLiveData<List<User>> = MutableLiveData()

    fun fetchUsers(){

    }

    fun getUsers() : LiveData<List<User>>{
        return users
    }

    fun updateData() {
        val newList =  mutableListOf<User>()
        newList.add(User("Jyoti","Android Developer"))
        newList.add(User("Saurabh","Backend Developer"))
        users.value = newList
    }

}
