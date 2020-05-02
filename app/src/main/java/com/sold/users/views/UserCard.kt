package com.sold.users.views

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.tag
import androidx.ui.foundation.Text
import androidx.ui.layout.ConstraintLayout
import androidx.ui.layout.ConstraintSet
import com.sold.users.data.model.User

@Composable
fun UserCard(user: User){
    val tagName  = "Name"
    val tagOccupation = "Occupation"
    val constrainSet = ConstraintSet{
        tag(tagName).apply{
            top constrainTo parent.top
            left constrainTo parent.left
        }
        tag(tagOccupation).apply{
            top constrainTo tag(tagName).bottom
            left constrainTo parent.left
        }
    }
    ConstraintLayout(constraintSet = constrainSet) {
        Text(text = user.name, modifier = Modifier.tag(tagName))
        Text(text = user.designation, modifier = Modifier.tag(tagOccupation))

    }
}
