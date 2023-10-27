package com.example.teli

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class login : AppCompatActivity() {

    lateinit var phoneNumberEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var loginButton: Button
    lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://donkeydeliveries.com/donkey/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authService = retrofit.create(AuthService::class.java)

        phoneNumberEditText = findViewById(R.id.phoneno)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val phoneNumber = phoneNumberEditText.text.toString()
            val password = passwordEditText.text.toString()

            var logReq = LoginRequest()

            logReq.phone = phoneNumber
            logReq.password = password
            logReq.devicetoken = "hguygvyygygy8"


            authService.login(logReq).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val token = response.body()?.data!!.token
                        Toast.makeText(this@login, "api success", Toast.LENGTH_SHORT).show()
                        // Handle a successful login, save the token, and navigate to the next screen
                      val  intent = Intent (this@login, first::class.java )
                        startActivity(intent)
                    } else {
                        // Handle login failure
                        when (response.code()) {
                            401 -> {
                                // Unauthorized, incorrect credentials
                                Toast.makeText(this@login, "Incorrect username or password", Toast.LENGTH_SHORT).show()

                            }
                            500 -> {
                                // Internal server error
                                Toast.makeText(this@login, "Server error. Please try again later.", Toast.LENGTH_SHORT).show()

                            }
                            403  -> {
                                // Internal server error
                                Toast.makeText(this@login, "Forbidden: You don't have permission to access this resource.", Toast.LENGTH_SHORT).show()

                            }
                            404  -> {
                                // Internal server error
                                Toast.makeText(this@login, "Not Found: The requested resource was not found.", Toast.LENGTH_SHORT).show()

                            }
                            else -> {
                                // Handle other status codes or generic error message
                                Toast.makeText(this@login, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show()

                            }
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Handle network or API call failure
                    Toast.makeText(this@login, "api failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
