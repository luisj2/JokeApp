package com.xluis.adviceapp.Presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.xluis.adviceapp.Presentation.Navitgation.NavigationWrapper
import com.xluis.adviceapp.ui.theme.AdviceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdviceAppTheme {
                NavigationWrapper()
            }
        }
    }
}