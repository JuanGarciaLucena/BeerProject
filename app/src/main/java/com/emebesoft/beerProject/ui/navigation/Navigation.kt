package com.emebesoft.beerProject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emebesoft.beerProject.ui.screens.BeerDetailScreen
import com.emebesoft.beerProject.ui.screens.BeerListScreen
import com.emebesoft.beerProject.ui.viewmodels.BeerViewModel

class Navigation {

    @Composable
    fun MyAppNavHost() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "BeerListScreen") {
            composable("BeerListScreen") { BeerListScreen(navController).BeerList() }
            composable(
                route = "BeerDetailScreen/{beerId}",
                arguments = listOf(navArgument("beerId") { type = NavType.StringType }))
            { backStackNavEntry ->
                BeerDetailScreen(navController = navController, backStackNavEntry.arguments?.getString("beerId")!!).Detail()
            }
        }
    }
}