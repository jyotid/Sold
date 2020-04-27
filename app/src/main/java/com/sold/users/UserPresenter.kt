package com.sold.users

import com.sold.users.domain.FetchUsersUsecase


interface UserPresenter {
    fun fetchUser()
}
class UserPresenterImpl(private val fetchUsersUsecase: FetchUsersUsecase) : UserPresenter {

    override fun fetchUser() {

    }

}
