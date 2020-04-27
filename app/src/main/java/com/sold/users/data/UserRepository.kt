package com.sold.users.data

import com.sold.users.data.model.User


interface UserRepository {
    fun getUser() : List<User>
}
class UserRepo : UserRepository {

    override fun getUser(): List<User> {
        return mutableListOf<User>()
    }

}
