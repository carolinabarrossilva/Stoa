package com.jeanbarrossilva.stoa.extensions.uri

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri

fun Uri.asBitmap(context: Context?): Bitmap? {
    return context?.contentResolver?.let {
        val source = ImageDecoder.createSource(it, this)
        ImageDecoder.decodeBitmap(source)
    }
}