package com.example.contactbook.ui_layer

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.contactbook.data.database.Contact

data class ContactState (

    val contact :List<Contact> = emptyList(),

    val id : MutableState<Int> = mutableStateOf(0),
    val name : MutableState<String> = mutableStateOf(""),
    val number : MutableState<String> = mutableStateOf(""),
    val gmail : MutableState<String> = mutableStateOf(""),
    val dateOfCreation : MutableState<Long> = mutableStateOf(0)
)