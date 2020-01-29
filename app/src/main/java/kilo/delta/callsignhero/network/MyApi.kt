package kilo.delta.callsignhero.network

import androidx.annotation.NonNull
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {

    @GET("{callsign}/json")
    fun userSearch(@Path("callsign") @NonNull callsign: String) : Call<ResponseBody>

    companion object {
        operator fun invoke() : MyApi{
            return Retrofit.Builder()
                .baseUrl("https://callook.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}