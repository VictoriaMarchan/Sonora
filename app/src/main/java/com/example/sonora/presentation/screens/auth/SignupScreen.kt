package com.example.sonora.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun SignupScreen(onBackToLogin: () -> Unit, onSignupSuccess: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 32.dp, vertical = 48.dp)
                ) {


                    Text(
                        text = "Name",
                        color = RetroPinkBorder,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PixelFont,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(28.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = SplashGradientTop,
                            focusedBorderColor = RetroPinkBorder,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))


                    Text(
                        text = "Email Address",
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

                    Spacer(modifier = Modifier.height(20.dp))


                    Text(
                        text = "Password",
                        color = RetroPinkBorder,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PixelFont,
                        fontSize = 18.sp
                    )
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


                    Text(
                        text = "At least 8 characters, 1 number and 1 symbol",
                        color = RetroPinkBorder.copy(alpha = 0.8f),
                        fontSize = 10.sp,
                        fontFamily = PixelFont,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(40.dp))


                    Image(
                        painter = painterResource(id = R.drawable.btn_sign_up),
                        contentDescription = "Sign Up Button",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSignupSuccess() }
                    )

                    Spacer(modifier = Modifier.height(24.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Have an account? ",
                            color = RetroPinkBorder.copy(alpha = 0.6f),
                            fontSize = 12.sp,
                            fontFamily = PixelFont
                        )
                        Text(
                            text = "Sign In",
                            color = RetroPinkBorder,
                            fontSize = 12.sp,
                            fontFamily = PixelFont,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable { onBackToLogin() }
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}