package com.sold.users.domain

import com.sold.users.data.UserRepository
import com.sold.users.data.model.User

class FetchUsersUsecase(private val userRepository: UserRepository){

    fun invoke():List<User> {
        return userRepository.getUser()
    }

}
