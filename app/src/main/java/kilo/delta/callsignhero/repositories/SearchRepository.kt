package kilo.delta.callsignhero.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kilo.delta.callsignhero.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository {
   fun userSearch(callsign: String): LiveData<String> {

       val searchResponse = MutableLiveData<String>()

        //TODO - bad practice inject this later
        MyApi().userSearch(callsign)
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    searchResponse.value = t.message
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        searchResponse.value = response.body()?.string()
                    } else {
                        searchResponse.value = response.errorBody()?.string()
                    }
                }
            })
        return searchResponse
    }
}