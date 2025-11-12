package com.kmp.book.presentation.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kmp.book.presentation.screen.detail.DetailScreen
import com.kmp.book.presentation.screen.home.HomeScreen

@Composable
fun BookNavigation(){
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.Home,
        ){
            composable<Screen.Home> {
                HomeScreen(
                    navController = navController,
                )
            }
            composable<Screen.Detail> { backStackEntry ->
                val thumbnail = backStackEntry.toRoute<Screen.Detail>().thumbnail
                val title = backStackEntry.toRoute<Screen.Detail>().title
                val contents = backStackEntry.toRoute<Screen.Detail>().contents
                val publisher = backStackEntry.toRoute<Screen.Detail>().publisher
                val salePrice = backStackEntry.toRoute<Screen.Detail>().salePrice
                val status = backStackEntry.toRoute<Screen.Detail>().status
                val authors = backStackEntry.toRoute<Screen.Detail>().authors
                val datetime = backStackEntry.toRoute<Screen.Detail>().datetime
                DetailScreen(
                    navController = navController,
                    thumbnail = thumbnail,
                    title = title,
                    contents = contents,
                    publisher = publisher,
                    salePrice = salePrice,
                    status = status,
                    authors = authors,
                    datetime = datetime,
                )
            }
        }
    }
}