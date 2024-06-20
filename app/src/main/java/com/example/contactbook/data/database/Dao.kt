package com.example.contactbook.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    @Upsert
    suspend fun saveEditContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact_table ORDER BY user_name ASC")
    fun getContactSortByName() : Flow<List<Contact>>

    @Query("SELECT * FROM contact_table ORDER BY dateOfCreation DESC")
    fun getContactSortByDate() : Flow<List<Contact>>
}