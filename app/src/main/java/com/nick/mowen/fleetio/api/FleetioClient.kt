package com.nick.mowen.fleetio.api

import com.nick.mowen.fleetio.BuildConfig
import com.nick.mowen.fleetio.data.VehiclesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class FleetioClient {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().build().also { client ->
        client.networkInterceptors().add(Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().let { builder ->
                builder.header("Content-Type", "application/json")
                builder.header("Authorization", BuildConfig.API_KEY)
                builder.header("Account-Token", BuildConfig.ACCOUNT_TOKEN)
                builder.build()
            })
        })
    }
    private val client: PrivateClient =
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
            .create(PrivateClient::class.java)

    suspend fun getVehicles(): VehiclesResponse? = withContext(Dispatchers.Default) {
        try {
            client.getVehicles().execute().body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private interface PrivateClient {

        @GET("")
        fun getVehicles(): Call<VehiclesResponse>
    }

    companion object {

        private const val BASE_URL = "https://secure.fleetio.com/api/v1/"
    }
}