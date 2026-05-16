package com.example.composebasicui // Updated to match the file's directory

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
// using Material3 for theming
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
// Color and vector painter imports not needed here
import androidx.compose.ui.platform.LocalContext
// TextAlign/TextOverflow not used
import androidx.compose.ui.tooling.preview.Preview
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
                    // GỌI HÀM GIAO DIỆN Ở ĐÂY
                    MainAppScreen()
                }
            }
        }
    }
}

@Composable
fun MainAppScreen() {
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // AVATAR: load vector drawable resource to illustrate Image usage
        Image(
            painter = painterResource(id = R.drawable.ic_account_circle),
            contentDescription = "Avatar mặc định",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )

        // TEXT
        Text(
            text = "Thành phần UI Cơ Bản",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        // TEXTFIELD
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Nhập tên") },
            modifier = Modifier.fillMaxWidth()
        )

        // BUTTONS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = { inputText = "" },
                modifier = Modifier.weight(1f)
            ) {
                Text("Làm mới")
            }

            Button(
                onClick = {
                    if (inputText.isNotBlank()) {
                        Toast.makeText(context, "Chào $inputText!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.weight(1f)
            )
            {
                Text("Xác nhận")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        MainAppScreen()
    }
}