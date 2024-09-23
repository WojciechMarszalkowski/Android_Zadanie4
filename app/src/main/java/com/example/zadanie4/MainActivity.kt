package com.example.zadanie4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.DeltaCalculatorTheme.ui.theme.DeltaCalculatorTheme
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeltaCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DeltaCalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun DeltaCalculatorScreen() {
    var a by remember { mutableStateOf("") }
    var b by remember { mutableStateOf("") }
    var c by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    fun calculateDelta() {
        val aVal = a.toDoubleOrNull()
        val bVal = b.toDoubleOrNull()
        val cVal = c.toDoubleOrNull()

        if (aVal != null && bVal != null && cVal != null) {
            val delta = bVal.pow(2) - 4 * aVal * cVal
            if (delta > 0) {
                val x1 = (-bVal - sqrt(delta)) / (2 * aVal)
                val x2 = (-bVal + sqrt(delta)) / (2 * aVal)
                result = "Delta: $delta\nPierwiastki: x1 = $x1, x2 = $x2"
            } else if (delta == 0.0) {
                val x = -bVal / (2 * aVal)
                result = "Delta: $delta\nJeden pierwiastek: x = $x"
            } else {
                result = "Delta: $delta\nBrak pierwiastków rzeczywistych"
            }
        } else {
            result = "Wprowadź poprawne wartości dla a, b i c."
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Kalkulator Delty", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = a,
            onValueChange = { a = it },
            label = { Text("Wartość a") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = b,
            onValueChange = { b = it },
            label = { Text("Wartość b") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = c,
            onValueChange = { c = it },
            label = { Text("Wartość c") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { calculateDelta() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Oblicz deltę")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = result, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeltaCalculatorTheme {
        DeltaCalculatorScreen()
    }
}
