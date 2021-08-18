package com.gekaradchenko.goresttestwork.mainfragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.goresttestwork.database.ItemDao
import java.lang.IllegalArgumentException

class MainViewModelFactory(
    private val data: ItemDao,
    private val application: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(data, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}
