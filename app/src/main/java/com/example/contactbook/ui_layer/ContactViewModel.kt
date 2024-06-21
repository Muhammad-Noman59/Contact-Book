package com.example.contactbook.ui_layer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactbook.data.database.Contact
import com.example.contactbook.data.database.ContactDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContactViewModel @Inject constructor(val database: ContactDatabase) : ViewModel() {

    private var isSortByName = MutableStateFlow(false)
    @OptIn(ExperimentalCoroutinesApi::class)
    private var contact = isSortByName.flatMapLatest {

        if (it) {
            database.dao.getContactSortByName()
        } else {
            database.dao.getContactSortByDate()
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = emptyList(),
        started = SharingStarted.WhileSubscribed()
    )

    val _state = MutableStateFlow(ContactState())

    val state = combine( _state, contact, isSortByName ){
        _state, contact, isSortByName ->

        _state.copy(
           contact = contact
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ContactState()
    )

    fun changeSorting (){
        isSortByName.value = !isSortByName.value
    }

    fun deleteContact(){

        val contact = Contact(

            id = state.value.id.value,
            name = state.value.name.value,
            number = state.value.number.value,
            gmail = state.value.gmail.value,
            dateOfCreation = state.value.dateOfCreation.value,
            isActive = true
        )
        viewModelScope.launch {
            database.dao.deleteContact(contact = contact)
        }

        state.value.id.value = 0
        state.value.name.value =""
        state.value.number.value = ""
        state.value.gmail.value = ""
        state.value.dateOfCreation.value = 0
        state.value.image.value = ByteArray(0)
    }


    fun saveContact (){

        val contact = Contact(

            id = state.value.id.value,
            name = state.value.name.value,
            number = state.value.number.value,
            gmail = state.value.gmail.value,
            dateOfCreation = System.currentTimeMillis(),
            isActive = true,
            image = state.value.image.value
        )

        viewModelScope.launch {
            database.dao.saveEditContact(contact = contact)
        }

        state.value.id.value = 0
        state.value.name.value =""
        state.value.number.value = ""
        state.value.gmail.value = ""
        state.value.dateOfCreation.value = 0
        state.value.image.value = ByteArray(0)
    }
}