package com.example.sonora.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.sonora.R
import com.example.sonora.core.theme.*

@Composable
fun LoginScreen(onNavigateToSignup: () -> Unit, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(SplashGradientTop, SplashGradientBottom)
    )

    val PixelFont = FontFamily.Monospace

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(56.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
            ) {

                Column(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 130.dp)
                        .padding(bottom = 32.dp)
                ) {
                    Text(
                        text = "SONORA",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Black,
                        fontStyle = FontStyle.Italic,
                        color = RetroPinkBorder,
                        fontFamily = FontFamily.Serif
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Text(
                        text = "Be a part of the soft music club",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontFamily = PixelFont
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Curate your digital diary, spin vintage vinyls, and collect melodies.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        fontFamily = PixelFont,
                        lineHeight = 18.sp
                    )
                }


                Image(
                    painter = painterResource(id = R.drawable.mascot_girl),
                    contentDescription = "Mascot",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(190.dp)
                        .offset(x = 16.dp, y = 85.dp)
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        Color.White.copy(alpha = 0.98f),
                        RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
                    .padding(horizontal = 32.dp, vertical = 48.dp)
            ) {
                Column {
                    Text(
                        text = "Email address",
                        color = RetroPinkBorder,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PixelFont,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(28.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = SplashGradientTop,
                            focusedBorderColor = RetroPinkBorder,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "Password",
                            color = RetroPinkBorder,
                            fontWeight = FontWeight.Bold,
                            fontFamily = PixelFont,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Forgot password?",
                            color = RetroPinkBorder.copy(alpha = 0.6f),
                            fontSize = 12.sp,
                            fontFamily = PixelFont,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable { }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(28.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = SplashGradientTop,
                            focusedBorderColor = RetroPinkBorder,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (rememberMe) RetroPinkBorder else SplashGradientTop)
                                    .clickable { rememberMe = !rememberMe }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Remember me",
                                color = RetroPinkBorder,
                                fontFamily = PixelFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Face ID",
                                color = RetroPinkBorder,
                                fontFamily = PixelFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(RetroPinkBorder)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(48.dp))


                    Image(
                        painter = painterResource(id = R.drawable.btn_sign_in),
                        contentDescription = "Sign In Button",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLoginSuccess() }
                    )

                    Spacer(modifier = Modifier.weight(1f))


                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Create an account",
                            color = RetroPinkBorder.copy(alpha = 0.7f),
                            fontSize = 14.sp,
                            fontFamily = PixelFont,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable { onNavigateToSignup() }
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}