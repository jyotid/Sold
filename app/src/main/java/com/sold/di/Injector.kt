package com.sold.di


import com.sold.users.data.UserRepo
import com.sold.users.data.UserRepository
import com.sold.users.domain.FetchUsersUsecase
import com.sold.users.presentation.UserViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> {
        UserRepo()
    }
}

val userModule = module {
    scope(named("user_scope")){
        scoped<FetchUsersUsecase> {
            FetchUsersUsecase(get())
        }

        scoped {
            UserViewModel(get())
        }
    }
}
