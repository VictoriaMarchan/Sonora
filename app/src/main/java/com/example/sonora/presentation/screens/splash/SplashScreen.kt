package com.example.sonora.presentation.screens.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sonora.R
import com.example.sonora.core.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToAuth: () -> Unit) {

    var startTyping by remember { mutableStateOf(false) }
    val alphaAssets = remember { Animatable(0f) }


    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFFEE5EA), Color(0xFFF0DCF9))
    )

    LaunchedEffect(key1 = true) {
        startTyping = true


        delay(2500)


        alphaAssets.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1200, easing = EaseInOut)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 48.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            if (startTyping) {
                TypewriterText(
                    text = "⋆ ♡ ⋆ since 2026",
                    textStyle = SonoraTypography.bodyMedium.copy(
                        color = Color(0xFFFF6699),
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                TypewriterText(
                    text = "Sonora",
                    textStyle = SonoraTypography.headlineLarge.copy(
                        fontSize = 64.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFFFF6699)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                TypewriterText(
                    text = "music for the soft soul",
                    textStyle = SonoraTypography.bodyMedium.copy(
                        color = TextBerry.copy(alpha = 0.6f),
                        fontStyle = FontStyle.Italic
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1.2f))

            Image(
                painter = painterResource(id = R.drawable.welcome_window),
                contentDescription = "Start Playlist Window",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .alpha(alphaAssets.value)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.btn_tap_begin),
                contentDescription = "Tap to begin",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .alpha(alphaAssets.value)
                    .clickable(enabled = alphaAssets.value == 1f) {
                        onNavigateToAuth()
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TypewriterText(
    text: String,
    modifier: Modifier = Modifier,
    delayPerChar: Long = 50L,
    textStyle: TextStyle
) {
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        displayedText = ""
        for (i in text.indices) {
            displayedText += text[i]
            delay(delayPerChar)
        }
    }

    Text(text = displayedText, style = textStyle, modifier = modifier)
}