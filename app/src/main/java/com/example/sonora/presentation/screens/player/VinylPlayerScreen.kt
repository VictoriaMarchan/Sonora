package com.example.sonora.presentation.screens.player

import android.media.MediaPlayer
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

val QuicksandFont = FontFamily.SansSerif
val SonoraSerif = FontFamily.Serif
val PixelFont = FontFamily.Monospace
val CaveatFont = FontFamily.Cursive
val RetroPinkBorder = Color(0xFFE2368C)
val BackgroundPink = Color(0xFFFFF0F3)

@Composable
fun VinylPlayerScreen(
    trackName: String,
    artistName: String,
    coverRes: Int,
    audioResId: Int,
    onMinimize: () -> Unit
) {
    val context = LocalContext.current

    val mediaPlayer = remember(audioResId) {
        MediaPlayer.create(context, audioResId).apply {
            isLooping = true
        }
    }

    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableStateOf(0f) }

    val duration = remember(mediaPlayer) {
        try { mediaPlayer.duration.toFloat() } catch (e: Exception) { 0f }
    }

    LaunchedEffect(isPlaying, audioResId) {
        while (isPlaying && mediaPlayer.isPlaying) {
            currentPosition = mediaPlayer.currentPosition.toFloat()
            delay(1000)
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "VinylRotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "RotationAngle"
    )

    DisposableEffect(audioResId) {
        onDispose {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.release()
        }
    }

    fun formatTime(ms: Float): String {
        if (ms.isNaN() || ms < 0) return "0:00"
        val totalSeconds = (ms / 1000).toInt()
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%d:%02d", minutes, seconds)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundPink)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, CircleShape)
                    .clickable { onMinimize() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Minimize", tint = RetroPinkBorder)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "NOW PLAYING",
                    fontFamily = PixelFont,
                    fontSize = 14.sp,
                    color = RetroPinkBorder,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Text(
                    text = trackName,
                    fontFamily = CaveatFont,
                    fontSize = 18.sp,
                    color = Color(0xFFB388A4)
                )
            }

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.QueueMusic, contentDescription = "Queue", tint = RetroPinkBorder)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(340.dp)
                .shadow(16.dp, CircleShape, spotColor = Color.Black.copy(alpha = 0.5f))
                .rotate(if (isPlaying) rotation else 0f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Brush.radialGradient(listOf(Color(0xFF2B2B2B), Color(0xFF111111))))
            )

            Image(
                painter = painterResource(id = coverRes),
                contentDescription = null,
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            // Furo central
            Box(modifier = Modifier.size(12.dp).background(BackgroundPink, CircleShape))
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = trackName,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = RetroPinkBorder,
                    fontFamily = SonoraSerif,
                    maxLines = 1
                )
                Text(
                    text = artistName,
                    fontSize = 16.sp,
                    color = Color(0xFF996688),
                    fontFamily = QuicksandFont,
                    maxLines = 1
                )
            }
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = RetroPinkBorder,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column {
            Slider(
                value = currentPosition,
                onValueChange = {
                    currentPosition = it
                    mediaPlayer.seekTo(it.toInt())
                },
                valueRange = 0f..if (duration > 0f) duration else 1f,
                colors = SliderDefaults.colors(
                    thumbColor = Color.Transparent,
                    activeTrackColor = RetroPinkBorder,
                    inactiveTrackColor = Color.White
                ),
                modifier = Modifier.height(20.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = formatTime(currentPosition),
                    fontFamily = PixelFont,
                    color = RetroPinkBorder,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Text(
                    text = formatTime(duration),
                    fontFamily = PixelFont,
                    color = RetroPinkBorder,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Shuffle, null, tint = RetroPinkBorder, modifier = Modifier.size(28.dp))
            Icon(Icons.Default.SkipPrevious, null, modifier = Modifier.size(48.dp), tint = RetroPinkBorder)


            Surface(
                modifier = Modifier
                    .size(80.dp)
                    .clickable {
                        isPlaying = !isPlaying
                        if (isPlaying) mediaPlayer.start() else mediaPlayer.pause()
                    },
                shape = CircleShape,
                color = RetroPinkBorder,
                shadowElevation = 8.dp
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(
                        Brush.radialGradient(listOf(Color(0xFFFF7EB3), RetroPinkBorder))
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Icon(Icons.Default.SkipNext, null, modifier = Modifier.size(48.dp), tint = RetroPinkBorder)
            Icon(Icons.Default.Repeat, null, tint = RetroPinkBorder, modifier = Modifier.size(28.dp))
        }
    }
}