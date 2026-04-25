package com.example.sonora.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sonora.R
import com.example.sonora.core.theme.RetroPinkBorder
import com.example.sonora.data.model.AlbumRepository

val LilaSoft = Color(0xFFC88DCF)
val PinkStrong = Color(0xFFCE176C)
val PinkBio = Color(0xFFEF6CA6)

val QuicksandFont = FontFamily.SansSerif
val SonoraSerif = FontFamily.Serif
val CaveatFont = FontFamily.Cursive

fun Modifier.notebookBackground() = this.drawBehind {
    val backgroundColor = Color(0xFFFFFBEB)
    val lineColor = Color(0xFFF764A9)
    drawRect(color = backgroundColor)
    val lineHeight = 35.dp.toPx()
    var y = lineHeight
    while (y < size.height) {
        drawLine(
            color = lineColor.copy(alpha = 0.25f),
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1.dp.toPx()
        )
        y += lineHeight
    }
}

@Composable
fun ProfileScreen(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().notebookBackground()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 48.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        modifier = Modifier.size(28.dp).clickable { onBack() },
                        tint = RetroPinkBorder
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "User",
                        fontFamily = SonoraSerif,
                        fontStyle = FontStyle.Italic,
                        color = RetroPinkBorder,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(28.dp))
                }
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_profile_avatar),
                        contentDescription = "Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(110.dp).clip(CircleShape).background(Color.White)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "@usercore",
                        fontFamily = CaveatFont,
                        fontSize = 26.sp,
                        color = LilaSoft
                    )

                    Text(
                        text = "chronically online. Collecting playlists like seashells",
                        fontFamily = QuicksandFont,
                        fontSize = 13.sp,
                        color = PinkBio,
                        modifier = Modifier.padding(horizontal = 48.dp),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ProfileStat(value = "14", label = "playlists")
                        ProfileStat(value = "342", label = "followers")
                        ProfileStat(value = "108", label = "following")
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Favorite artists",
                    fontFamily = SonoraSerif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 22.sp,
                    color = RetroPinkBorder,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    FavoriteArtistCard(modifier = Modifier.weight(1f), title = "Beabadoobee", playedCount = "342", imageRes = R.drawable.img_fav_artist_1)
                    FavoriteArtistCard(modifier = Modifier.weight(1f), title = "Laufey", playedCount = "218", imageRes = R.drawable.img_fav_artist_2)
                }
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Listening diary",
                        fontFamily = SonoraSerif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 22.sp,
                        color = RetroPinkBorder
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "this week",
                        fontFamily = CaveatFont,
                        fontSize = 18.sp,
                        color = LilaSoft
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            val diaryAlbum = AlbumRepository.getAlbum("malice_mizer")
            diaryAlbum?.let { album ->
                itemsIndexed(album.tracks.take(3)) { index, track ->
                    DiaryTrackItem(track.title, album.artist, track.releaseDate, track.durationSeconds, album.coverRes)
                }
            }
        }
    }
}

@Composable
fun ProfileStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontFamily = CaveatFont, fontSize = 28.sp, color = PinkStrong, fontWeight = FontWeight.Bold)
        Text(text = label, fontFamily = QuicksandFont, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun FavoriteArtistCard(modifier: Modifier, title: String, playedCount: String, imageRes: Int) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontFamily = SonoraSerif, fontStyle = FontStyle.Italic, fontSize = 14.sp, color = RetroPinkBorder)
            Text(text = "played $playedCount times", fontFamily = QuicksandFont, fontSize = 10.sp, color = Color.Gray)
        }
    }
}

@Composable
fun DiaryTrackItem(title: String, artist: String, date: String, duration: Int, cover: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = cover),
            contentDescription = null,
            modifier = Modifier.size(45.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontFamily = SonoraSerif, color = RetroPinkBorder, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Text(text = artist, fontFamily = QuicksandFont, color = Color.Gray, fontSize = 12.sp)
        }
        Text(text = date, fontFamily = QuicksandFont, color = Color.Gray, fontSize = 11.sp, modifier = Modifier.width(50.dp))
        Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = PinkStrong, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(8.dp))

        val m = duration / 60
        val s = (duration % 60).toString().padStart(2, '0')
        Text(text = "$m:$s", fontFamily = QuicksandFont, color = Color.Gray, fontSize = 12.sp)
    }
}