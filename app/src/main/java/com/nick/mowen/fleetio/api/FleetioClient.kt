package com.nick.mowen.fleetio.api

import com.nick.mowen.fleetio.BuildConfig
import com.nick.mowen.fleetio.data.AssignmentResponse
import com.nick.mowen.fleetio.data.CommentResponse
import com.nick.mowen.fleetio.data.VehiclesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
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
    }.addInterceptor(HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }).build()
    private val client: PrivateClient =
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
            .create(PrivateClient::class.java)

    suspend fun getVehicles(
        startCursor: String? = null,
        nameFilter: String? = null,
    ): VehiclesResponse? = withContext(Dispatchers.Default) {
        try {
            client.getVehicles(startCursor, filterName = nameFilter).execute().body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getCommentsOnVehicle(vehicleId: Long, startCursor: String? = null) = withContext(Dispatchers.Default) {
        // TODO figure out how to use vehicle ID to filter comments on specific vehicle
        try {
            client.getComments(startCursor).execute().body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getVehicleAssignment(vehicleId: Long) = withContext(Dispatchers.Default) {
        try {
            client.getVehicleAssignments(vehicleId).execute().body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private interface PrivateClient {

        @GET("vehicles")
        fun getVehicles(
            @Query("start_cursor") startCursor: String?,
            @Query("per_page") limit: Int = DEFAULT_VEHICLE_PAGE_SIZE,
            @Query("sort[name]") nameSort: String = DEFAULT_VEHICLE_SORT,
            @Query("filter[name][like]") filterName: String? = null,
        ): Call<VehiclesResponse>

        @GET("comments")
        fun getComments(@Query("start_cursor") startCursor: String?): Call<CommentResponse>

        @GET("vehicles/{vehicleId}/current_assignment")
        fun getVehicleAssignments(
            @Path("vehicleId") vehicleId: Long
        ): Call<AssignmentResponse>
    }

    companion object {

        private const val BASE_URL = "https://secure.fleetio.com/api/v1/"

        private const val DEFAULT_VEHICLE_PAGE_SIZE = 10
        private const val DEFAULT_VEHICLE_SORT = "asc"
    }
}