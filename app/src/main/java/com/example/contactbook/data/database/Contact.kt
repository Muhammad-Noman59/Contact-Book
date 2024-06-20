package com.example.contactbook.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
   @ColumnInfo(name = "user_name") val name: String,
    val number: String,
    val gmail: String,
    val dateOfCreation: Long,
    val isActive : Boolean
)