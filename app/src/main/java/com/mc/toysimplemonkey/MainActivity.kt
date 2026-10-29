package com.mc.toysimplemonkey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mc.toysimplemonkey.features.simple_consulting.compose.presentation.SimpleConsultingScreen

import com.mc.toysimplemonkey.ui.theme.SimpleMonkeyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMonkeyTheme() {
                SimpleConsultingScreen()
            }
        }
    }
}
