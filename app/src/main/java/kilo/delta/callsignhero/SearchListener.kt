package kilo.delta.callsignhero

import kilo.delta.callsignhero.data.CallookResponse

interface SearchListener {
    fun onStarted()
    fun onSuccess(searchResponse: CallookResponse)
    fun onFailure(message: String)
}