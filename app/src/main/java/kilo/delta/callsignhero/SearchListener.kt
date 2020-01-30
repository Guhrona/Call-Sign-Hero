package kilo.delta.callsignhero

import androidx.lifecycle.LiveData
import io.reactivex.Single
import kilo.delta.callsignhero.data.CallookResponse

interface SearchListener {
    fun onStarted()
    fun onSuccess(searchResponse: CallookResponse)
    fun onFailure(message: String)
}