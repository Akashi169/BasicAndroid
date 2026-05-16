package com.example.modifiertest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ModifierDemoScreen()
                }
            }
        }
    }
}

// --- CODE TỪ ĐOẠN 2 CỦA BẠN: Biến Modifier dùng chung ---
val standardCardModifier = Modifier
    .fillMaxWidth()
    .height(120.dp)
    .clip(RoundedCornerShape(12.dp))
    .background(Color.LightGray)
    .padding(16.dp)

@Composable
fun ModifierDemoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // --- CODE TỪ ĐOẠN 1 CỦA BẠN: Order Matters ---
        Text("1.Thứ tự Modifier", style = MaterialTheme.typography.titleLarge)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .background(Color.Red)
            ) {
                Text("Khối A", modifier = Modifier.align(Alignment.Center), color = Color.White)
            }

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
                    .padding(16.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
                    Text("Khối B", modifier = Modifier.align(Alignment.Center), color = Color.Black)
                }
            }
        }

        // --- CODE TỪ ĐOẠN 2 CỦA BẠN: Reusable Modifier ---
        Text("2. Tái sử dụng Modifier", style = MaterialTheme.typography.titleLarge)

        ProductCard()

        ProfileCard()
    }
}

@Composable
fun ProductCard() {
    Row(modifier = standardCardModifier) {
        Text("Nội dung của Product Card")
    }
}

@Composable
fun ProfileCard() {
    // Kế thừa standardCardModifier và thêm hiệu ứng click
    Row(modifier = standardCardModifier.clickable { println("Đã click vào Profile") }) {
        Text("Nội dung của Profile Card (Click sẽ có hiệu ứng mờ nhẹ)")
    }
}