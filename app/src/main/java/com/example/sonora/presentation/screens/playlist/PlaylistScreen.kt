package com.example.sonora.presentation.screens.playlist

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sonora.core.theme.RetroPinkBorder
import com.example.sonora.data.model.AlbumRepository
import com.example.sonora.data.model.TrackInfo
import com.example.sonora.presentation.screens.home.ProgrammaticMiniPlayer


val QuicksandFont = FontFamily.SansSerif
val PixelFont = FontFamily.Monospace
val SonoraSerif = FontFamily.Serif

@Composable
fun PlaylistScreen(
    albumId: String,
    onBack: () -> Unit,
    onTrackClick: (TrackInfo) -> Unit,
    currentTrack: TrackInfo?,
    onNavigateToPlayer: () -> Unit
) {
    val album = AlbumRepository.getAlbum(albumId)
    val BackgroundPink = Color(0xFFFFF0F3)

    if (album == null) {
        Box(modifier = Modifier.fillMaxSize().background(BackgroundPink), contentAlignment = Alignment.Center) {
            Text("Álbum não encontrado :(", color = RetroPinkBorder)
        }
        return
    }

    Box(modifier = Modifier.fillMaxSize().background(BackgroundPink)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 120.dp)
        ) {

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(24.dp).padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable { onBack() },
                        tint = RetroPinkBorder
                    )
                    Row(
                        modifier = Modifier.background(Color.White, RoundedCornerShape(16.dp)).padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(12.dp).background(RetroPinkBorder, CircleShape))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(album.artist, color = Color.Gray, fontFamily = QuicksandFont, fontSize = 12.sp)
                    }
                }
            }

            item {
                Row(modifier = Modifier.padding(horizontal = 24.dp), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(album.coverRes),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp).shadow(8.dp, RoundedCornerShape(16.dp)).clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("PLAYLIST", fontFamily = PixelFont, color = RetroPinkBorder, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                        Text(album.title, fontFamily = SonoraSerif, color = RetroPinkBorder, fontSize = 22.sp, fontWeight = FontWeight.Bold, maxLines = 2)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(album.artist, fontFamily = QuicksandFont, color = Color.Gray, fontSize = 14.sp)
                            Text(" ♡ ${album.tracks.size} songs", fontFamily = QuicksandFont, color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)) {
                    Text("#", color = RetroPinkBorder.copy(alpha=0.6f), fontFamily = QuicksandFont, modifier = Modifier.width(30.dp))
                    Text("TITLE", color = RetroPinkBorder.copy(alpha=0.6f), fontFamily = QuicksandFont, modifier = Modifier.weight(1f))
                    Text("DATE", color = RetroPinkBorder.copy(alpha=0.6f), fontFamily = QuicksandFont, modifier = Modifier.width(60.dp))
                    Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = RetroPinkBorder.copy(alpha=0.6f), modifier = Modifier.size(16.dp))
                }
                HorizontalDivider(color = RetroPinkBorder.copy(alpha=0.2f), modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp))
            }

            itemsIndexed(album.tracks) { index, track ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val playingTrack = TrackInfo(
                                title = track.title,
                                artist = album.artist,
                                coverRes = album.coverRes,
                                durationSeconds = track.durationSeconds,
                                audioResId = track.audioResId
                            )
                            onTrackClick(playingTrack)
                        }
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("${index + 1}", color = RetroPinkBorder.copy(alpha=0.6f), fontFamily = QuicksandFont, modifier = Modifier.width(30.dp))

                    Image(
                        painter = painterResource(album.coverRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(40.dp).clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(track.title, fontFamily = SonoraSerif, color = RetroPinkBorder, fontSize = 16.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                        Text(album.artist, fontFamily = QuicksandFont, color = Color.Gray, fontSize = 12.sp, maxLines = 1)
                    }

                    Text(track.releaseDate, color = Color.Gray, fontFamily = QuicksandFont, fontSize = 11.sp, modifier = Modifier.width(60.dp))

                    val m = track.durationSeconds / 60
                    val s = (track.durationSeconds % 60).toString().padStart(2, '0')
                    Text("$m:$s", color = Color.Gray, fontFamily = QuicksandFont, fontSize = 12.sp)
                }
            }
        }

        AnimatedVisibility(
            visible = currentTrack != null,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
        ) {
            currentTrack?.let { track ->
                ProgrammaticMiniPlayer(track = track, onClick = onNavigateToPlayer)
            }
        }
    }
}