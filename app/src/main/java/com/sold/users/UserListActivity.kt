package com.sold.users

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Layout
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.ConstrainedBox
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import org.intellij.lang.annotations.JdkConstants
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

// Figure out why the object survives even after closing the scope
class UserListActivity  : AppCompatActivity() {

    lateinit var userPresenter : UserPresenter
    private lateinit var session : Scope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {
                Greeting()
                
            }
        }
        session = getKoin().getOrCreateScope("user_scope_ID", named("user_scope"))
        userPresenter = session.get<UserPresenter>()
        userPresenter.fetchUser()

        session.close()

    }

    override fun onDestroy() {
        userPresenter.fetchUser()
        super.onDestroy()
    }
}



@Composable
fun Greeting() {
    Text(text = "Hello")
}

@Preview
@Composable
fun DefaultPreview() {
    Text("Android")

}
