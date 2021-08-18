package com.gekaradchenko.goresttestwork.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.gekaradchenko.goresttestwork.database.Item
import com.gekaradchenko.goresttestwork.database.ItemDao
import com.gekaradchenko.goresttestwork.network.GorestApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: ItemDao) {


    val items: LiveData<List<Item>> = database.getItems()


    suspend fun updateItems(page: Int) {
        withContext(Dispatchers.IO) {
            val getGorestDeferred = GorestApi.retrofitInstance.getItemAsync(page)
            try {
                val result = getGorestDeferred.await()

                result.data.forEach {
                    database.insert(Item(it.id, it.title, it.body))
                }

            } catch (t: Throwable) {
                Log.d("Repository", "updateItems: ${t.message}")
            }
        }
    }
}