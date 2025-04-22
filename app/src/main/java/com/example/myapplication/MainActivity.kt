package com.example.myapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorApp()
        }
    }
}

@Composable
fun CalculatorApp() {
    var num1 by remember { mutableStateOf(TextFieldValue("")) }
    var num2 by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = num1,
            onValueChange = { num1 = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = num2,
            onValueChange = { num2 = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Kết quả: $result",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { result = calculate(num1.text, num2.text, "+") }) { Text("+") }
            Button(onClick = { result = calculate(num1.text, num2.text, "-") }) { Text("-") }
            Button(onClick = { result = calculate(num1.text, num2.text, "*") }) { Text("*") }
            Button(onClick = { result = calculate(num1.text, num2.text, "/") }) { Text("/") }
            Button(onClick = { num1 = TextFieldValue(""); num2 = TextFieldValue(""); result = "" }) {
                Text("Del")
            }
        }
    }
}

fun calculate(n1: String, n2: String, op: String): String {
    val num1 = n1.toDoubleOrNull() ?: return "Lỗi"
    val num2 = n2.toDoubleOrNull() ?: return "Lỗi"

    return when (op) {
        "+" -> (num1 + num2).toString()
        "-" -> (num1 - num2).toString()
        "*" -> (num1 * num2).toString()
        "/" -> if (num2 != 0.0) (num1 / num2).toString() else "Lỗi"
        else -> "Lỗi"
    }
}
