package com.example.contactbook.navigation

sealed class Routes (var routes: String){

    object HOME : Routes("HOME")

    object SPLASH : Routes("SPLASH")
}