package com.example.first

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.first.ui.theme.FirstTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Replaced Greeting with your new screen and passed the padding
                    UnitConverterScreen(modifier = Modifier.padding(innerPadding))
                }
            }
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

fun convertKgToLbs(kg: Double): Double {
    return kg * 2.20462
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstTheme {
        Greeting("Android")
    }
}

@Composable
fun UnitConverterScreen(modifier: Modifier = Modifier) {
    // State variables to track input and result
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("Result will appear here") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Enter Kilograms") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                val kg = input.toDoubleOrNull() ?: 0.0
                val lbs = convertKgToLbs(kg)
                // Formatted the string to 2 decimal places for a cleaner UI
                result = "%.2f Pounds".format(lbs)
            },
            // Added some padding so the button isn't squished against the text field
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        ) {
            Text("Convert to pounds")
        }

        Text(text = result, style = MaterialTheme.typography.headlineMedium)
    }
}