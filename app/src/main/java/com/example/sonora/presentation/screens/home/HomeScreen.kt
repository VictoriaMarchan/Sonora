package com.example.sonora.presentation.screens.home

import androidx.compose.animation.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.example.sonora.core.theme.*
import com.example.sonora.data.model.AlbumRepository
import com.example.sonora.data.model.TrackInfo
import kotlinx.coroutines.delay

enum class HomeTab { ALL, FOR_YOU, DAILY_MOOD }

val QuicksandFont = FontFamily.SansSerif
val PixelFont = FontFamily.Monospace

@Composable
fun HomeScreen(
    currentTrack: TrackInfo?,
    selectedTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit,
    onTrackSelected: (TrackInfo) -> Unit,
    onNavigateToPlayer: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToLibrary: () -> Unit,
    onNavigateToPlaylist: (String) -> Unit
) {
    val BackgroundPink = Color(0xFFFFF0F3)

    Scaffold(
        bottomBar = {
            CommonBottomNavigation(
                onNavigateToHome = {  },
                onNavigateToSearch = onNavigateToSearch,
                onNavigateToLibrary = onNavigateToLibrary,
                currentScreen = "home"
            ) }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

            Column(modifier = Modifier.fillMaxSize().background(BackgroundPink)) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.btn_profile), contentDescription = "Profile", modifier = Modifier.size(48.dp).clickable { onNavigateToProfile() })
                    Spacer(modifier = Modifier.width(16.dp))

                    Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TabButton("All", selectedTab == HomeTab.ALL) { onTabSelected(HomeTab.ALL) }
                        TabButton("For you", selectedTab == HomeTab.FOR_YOU) { onTabSelected(HomeTab.FOR_YOU) }
                        TabButton("Daily Mood", selectedTab == HomeTab.DAILY_MOOD) { onTabSelected(HomeTab.DAILY_MOOD) }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Crossfade(targetState = selectedTab, label = "Tab Transition") { tab ->
                    when (tab) {
                        HomeTab.ALL -> TabAllContent { track -> onTrackSelected(track); onNavigateToPlayer() }
                        HomeTab.FOR_YOU -> TabForYouContent(onTrackClick = { track -> onTrackSelected(track); onNavigateToPlayer() }, onNavigateToPlaylist = onNavigateToPlaylist)
                        HomeTab.DAILY_MOOD -> TabDailyMoodContent(onTrackClick = { track -> onTrackSelected(track); onNavigateToPlayer() }, onNavigateToPlaylist = onNavigateToPlaylist)
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
private fun TabAllContent(onTrackClick: (TrackInfo) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp), contentPadding = PaddingValues(bottom = 100.dp)) {
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    TrackCard(TrackInfo("Windy Summer", "Anri", R.drawable.img_track_windy, 200, R.raw.windy_audio), onTrackClick)
                    TrackCard(TrackInfo("Tear You Apart", "She wants revenge", R.drawable.img_track_tear, 205, R.raw.tear_audio), onTrackClick)
                    TrackCard(TrackInfo("Tarantula", "Gorillaz", R.drawable.img_track_tarantula, 195, R.raw.tarantula_audio), onTrackClick)
                }
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    TrackCard(TrackInfo("Sara", "fleetwood mac", R.drawable.img_track_sara, 210, R.raw.sara_audio), onTrackClick)
                    TrackCard(TrackInfo("Gardenia", "Malice Mizer", R.drawable.img_track_gardenia, 230, R.raw.gardenia_audio), onTrackClick)
                    TrackCard(TrackInfo("If I Should Lose You", "Nina Simone", R.drawable.img_track_ifishould, 185, R.raw.ifishoyldt_audio), onTrackClick)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        item {
            Text("Newest Released", fontFamily = FontFamily.Serif, fontStyle = FontStyle.Italic, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = RetroPinkBorder)
            Spacer(modifier = Modifier.height(16.dp))
            BannerCard(onPlayClick = { onTrackClick(TrackInfo("Tonight", "PinkPantheress", R.drawable.img_banner_pinkpantheress, 180, R.raw.tonight_audio)) })
            Spacer(modifier = Modifier.height(32.dp))
        }
        item {
            Text("Recently played", fontFamily = FontFamily.Serif, fontStyle = FontStyle.Italic, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = RetroPinkBorder)
            Spacer(modifier = Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                TrackCard(TrackInfo("ATTITUDE", "IVE", R.drawable.img_recent_attitude, 190, R.raw.attitude_audio), onTrackClick)
                TrackCard(TrackInfo("LIPSTICK", "Orange Caramel", R.drawable.img_recent_lipstick, 210, R.raw.lipstick_audio), onTrackClick)
                TrackCard(TrackInfo("Stateside", "PinkPantheress", R.drawable.img_recent_stateside, 180, R.raw.stateside_audio), onTrackClick)
                TrackCard(TrackInfo("Catch Catch", "YENA", R.drawable.img_recent_catchcatch, 195, R.raw.catch_audio), onTrackClick)
            }
        }
    }
}


@Composable
private fun TabForYouContent(onTrackClick: (TrackInfo) -> Unit, onNavigateToPlaylist: (String) -> Unit) {
    val favoriteArtists = listOf(
        Pair("PinkPantheress", R.drawable.img_artist_pinkpantheress),
        Pair("Alaíde Costa", R.drawable.img_artist_alaide),
        Pair("Aventura", R.drawable.img_artist_aventura),
        Pair("YENA", R.drawable.img_artist_yena),
        Pair("Caetano Veloso", R.drawable.img_artist_caetano)
    )

    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 100.dp)) {
        item {
            Text("Your radio's favorite artists", fontFamily = FontFamily.Serif, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = RetroPinkBorder, modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(contentPadding = PaddingValues(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(favoriteArtists) { artist ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(110.dp)) {
                        Image(painter = painterResource(id = artist.second), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.size(110.dp).clip(CircleShape))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(artist.first, fontFamily = QuicksandFont, color = RetroPinkBorder, fontSize = 14.sp, maxLines = 1)
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        item {
            Row(modifier = Modifier.padding(horizontal = 24.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.img_artist_pinkpantheress), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.size(56.dp).clip(CircleShape))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("More like", fontFamily = FontFamily.Serif, color = RetroPinkBorder, fontSize = 14.sp)
                    Text("PinkPantheress", fontFamily = FontFamily.Serif, color = RetroPinkBorder, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(modifier = Modifier.weight(1f)) { TrackCard(TrackInfo("Little Bit", "Erika de Casier", R.drawable.img_track_littlebit, 200, R.raw.little_audio), onTrackClick) }
                    Box(modifier = Modifier.weight(1f)) { TrackCard(TrackInfo("Ditto", "New Jeans", R.drawable.img_track_ditto, 185, R.raw.ditto_audio), onTrackClick) }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(modifier = Modifier.weight(1f)) { TrackCard(TrackInfo("obsessed", "ilykimchi", R.drawable.img_track_obsessed, 175, R.raw.obsessed_audio), onTrackClick) }
                    Box(modifier = Modifier.weight(1f)) { TrackCard(TrackInfo("IN A MOOD", "Berryblue", R.drawable.img_track_inamood, 210, R.raw.inamood_audio), onTrackClick) }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(modifier = Modifier.weight(1f)) { TrackCard(TrackInfo("soft spot", "piri & tommy", R.drawable.img_track_softspot, 160, R.raw.softspot_audio), onTrackClick) }
                    Box(modifier = Modifier.weight(1f)) { TrackCard(TrackInfo("Jelly", "Supast4r", R.drawable.img_track_jelly, 190, R.raw.jelly_audio), onTrackClick) }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        item {
            Text("Recently played", fontFamily = FontFamily.Serif, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = RetroPinkBorder, modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(contentPadding = PaddingValues(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(AlbumRepository.albums.take(6)) { album ->
                    Image(
                        painter = painterResource(id = album.coverRes),
                        contentDescription = album.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(140.dp).clip(RoundedCornerShape(12.dp)).clickable { onNavigateToPlaylist(album.id) }
                    )
                }
            }
        }
    }
}


@Composable
private fun TabDailyMoodContent(onTrackClick: (TrackInfo) -> Unit, onNavigateToPlaylist: (String) -> Unit) {
    val fadeAnim = remember { Animatable(0f) }
    LaunchedEffect(key1 = true) {
        delay(300)
        fadeAnim.animateTo(targetValue = 1f, animationSpec = tween(1200))
    }

    val dailyAlbums = AlbumRepository.albums.drop(6)

    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 100.dp)) {
        item {
            Text("Daily Suggestion's", fontFamily = FontFamily.Serif, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = RetroPinkBorder, modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(contentPadding = PaddingValues(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(dailyAlbums) { album ->
                    Image(
                        painter = painterResource(id = album.coverRes),
                        contentDescription = album.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(140.dp).clip(RoundedCornerShape(12.dp)).clickable { onNavigateToPlaylist(album.id) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        item {
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text("Based on your recent listening", fontFamily = FontFamily.Serif, fontSize = 18.sp, color = RetroPinkBorder)
                Text("I think you should listen to...", fontFamily = FontFamily.Serif, fontSize = 18.sp, color = RetroPinkBorder)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.padding(horizontal = 24.dp)) {
                BannerCardBeabadoobee(onPlayClick = { onTrackClick(TrackInfo("Real Man", "Beabadoobee", R.drawable.img_banner_beabadoobee, 220, R.raw.realman_audio)) })
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        item {
            Text("Melody's personal suggestion", fontFamily = FontFamily.Serif, fontSize = 22.sp, color = RetroPinkBorder, modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(8.dp))
            InfiniteTypewriter(text = "I love Laufey", font = PixelFont, modifier = Modifier.padding(horizontal = 24.dp), color = Color(0xFFA566C7))
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f).alpha(fadeAnim.value), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    TrackCard(TrackInfo("Forget-me-not", "Laufey", R.drawable.img_track_forgetmenot, 240, R.raw.forgetmenot_audio), onTrackClick)
                    TrackCard(TrackInfo("Madwoman", "Laufey", R.drawable.img_track_madwoman, 215, R.raw.madwoman_audio), onTrackClick)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Image(painter = painterResource(id = R.drawable.img_melody_pixel), contentDescription = "Melody Mascot", modifier = Modifier.size(120.dp))
            }
        }
    }
}


@Composable
fun TabButton(text: String, isActive: Boolean, onClick: () -> Unit) {
    val gradientColors = if (isActive) listOf(Color(0xFFF76EA7), Color(0xFFC285E0)) else listOf(Color(0xFFE8B2C1), Color(0xFFD4B2E8))
    Box(
        modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(Brush.horizontalGradient(gradientColors)).clickable { onClick() }.padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) { Text(text, color = Color.White, fontFamily = QuicksandFont, fontWeight = FontWeight.Bold, fontSize = 14.sp) }
}

@Composable
fun TrackCard(track: TrackInfo, onClick: (TrackInfo) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth().clickable { onClick(track) }
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = track.coverRes), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)))
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(track.title, fontFamily = SonoraTypography.headlineLarge.fontFamily, color = RetroPinkBorder, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(track.artist, fontFamily = QuicksandFont, color = Color.Gray, fontSize = 12.sp, maxLines = 1)
            }
            Text("•••", color = RetroPinkBorder, fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 4.dp))
        }
    }
}

@Composable
fun BannerCard(onPlayClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().height(180.dp).clip(RoundedCornerShape(24.dp)).background(Brush.horizontalGradient(listOf(Color(0xFFE9C8F9), Color(0xFFFF8FBE))))) {
        Image(painter = painterResource(id = R.drawable.img_pinkpantheress_cutout), contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier.align(Alignment.BottomEnd).fillMaxHeight())
        Column(modifier = Modifier.padding(24.dp).fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Text("Fancy That", color = Color.White, fontSize = 28.sp, fontFamily = SonoraTypography.headlineLarge.fontFamily)
            Spacer(modifier = Modifier.height(4.dp))
            InfiniteTypewriter(text = "* By PinkPantheress *", font = PixelFont)
            Spacer(modifier = Modifier.height(24.dp))
            Box(modifier = Modifier.clip(RoundedCornerShape(16.dp)).background(Color.White).clickable { onPlayClick() }.padding(horizontal = 16.dp, vertical = 6.dp), contentAlignment = Alignment.Center) {
                Text("play ♡", color = RetroPinkBorder, fontWeight = FontWeight.Bold, fontFamily = QuicksandFont)
            }
        }
    }
}

@Composable
fun BannerCardBeabadoobee(onPlayClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().height(180.dp).clip(RoundedCornerShape(24.dp)).background(Brush.horizontalGradient(listOf(Color(0xFFE4C1F9), Color(0xFFFF99C8))))) {
        Image(painter = painterResource(id = R.drawable.img_beabadoobee_cutout), contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier.align(Alignment.BottomEnd).fillMaxHeight())
        Column(modifier = Modifier.padding(24.dp).fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Text("Real Man", color = Color.White, fontSize = 28.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            InfiniteTypewriter(text = "★ By Beabadoobee ★", font = PixelFont)
            Spacer(modifier = Modifier.height(24.dp))
            Box(modifier = Modifier.clip(RoundedCornerShape(16.dp)).background(Color.White).clickable { onPlayClick() }.padding(horizontal = 16.dp, vertical = 6.dp), contentAlignment = Alignment.Center) {
                Text("play ♡", color = RetroPinkBorder, fontWeight = FontWeight.Bold, fontFamily = QuicksandFont)
            }
        }
    }
}

@Composable
fun InfiniteTypewriter(text: String, font: FontFamily, modifier: Modifier = Modifier, color: Color = Color.White) {
    var displayedText by remember { mutableStateOf("") }
    LaunchedEffect(text) {
        while (true) {
            displayedText = ""
            for (i in text.indices) { displayedText += text[i]; delay(120L) }
            delay(2000L)
            for (i in text.indices.reversed()) { displayedText = displayedText.dropLast(1); delay(40L) }
            delay(500L)
        }
    }
    Text(
        text = displayedText,
        style = TextStyle(fontFamily = font, color = color, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 2.sp),
        modifier = modifier)

}

@Composable
fun ProgrammaticMiniPlayer(track: TrackInfo, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().height(72.dp).clip(RoundedCornerShape(36.dp)).background(Brush.horizontalGradient(listOf(Color(0xFFF76EA7).copy(alpha = 0.8f), Color(0xFFC285E0).copy(alpha = 0.8f)))).clickable { onClick() }) {
        Row(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = track.coverRes), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.size(48.dp).clip(CircleShape))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(track.title, color = Color.White, fontWeight = FontWeight.Bold, fontFamily = SonoraTypography.headlineLarge.fontFamily, fontSize = 16.sp)
                Text(track.artist, color = Color.White.copy(alpha = 0.8f), fontStyle = FontStyle.Italic, fontSize = 12.sp, fontFamily = QuicksandFont)
            }
            Icon(Icons.Filled.PlayArrow, contentDescription = "Play", tint = Color.White, modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
fun CommonBottomNavigation(
    onNavigateToHome: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToLibrary: () -> Unit,
    currentScreen: String
) {
    val BackgroundPink = Color(0xFFFFE4E8)
    NavigationBar(containerColor = BackgroundPink, tonalElevation = 0.dp) {
        NavigationBarItem(
            selected = currentScreen == "home",
            onClick = onNavigateToHome,
            icon = { Icon(Icons.Default.Home, null, tint = if(currentScreen == "home") RetroPinkBorder else Color.Gray) },
            label = { Text("Home", fontFamily = QuicksandFont, color = RetroPinkBorder) }
        )
        NavigationBarItem(
            selected = currentScreen == "search",
            onClick = onNavigateToSearch,
            icon = { Icon(Icons.Default.Search, null, tint = if(currentScreen == "search") RetroPinkBorder else Color.Gray) },
            label = { Text("Search", fontFamily = QuicksandFont, color = RetroPinkBorder) }
        )
        NavigationBarItem(
            selected = currentScreen == "library",
            onClick = onNavigateToLibrary,
            icon = { Icon(Icons.Default.LibraryMusic, null, tint = if(currentScreen == "library") RetroPinkBorder else Color.Gray) },
            label = { Text("Library", fontFamily = QuicksandFont, color = RetroPinkBorder) }
        )


        }
}