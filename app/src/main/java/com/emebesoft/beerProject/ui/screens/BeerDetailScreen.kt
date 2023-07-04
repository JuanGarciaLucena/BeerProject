package com.emebesoft.beerProject.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.emebesoft.beerProject.ui.theme.MyApplicationTheme

class BeerDetailScreen (private val navController: NavController, private val beerId: String) {

    @Composable
    fun Detail() {

        MyApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Text(text = "CERVEZA DETALLE")
            }
        }
    }
}