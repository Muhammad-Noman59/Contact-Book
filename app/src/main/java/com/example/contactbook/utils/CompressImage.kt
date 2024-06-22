package com.example.contactbook.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun compressImage( imageData: ByteArray ): ByteArray {

    val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream) // Adjust quality as needed
    return outputStream.toByteArray()
}
