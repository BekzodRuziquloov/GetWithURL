package space.beka.getwithurl.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL = "https://hvax.pythonanywhere.com/"

    fun getRetrofitService():RetrofitServise{
        val build =Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return build.create(RetrofitServise::class.java)
    }
}