package com.sold.users

import android.graphics.Color.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.State
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.core.tag
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Card
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.sold.users.presentation.Category
import com.sold.users.presentation.HomeUiState
import com.sold.users.presentation.Task
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
                    Surface(color = Color(parseColor("#030a3a"))) {
                        val uiState = userViewModel.getUsers().observeAsState()
                        TaskLayout(uiState as State<HomeUiState>)
                    }


            }
        }
    }

    @Composable
    fun TaskLayout(state: State<HomeUiState>) {
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
                ProfileSection(profileInfoTag, state.value.salutation, state.value.image)
                Spacer(modifier = Modifier.height(60.dp).plus(Modifier.fillMaxWidth().plus(Modifier.tag(spacer))))
                CategorySection(categoryTag, state.value.categories)
                TaskSection(taskTodayTag, state.value.tasks)

        }
    }
}

@Composable
fun ProfileSection(tag: String, salutation: String, image: Int) {
    val salutationTag = "salutation"
    val profilePicTag = "profilePic"
    val constraintSet = ConstraintSet {
        tag(profilePicTag).right constrainTo parent.right
        tag(profilePicTag).left constrainTo tag(salutationTag).right
        tag(salutationTag).left constrainTo parent.left
    }
    ConstraintLayout(
            constraintSet = constraintSet,
            modifier = Modifier.tag(tag)
                    .plus(Modifier.fillMaxWidth())
                    .plus(Modifier.fillMaxHeight()
                    )
    ) {
        Text(color = Color.White,text = salutation, modifier = Modifier.tag(salutationTag))
        Image(asset = vectorResource(id = image),  modifier = Modifier.tag(profilePicTag))
    }
}

@Composable
fun CategorySection(tag: String, categories: List<Category>) {
    HorizontalScroller(modifier = Modifier.tag(tag)) {
        Row {
            categories.forEach {
                CategoryCard(it.name, it.backgroundColor, it.taskCount, it.image)
            }
        }

    }
}

@Composable
fun CategoryCard(
    title: String,
    color: String,
    taskCount: String,
    image: Int
) {
    Card(modifier = Modifier.preferredHeight(140.dp).plus(Modifier.preferredWidth(120.dp).plus(Modifier.padding(5.dp))),
            color = Color(parseColor(color)),
            shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Image(asset = vectorResource(id = image))
            Text(color =  Color(parseColor("#fefdfe")), text = title)
            Text(color =  Color(parseColor("#fefdfe")), text = taskCount)

        }

    }
}

@Composable
fun TaskSection(tag: String, tasks: List<Task>) {
    Card(modifier = Modifier.preferredHeight(700.dp).plus(Modifier.fillMaxWidth()).plus(Modifier.tag(tag = tag)),
            shape = RoundedCornerShape(topLeft = 16.dp, topRight = 16.dp),
            color = Color(parseColor("#fefefe")) ) {
        Column {
            Text(text = "Today's Task")
            TaskList(tasks = tasks)
        }

    }
}
@Composable
fun TaskList(tasks: List<Task>) {
    VerticalScroller {
        Column {
            tasks.forEach {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row {
                        Icon(asset = vectorResource(id = it.icon),tint = Color(parseColor(it.tint)) )
                        Text(text = it.title, modifier = Modifier.padding(start = 5.dp))
                    }
                   
                    Text(color = Color(parseColor("#cfcfd8")),text = it.completedCount)

                }
            }

        }
    }
}






