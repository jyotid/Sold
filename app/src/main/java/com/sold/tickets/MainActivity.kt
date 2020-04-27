package com.sold.tickets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.core.tag
import androidx.ui.foundation.Text
import androidx.ui.layout.ConstraintLayout
import androidx.ui.layout.ConstraintSet
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                UserCard()
            }
        }
    }
}

@Composable
fun UserCard(){
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
        Text(text = "Jyoti Dubey", modifier = Modifier.tag(tagName))
        Text(text = "Android Engineer", modifier = Modifier.tag(tagOccupation))

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Greeting("Android")
    }
}
