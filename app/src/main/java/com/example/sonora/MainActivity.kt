package com.example.sonora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.sonora.core.theme.SonoraTheme
import com.example.sonora.core.theme.SoftPink

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SonoraTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SoftPink
                ) {
                    SonoraNavigation()
                }
            }
        }
    }
}