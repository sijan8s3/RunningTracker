package com.sijanneupane.runningtracker.database

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {

    @TypeConverter
    //convert the bitmap images to ByteArray to save it in database
    fun fromBitmap(bmp: Bitmap): ByteArray{
        val outputStream= ByteArrayOutputStream()  //outputStream to save the bytes
        bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }


    @TypeConverter
    //convert the byteArray  to Bitmap
    fun toBitmap(byteArray: ByteArray): Bitmap{
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}