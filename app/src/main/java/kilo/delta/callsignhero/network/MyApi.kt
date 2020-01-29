package kilo.delta.callsignhero.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("search")
    fun userSearch(
        @Field("callsign")callsign: String
    ) : Call<ResponseBody>


    companion object {
        operator fun invoke() : MyApi{
            return Retrofit.Builder()
                    //TODO - need to make the callsign a param
                .baseUrl("https://callook.info/w1aw/json")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}