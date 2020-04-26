package com.sold.users.domain

import com.sold.users.data.UserRepository
import com.sold.users.data.model.User
import kotlinx.coroutines.flow.Flow

class FetchUsersUsecase(private val userRepository: UserRepository){

    fun invoke(): Flow<List<User>> {
        return userRepository.getUser()
    }

}
