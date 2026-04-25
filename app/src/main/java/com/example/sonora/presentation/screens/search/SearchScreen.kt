package com.example.sonora.presentation.screens.search

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sonora.core.theme.RetroPinkBorder
import com.example.sonora.data.model.AlbumRepository
import com.example.sonora.data.model.TrackInfo
import com.example.sonora.presentation.screens.home.*

val CaveatFont = FontFamily.Cursive
val SonoraSerif = FontFamily.Serif
val QuicksandFont = FontFamily.SansSerif

@Composable
fun SearchScreen(
    currentTrack: TrackInfo?,
    onNavigateToPlayer: () -> Unit,
    onNavigateToPlaylist: (String) -> Unit,
    onTrackSelected: (TrackInfo) -> Unit,
    onNavigateToLibrary: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val BackgroundPink = Color(0xFFFFF0F3)
    val CaveatColor = Color(0xFFD298D7)

    var searchQuery by remember { mutableStateOf("") }

    val albumResults = remember(searchQuery) {
        if (searchQuery.isEmpty()) emptyList()
        else AlbumRepository.albums.filter {
            it.title.contains(searchQuery, ignoreCase = true) ||
                    it.artist.contains(searchQuery, ignoreCase = true)
        }
    }

    val trackResults = remember(searchQuery) {
        if (searchQuery.isEmpty()) emptyList()
        else AlbumRepository.albums.flatMap { album ->
            album.tracks.filter { it.title.contains(searchQuery, ignoreCase = true) }
                .map { track ->
                    TrackInfo(
                        title = track.title,
                        artist = album.artist,
                        coverRes = album.coverRes,
                        durationSeconds = track.durationSeconds,
                        audioResId = track.audioResId
                    )
                }
        }
    }

    Scaffold(
        bottomBar = {
            CommonBottomNavigation(
                onNavigateToHome = onNavigateToHome,
                onNavigateToSearch = {  },
                onNavigateToLibrary = onNavigateToLibrary,
                currentScreen = "search"
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding).background(BackgroundPink)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 120.dp)
            ) {
                item {
                    Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp)) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.clickable { onNavigateToHome() },
                            tint = RetroPinkBorder
                        )
                    }

                    Text(
                        text = "Search",
                        fontFamily = SonoraSerif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = RetroPinkBorder,
                        modifier = Modifier.padding(start = 24.dp, top = 16.dp)
                    )

                    Text(
                        text = "find your next favorite song",
                        fontFamily = CaveatFont,
                        fontSize = 20.sp,
                        color = CaveatColor,
                        modifier = Modifier.padding(start = 24.dp, bottom = 24.dp)
                    )
                }

                item {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        placeholder = { Text("What do you want to listen to?", fontFamily = QuicksandFont) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = RetroPinkBorder) },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                Icon(Icons.Default.Close, null, Modifier.clickable { searchQuery = "" }, tint = RetroPinkBorder)
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = RetroPinkBorder,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }

                if (searchQuery.isNotEmpty()) {

                    items(albumResults) { album ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onNavigateToPlaylist(album.id) }
                                .padding(horizontal = 24.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = album.coverRes),
                                contentDescription = null,
                                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(album.title, fontFamily = SonoraSerif, fontWeight = FontWeight.Bold, color = RetroPinkBorder)
                                Text("Album • ${album.artist}", fontFamily = QuicksandFont, color = Color.Gray, fontSize = 12.sp)
                            }
                        }
                    }

                    items(trackResults) { track ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onTrackSelected(track)
                                    onNavigateToPlayer()
                                }
                                .padding(horizontal = 24.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = track.coverRes),
                                contentDescription = null,
                                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(track.title, fontFamily = SonoraSerif, fontWeight = FontWeight.Bold, color = RetroPinkBorder)
                                Text("Song • ${track.artist}", fontFamily = QuicksandFont, color = Color.Gray, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            AnimatedVisibility(
                visible = currentTrack != null,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                currentTrack?.let { track ->
                    ProgrammaticMiniPlayer(track = track, onClick = onNavigateToPlayer)
                }
            }
        }
    }
}