package com.dd2d.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dd2d.core.presentation.theme.ProjectTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { true }
        }
        enableEdgeToEdge()
        setContent {
            ProjectTemplateTheme(
                darkTheme = false
            ) {

            }
        }
    }
}