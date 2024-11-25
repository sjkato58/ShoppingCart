package com.mtfuji.sakura.shoppingcart

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.mtfuji.sakura.shoppingcart.ui.theme.ShoppingCartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingCartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig
            .fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val jsonString = remoteConfig.getString("product_list")
                    Log.i("seiji", "jsonString: $jsonString")
                } else {
                    Log.e("seiji", "FirebaseRemoteConfig - failed - ${task.exception}")
                }
            }
            .addOnSuccessListener {
                Log.d("seiji", "FirebaseRemoteConfig - Success!")
            }
            .addOnFailureListener {
                Log.e("seiji", "FirebaseRemoteConfig - Failed - $it")
            }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingCartTheme {
        Greeting("Android")
    }
}