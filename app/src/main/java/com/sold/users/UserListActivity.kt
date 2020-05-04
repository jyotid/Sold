package com.sold.users

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.core.tag
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Card
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.unit.dp
import com.sold.users.presentation.UserViewModel
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
                    TaskLayout()

            }
        }
    }

    @Composable
    fun TaskLayout() {
        val profileInfoTag = "profileInfo"
        val categoryTag = "category"
        val taskTodayTag = "taskToday"
        val spacer = "spacer"

        val constraintSet = ConstraintSet {
            tag(taskTodayTag).apply {
                top constrainTo tag(profileInfoTag).bottom
            }
            tag(categoryTag).apply {
                top constrainTo tag(spacer).bottom
            }

            tag(taskTodayTag).apply {
                bottom constrainTo parent.bottom
            }
        }
        ConstraintLayout(constraintSet = constraintSet, modifier = Modifier.fillMaxHeight().plus(Modifier.fillMaxWidth())) {
            ProfileSection(profileInfoTag)
            val categories = mutableListOf<String>()
            categories.add("Office")
            categories.add("Personal")
            categories.add("Learning")
            categories.add("Music")
            categories.add("Office")
            categories.add("Personal")
            categories.add("Learning")
            categories.add("Music")
            Spacer(modifier = Modifier.height(30.dp).plus(Modifier.fillMaxWidth().plus(Modifier.tag(spacer))))
            CategorySection(categoryTag, categories)
            TaskSection(taskTodayTag)
        }
    }
}

@Composable
fun ProfileSection(tag: String) {
    val salutationTag = "salutation"
    val profilePicTag = "profilePic"
    val constraintSet = ConstraintSet {
        tag(profilePicTag).right constrainTo parent.right
        tag(profilePicTag).left constrainTo tag(salutationTag).right
        tag(salutationTag).left constrainTo parent.left
    }
    ConstraintLayout(constraintSet = constraintSet, modifier = Modifier.tag(tag)) {
        Text(text = "Hello, Jyoti", modifier = Modifier.tag(salutationTag))
        Text(text = "Profile photo", modifier = Modifier.tag(profilePicTag))
    }
}

@Composable
fun CategorySection(tag: String, categories: List<String>) {
    HorizontalScroller(modifier = Modifier.tag(tag)) {
        Row {
            categories.forEach {
                CategoryCard(it)
            }
        }

    }
}

@Composable
fun CategoryCard(title: String) {
    Card(modifier = Modifier.preferredHeight(80.dp).plus(Modifier.preferredWidth(80.dp))) {
        Row {
            Text(text = title)
        }
    }
}

@Composable
fun TaskSection(tag: String) {
    Card(modifier = Modifier.preferredHeight(300.dp).plus(Modifier.fillMaxWidth()).plus(Modifier.tag(tag = tag)), shape = RoundedCornerShape(topLeft = 5.dp), color = Color.Green) {
        Text(text = "title")
    }
}
//@Composable
//fun UserList(users : List<User>){

//    Box(modifier = Modifier.tag(taskTodayTag)){
//        val userState= userViewModel.getUsers().observeAsState(initial = listOf())
//        UserList(users = userState.value)
//    }
//}


