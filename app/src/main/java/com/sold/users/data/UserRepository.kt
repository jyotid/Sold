package com.sold.users.data

import com.sold.users.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRepository {
    fun getUser() : Flow<List<User>>
}
class UserRepo : UserRepository {

    override fun getUser(): Flow<List<User>> {
        return flow {
            val users = mutableListOf<User>()
            users.add(User("Jyoti"))
            users.add(User("Amanjeet"))
            users.add(User("Saurabh"))
            users.add(User("Jitin"))
            users.add(User("Bhavya"))
           emit(users)
        }
    }

}
