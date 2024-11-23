package com.nick.mowen.fleetio.api

import com.nick.mowen.fleetio.BuildConfig
import com.nick.mowen.fleetio.data.VehiclesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class FleetioClient {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        chain.proceed(
            chain.request()
                .newBuilder()
                .also { builder ->
                    builder.addHeader("Content-Type", "application/json")
                    builder.addHeader("Authorization", BuildConfig.API_KEY)
                    builder.addHeader("Account-Token", BuildConfig.ACCOUNT_TOKEN)
                }.build()
        )
    }.build()
    private val client: PrivateClient =
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
            .create(PrivateClient::class.java)

    suspend fun getVehicles(startCursor: String? = null): VehiclesResponse? = withContext(Dispatchers.Default) {
        try {
            client.getVehicles(startCursor).execute().body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private interface PrivateClient {

        @GET("vehicles")
        fun getVehicles(
            @Query("start_cursor") startCursor: String?,
            @Query("per_page") limit: Int = 10,
            @Query("sort[name]") nameSort: String = "asc"
        ): Call<VehiclesResponse>
    }

    companion object {

        private const val BASE_URL = "https://secure.fleetio.com/api/v1/"
    }
}