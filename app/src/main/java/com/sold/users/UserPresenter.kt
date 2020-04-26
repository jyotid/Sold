package com.sold.users

import com.sold.users.domain.FetchUsersUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

interface UserPresenter {
    fun fetchUser()
}
class UserPresenterImpl(private val fetchUsersUsecase: FetchUsersUsecase) : UserPresenter {

    override fun fetchUser() {
        CoroutineScope(Dispatchers.Main).launch {
            fetchUsersUsecase.invoke()
                .flowOn(Dispatchers.IO)
                .collect { list ->
                    print(list)
                }
        }
    }

}
