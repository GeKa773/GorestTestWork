package com.gekaradchenko.goresttestwork.mainfragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gekaradchenko.goresttestwork.database.Item
import com.gekaradchenko.goresttestwork.database.ItemDao
import com.gekaradchenko.goresttestwork.network.GorestApi
import com.gekaradchenko.goresttestwork.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(data: ItemDao, application: Application) : AndroidViewModel(application) {

    private val database = data
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val repository = Repository(database)

    private val _page = MutableLiveData<Int>(1)
    val page: LiveData<Int> = _page

    private val _progress = MutableLiveData<Boolean>(false)
    val progress: LiveData<Boolean> = _progress

    init {
        coroutineScope.launch {
            _page.value?.let {
                _progress.value = true
                repository.updateItems(it)
                _progress.value = false
            }
        }
    }

    val list = repository.items

    fun nextPage() {
        coroutineScope.launch {
            _page.value?.let {
                _progress.value = true
                _page.value = it + 1
                repository.updateItems(it + 1)
                _progress.value = false
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}