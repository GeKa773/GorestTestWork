package com.gekaradchenko.goresttestwork.startfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.gekaradchenko.goresttestwork.SingleLiveEvent
import kotlinx.coroutines.*

private const val TIME_WAIT = 2_500L

class StartViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Default)

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    init {
        navigateWait()
    }

    private fun onNavigate() {
        _navigationEvent.postValue(
            StartFragmentDirections.actionStartFragmentToMainFragment()
        )
    }

    private fun navigateWait() {
        coroutineScope.launch {
            delay(TIME_WAIT)
            onNavigate()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}