package com.example.sonora.presentation.screens.library

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.sonora.data.model.AlbumInfo
import com.example.sonora.data.model.AlbumRepository
import com.example.sonora.data.model.TrackInfo
import com.example.sonora.presentation.screens.home.*

enum class LibraryTab { LIBRARY, BY_YOU, LIKED }


val QuicksandFont = FontFamily.SansSerif
val SonoraSerif = FontFamily.Serif

@Composable
fun LibraryScreen(
    currentTrack: TrackInfo?,
    onNavigateToPlayer: () -> Unit,
    onNavigateToPlaylist: (String) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToSearch: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(LibraryTab.LIBRARY) }
    val BackgroundPink = Color(0xFFFFF0F3)

    Scaffold(
        bottomBar = {
            CommonBottomNavigation(
                onNavigateToHome = onNavigateToHome,
                onNavigateToSearch = onNavigateToSearch,
                onNavigateToLibrary = {  },
                currentScreen = "library"
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding).background(BackgroundPink)) {
            Column(modifier = Modifier.fillMaxSize()) {

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TabButton("Library", selectedTab == LibraryTab.LIBRARY) { selectedTab = LibraryTab.LIBRARY }
                    TabButton("By you", selectedTab == LibraryTab.BY_YOU) { selectedTab = LibraryTab.BY_YOU }
                    TabButton("Liked", selectedTab == LibraryTab.LIKED) { selectedTab = LibraryTab.LIKED }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Crossfade(targetState = selectedTab, label = "LibraryTabs") { tab ->
                    when (tab) {
                        LibraryTab.LIBRARY -> TabContentGrid(
                            title = "Your Library",
                            albumIds = listOf("megan_stallion", "cabaret_soundtrack", "ajulliacosta_novo", "pink_fancy", "vibin_mix", "bedroom_mix", "cute_mix", "favs_mix"),
                            onNavigateToPlaylist = onNavigateToPlaylist
                        )
                        LibraryTab.BY_YOU -> TabContentGrid(
                            title = "By You",
                            albumIds = listOf("vibin_mix", "bedroom_mix", "cute_mix", "favs_mix"),
                            onNavigateToPlaylist = onNavigateToPlaylist
                        )
                        LibraryTab.LIKED -> TabContentGrid(
                            title = "Liked",
                            albumIds = listOf("megan_stallion", "cabaret_soundtrack", "ajulliacosta_novo", "pink_fancy"),
                            onNavigateToPlaylist = onNavigateToPlaylist
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = currentTrack != null,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
            ) {
                currentTrack?.let { track ->
                    ProgrammaticMiniPlayer(track = track, onClick = onNavigateToPlayer)
                }
            }
        }
    }
}

@Composable
private fun TabContentGrid(
    title: String,
    albumIds: List<String>,
    onNavigateToPlaylist: (String) -> Unit
) {
    val SonoraPink = Color(0xFFE2368C)
    val QuicksandPurple = Color(0xFF996688)

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = title,
            fontFamily = SonoraSerif,
            fontStyle = FontStyle.Italic,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = SonoraPink,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 120.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(albumIds) { albumId ->
                val album = AlbumRepository.getAlbum(albumId)
                if (album != null) {
                    AlbumGridItem(
                        album = album,
                        titleColor = SonoraPink,
                        textColor = QuicksandPurple,
                        onClick = { onNavigateToPlaylist(album.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun AlbumGridItem(
    album: AlbumInfo,
    titleColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = album.coverRes),
            contentDescription = album.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = album.title,
            fontFamily = SonoraSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = titleColor,
            maxLines = 1
        )

        Text(
            text = album.artist,
            fontFamily = QuicksandFont,
            fontSize = 14.sp,
            color = textColor,
            maxLines = 1
        )

        Text(
            text = "${album.tracks.size} tracks • Updated recently",
            fontFamily = QuicksandFont,
            fontSize = 12.sp,
            color = textColor.copy(alpha = 0.8f)
        )
    }
}