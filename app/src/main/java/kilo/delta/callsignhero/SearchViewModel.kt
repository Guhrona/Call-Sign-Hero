package kilo.delta.callsignhero

import android.view.View
import androidx.lifecycle.ViewModel
import kilo.delta.callsignhero.repositories.SearchRepository

class SearchViewModel: ViewModel() {

        var callsign: String? = null

        var searchListener: SearchListener? = null

        fun onSearchButtonClicked(view: View) {
            searchListener?.onStarted()

            if(callsign.isNullOrEmpty()) {
                searchListener?.onFailure("You need to enter a call sign to search.")
                return
            }
            //searchListener?.onSuccess(searchResponse)

            //FIXME - bad practice just use for now
            val searchResponse = SearchRepository().userSearch(callsign!!)
            searchListener?.onSuccess(searchResponse)
            //Timber.d("searchResponse = %s", searchResponse)
        }
}