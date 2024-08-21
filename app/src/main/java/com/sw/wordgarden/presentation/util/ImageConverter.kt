package com.sw.wordgarden.presentation.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.IOException

object ImageConverter {
    private const val TAG = "UriToByteArrayConvertor"

    fun uriToString(uri: Uri, context: Context): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val imageBytes = byteArrayOutputStream.toByteArray()
            val base64Image = Base64.encodeToString(imageBytes, Base64.DEFAULT)

            base64Image
        } catch (e: IOException) {
            Log.e(TAG, "Failed to convert URI to Bitmap", e)
            null
        }
    }

    fun stringToByteArray(imageString: String): ByteArray {
        return Base64.decode(imageString, Base64.DEFAULT)
    }
}