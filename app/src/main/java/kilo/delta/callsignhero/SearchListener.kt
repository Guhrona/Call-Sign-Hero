package kilo.delta.callsignhero

import androidx.lifecycle.LiveData

interface SearchListener {
    fun onStarted()
    fun onSuccess(searchResponse: LiveData<String>)
    fun onFailure(message: String)
}