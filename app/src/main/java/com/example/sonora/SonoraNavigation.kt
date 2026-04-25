package com.example.sonora

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sonora.data.model.TrackInfo
import com.example.sonora.presentation.screens.auth.LoginScreen
import com.example.sonora.presentation.screens.auth.SignupScreen
import com.example.sonora.presentation.screens.home.HomeScreen
import com.example.sonora.presentation.screens.home.HomeTab
import com.example.sonora.presentation.screens.library.LibraryScreen
import com.example.sonora.presentation.screens.onboarding.InterestsScreen
import com.example.sonora.presentation.screens.player.VinylPlayerScreen
import com.example.sonora.presentation.screens.playlist.PlaylistScreen
import com.example.sonora.presentation.screens.profile.ProfileScreen
import com.example.sonora.presentation.screens.search.SearchScreen
import com.example.sonora.presentation.screens.splash.SplashScreen

@Composable
fun SonoraNavigation() {
    val navController = rememberNavController()

    var currentPlayingTrack by remember { mutableStateOf<TrackInfo?>(null) }

    var selectedHomeTab by remember { mutableStateOf(HomeTab.ALL) }

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(onNavigateToAuth = {
                navController.navigate("login") { popUpTo("splash") { inclusive = true } }
            })
        }

        composable("login") {
            LoginScreen(
                onNavigateToSignup = { navController.navigate("signup") },
                onLoginSuccess = {
                    navController.navigate("interests") { popUpTo("login") { inclusive = true } }
                }
            )
        }

        composable("signup") {
            SignupScreen(
                onBackToLogin = { navController.popBackStack() },
                onSignupSuccess = {
                    navController.navigate("interests") {
                        popUpTo("signup") { inclusive = true }
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("interests") {
            InterestsScreen(onContinue = {
                navController.navigate("home") { popUpTo("interests") { inclusive = true } }
            })
        }

        composable("home") {
            HomeScreen(
                currentTrack = currentPlayingTrack,
                selectedTab = selectedHomeTab,
                onTabSelected = { selectedHomeTab = it },
                onTrackSelected = { track -> currentPlayingTrack = track },
                onNavigateToPlayer = { navController.navigate("player") },
                onNavigateToProfile = { navController.navigate("profile") },
                onNavigateToSearch = { navController.navigate("search") },
                onNavigateToLibrary = { navController.navigate("library") },
                onNavigateToPlaylist = { albumId -> navController.navigate("playlist/$albumId") }
            )
        }

        composable("profile") { ProfileScreen(onBack = { navController.popBackStack() }) }


        composable("playlist/{albumId}") { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId") ?: ""
            PlaylistScreen(
                albumId = albumId,
                onBack = { navController.popBackStack() },
                onTrackClick = { track ->
                    currentPlayingTrack = track
                    navController.navigate("player")
                },
                currentTrack = currentPlayingTrack,
                onNavigateToPlayer = { navController.navigate("player") }
            )
        }


            composable("player") {
                currentPlayingTrack?.let { track ->
                    VinylPlayerScreen(
                        trackName = track.title,
                        artistName = track.artist,
                        coverRes = track.coverRes,
                        audioResId = track.audioResId,
                        onMinimize = { navController.popBackStack() }
                    )
            }
        }

        composable("library") {
            LibraryScreen(
                currentTrack = currentPlayingTrack,
                onNavigateToPlayer = { navController.navigate("player") },
                onNavigateToPlaylist = { albumId -> navController.navigate("playlist/$albumId") },
                onNavigateToHome = { navController.popBackStack("home", inclusive = false) },
                onNavigateToSearch = { navController.navigate("search") }
            )
        }

        composable("search") {
            SearchScreen(
                currentTrack = currentPlayingTrack,
                onNavigateToPlayer = { navController.navigate("player") },
                onNavigateToPlaylist = { albumId -> navController.navigate("playlist/$albumId") },
                onTrackSelected = { track -> currentPlayingTrack = track },
                onNavigateToLibrary = { navController.navigate("library") },
                onNavigateToHome = { navController.popBackStack("home", inclusive = false) }
            )
        }
    }
}