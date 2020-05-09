package com.sold.users

import android.content.res.Resources
import android.graphics.Color.*
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.core.tag
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.imageFromResource
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
                    Surface(color = Color(parseColor("#030a3a"))) {
                        TaskLayout()
                    }


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
                val categories = mutableListOf<Pair<String, String>>()
                categories.add(Pair("Office", "#fe3966"))
                categories.add(Pair("Personal", "#057dff"))
                categories.add(Pair("TasK", "#fea623"))
                categories.add(Pair("Daily", "#9345cd"))
                categories.add(Pair("Office", "#fe3966"))
                categories.add(Pair("Personal", "#057dff"))
                categories.add(Pair("TasK", "#fea623"))
                categories.add(Pair("Daily", "#9345cd"))

                Spacer(modifier = Modifier.height(60.dp).plus(Modifier.fillMaxWidth().plus(Modifier.tag(spacer))))
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
    ConstraintLayout(constraintSet = constraintSet, modifier = Modifier.tag(tag).plus(Modifier.fillMaxWidth()).plus(
        Modifier.fillMaxHeight())) {
        Text(color = Color.White,text = "Hello, Jyoti", modifier = Modifier.tag(salutationTag))
        Text(color = Color.White,text = "Profile photo", modifier = Modifier.tag(profilePicTag))
    }
}

@Composable
fun CategorySection(tag: String, categories: List<Pair<String, String>>) {
    HorizontalScroller(modifier = Modifier.tag(tag)) {
        Row {
            categories.forEach {
                CategoryCard(it.first, it.second)
            }
        }

    }
}

@Composable
fun CategoryCard(title: String, color: String) {
    Card(modifier = Modifier.preferredHeight(120.dp).plus(Modifier.preferredWidth(100.dp).plus(Modifier.padding(5.dp))),
            color = Color(parseColor(color)),
            shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Text(color =  Color(parseColor("#fefdfe")), text = title)
            Text(color =  Color(parseColor("#fefdfe")), text = "5 task")
        }

    }
}

@Composable
fun TaskSection(tag: String) {
    Card(modifier = Modifier.preferredHeight(400.dp).plus(Modifier.fillMaxWidth()).plus(Modifier.tag(tag = tag)),
            shape = RoundedCornerShape(topLeft = 16.dp, topRight = 16.dp),
            color = Color(parseColor("#fefefe")) ) {
        Text(text = "title")
    }
}


