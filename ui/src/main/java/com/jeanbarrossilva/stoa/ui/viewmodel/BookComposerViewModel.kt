package com.jeanbarrossilva.stoa.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookComposerViewModel: ViewModel() {
    private val coverBitmap = MutableLiveData<Bitmap>(null)
    private val title = MutableLiveData<String>()
    private val subtitle = MutableLiveData<String>()
    private val description = MutableLiveData<String>()

    fun getCoverBitmap(): LiveData<Bitmap> {
        return coverBitmap
    }

    fun setCoverBitmap(coverBitmap: Bitmap) {
        this.coverBitmap.value = coverBitmap
    }

    fun getTitle(): LiveData<String> {
        return title
    }

    fun setTitle(title: String) {
        this.title.value = title
    }

    fun getSubtitle(): LiveData<String> {
        return subtitle
    }

    fun setSubtitle(subtitle: String) {
        this.subtitle.value = subtitle
    }

    fun getDescription(): LiveData<String> {
        return description
    }

    fun setDescription(description: String) {
        this.description.value = description
    }
}