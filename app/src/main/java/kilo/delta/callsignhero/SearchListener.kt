package kilo.delta.callsignhero

interface SearchListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}