package com.example.contactbook.ui_layer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contactbook.R
import com.example.contactbook.navigation.Routes
import com.example.contactbook.ui.theme.PrimaryColor
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay


@Composable
fun Splash(navHostController: NavHostController) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PrimaryColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(300.dp)
        )
    }

    LaunchedEffect(key1 = true) {

        delay(3000)

        navHostController.navigate(Routes.HOME.routes)
    }

}