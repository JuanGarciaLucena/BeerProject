package com.emebesoft.beerProject.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.emebesoft.beerProject.ui.theme.MyApplicationTheme

class BeerListScreen(private val navController: NavController) {


    @Composable
    fun BeerList() {

        val beerId = 123

        MyApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                color = MaterialTheme.colorScheme.background
            ) {

                Button(
                    onClick = { navController.navigate("BeerDetailScreen/${beerId}") },
                    modifier = Modifier
                        .height(100.dp)
                        .width(200.dp)
                ) {
                    Text(text = "BOTON")
                }
            }
        }
    }
}