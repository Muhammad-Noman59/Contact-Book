package com.example.contactbook.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.contactbook.ui_layer.ContactState
import com.example.contactbook.ui_layer.ContactViewModel
import com.example.contactbook.ui_layer.Home
import com.example.contactbook.ui_layer.Splash


@Composable
fun NavGraph(
    navHostController: NavHostController,
    viewModel: ContactViewModel,
    state: ContactState,
    onEvent: () -> Unit
) {

    NavHost(navController = navHostController, startDestination = Routes.SPLASH.routes) {

        composable (Routes.SPLASH.routes){
            Splash(navHostController = navHostController)
        }

        composable(Routes.HOME.routes){
            Home(
                state = state,
                viewModel = viewModel
            ) {
                onEvent()
            }
        }
    }
}
