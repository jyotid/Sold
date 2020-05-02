package com.sold.users

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.State
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import com.sold.users.data.model.User
import com.sold.users.presentation.UserViewModel
import com.sold.users.views.UserCard
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class UserListActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    private lateinit var session: Scope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        session = getKoin().getOrCreateScope("user_scope_ID", named("user_scope"))
        userViewModel = session.get()

        setContent {
            MaterialTheme {
                val userState= userViewModel.getUsers().observeAsState(initial = listOf())
                Column {
                    Button(onClick = { userViewModel.updateData() }) {
                        Text(text = "Click me")
                    }
                    UserList(users = userState.value)
                }
            }
        }
    }
}

@Composable
fun UserList(users : List<User>){
    AdapterList(data = users) {
        UserCard(user = it)
    }
}


