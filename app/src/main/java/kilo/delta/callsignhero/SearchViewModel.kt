package kilo.delta.callsignhero

import android.view.View
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {

        var callsign: String? = null

        var searchListener: SearchListener? = null

        fun onSearchButtonClicked(view: View) {
            searchListener?.onStarted()

            if(callsign.isNullOrEmpty()) {
                searchListener?.onFailure("You need to enter a call sign to search.")
                return
            }
            searchListener?.onSuccess()
        }
}