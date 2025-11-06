package com.example.ratingchess

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ratingchess.ui.theme.RatingChessTheme
import kotlin.random.Random
import androidx.compose.foundation.background
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RatingChessTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    GuessNumberGame()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GuessNumberGame() {
    val context = LocalContext.current
    var inputValue by remember { mutableStateOf("") }
    var secretNumber by remember { mutableStateOf(Random.nextInt(1, 101)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "RatingChess",
                color = Color.White,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF6A1B9A))
                    .padding(vertical = 12.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))


        Text(
            text = "Попробуйте угадать число (1 - 100)",
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))


        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text("Введите число") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(30.dp))


        Button(
            onClick = {
                val number = inputValue.toIntOrNull()
                if (number == null) {
                    Toast.makeText(context, "Введите число от 1 до 100", Toast.LENGTH_SHORT).show()
                } else {
                    when {
                        number < secretNumber ->
                            Toast.makeText(context, "Больше!", Toast.LENGTH_SHORT).show()
                        number > secretNumber ->
                            Toast.makeText(context, "Меньше!", Toast.LENGTH_SHORT).show()
                        else -> {
                            Toast.makeText(context, "Поздравляю! Вы угадали: $secretNumber", Toast.LENGTH_LONG).show()
                            secretNumber = Random.nextInt(1, 101)
                            inputValue = ""
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(0.6f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A))
        ) {
            Text(text = "ВВЕСТИ ЗНАЧЕНИЕ", color = Color.White)
        }
    }
}