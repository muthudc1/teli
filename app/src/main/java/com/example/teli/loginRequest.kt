package com.example.teli

import com.google.gson.annotations.SerializedName

data class LoginRequest(
	@field:SerializedName("phone")
	var phone: String? = null,

	@field:SerializedName("password")
	var password: String? = null,

	@field:SerializedName("device_token")
	var devicetoken: String? = null,

	)