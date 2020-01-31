package kilo.delta.callsignhero.repositories

import io.reactivex.Single
import kilo.delta.callsignhero.data.CallookResponse
import kilo.delta.callsignhero.network.MyApi

class SearchRepository {

   fun userSearch(callsign: String): Single<CallookResponse> {

       //TODO - bad practice use injection
        return MyApi().userSearch(callsign)
            .map {
                it
            }
    }
}