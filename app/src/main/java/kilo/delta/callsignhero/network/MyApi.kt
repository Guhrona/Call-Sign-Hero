package kilo.delta.callsignhero.network

import androidx.annotation.NonNull
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import kilo.delta.callsignhero.data.CallookResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {

    @GET("{callsign}/json")
    fun userSearch(@Path("callsign") @NonNull callsign: String) : Single<CallookResponse>

    companion object {
        operator fun invoke() : MyApi{
            return Retrofit.Builder()
                .baseUrl("https://callook.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}