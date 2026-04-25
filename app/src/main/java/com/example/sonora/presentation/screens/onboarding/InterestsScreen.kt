package com.example.sonora.presentation.screens.onboarding

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sonora.R
import com.example.sonora.core.theme.RetroPinkBorder
import com.example.sonora.core.theme.SonoraTypography
import kotlinx.coroutines.delay

data class GenreAsset(val id: String, val drawableRes: Int)

@Composable
fun InterestsScreen(onContinue: () -> Unit) {
    val BackgroundWarmCream = Color(0xFFFFF9EE)
    val PixelFont = FontFamily.Monospace

    val selectedGenres = remember { mutableStateListOf<String>() }

    var startTyping by remember { mutableStateOf(false) }
    val alphaContent = remember { Animatable(0f) }

    val genres = listOf(
        GenreAsset("Jazz", R.drawable.asset_genre_jazz),
        GenreAsset("Pop", R.drawable.asset_genre_pop),
        GenreAsset("City Pop", R.drawable.asset_genre_citypop),
        GenreAsset("Bossa", R.drawable.asset_genre_bossa),
        GenreAsset("Rock", R.drawable.asset_genre_rock),
        GenreAsset("Indie", R.drawable.asset_genre_indie),
        GenreAsset("MPB", R.drawable.asset_genre_mpb),
        GenreAsset("Rap", R.drawable.asset_genre_rap)
    )

    LaunchedEffect(key1 = true) {
        startTyping = true
        delay(1800)
        alphaContent.animateTo(1f, tween(800))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWarmCream)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        if (startTyping) {
            TypewriterText(
                text = "STEP 1 / 1",
                textStyle = TextStyle(
                    fontFamily = PixelFont,
                    color = RetroPinkBorder,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))


            TypewriterText(
                text = "Whats your vibe?",
                textStyle = SonoraTypography.headlineLarge.copy(
                    fontSize = 36.sp,
                    color = RetroPinkBorder,
                    fontFamily = FontFamily.Serif
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            TypewriterText(
                text = "pick at least 3 to start",
                textStyle = SonoraTypography.bodyMedium.copy(
                    fontSize = 16.sp,
                    color = RetroPinkBorder.copy(alpha = 0.7f),
                    fontStyle = FontStyle.Italic
                )
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .alpha(alphaContent.value),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(genres) { genre ->
                val isSelected = selectedGenres.contains(genre.id)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.1f)
                        .clickable {
                            if (isSelected) selectedGenres.remove(genre.id)
                            else selectedGenres.add(genre.id)
                        }
                ) {
                    Image(
                        painter = painterResource(id = genre.drawableRes),
                        contentDescription = genre.id,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )

                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = (-12).dp, y = 12.dp)
                                .size(28.dp)
                                .background(Color(0xFFE94B8A), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Selected",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        val canContinue = selectedGenres.size >= 3

        Image(
            painter = painterResource(id = R.drawable.btn_continue),
            contentDescription = "Continue",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 32.dp)
                .alpha(if (canContinue) alphaContent.value else alphaContent.value * 0.4f)
                .clickable(enabled = canContinue && alphaContent.value == 1f) {
                    onContinue()
                }
        )
    }
}

@Composable
private fun TypewriterText(
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