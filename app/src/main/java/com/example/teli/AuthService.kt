package com.example.teli
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

        @POST("login") // Replace with your API endpoint
        fun login(@Body loginRequest: LoginRequest): Call<LoginResponse> // Use the appropriate response type for your API

    //@POST("login") // Replace with your API endpoint
//fun login(@Query ("phone") phone: String, @Query ("password") password: String,@Query ("device_token") device_token: String): Call<LoginResponse> // Use the appropriate response type for your API

}


