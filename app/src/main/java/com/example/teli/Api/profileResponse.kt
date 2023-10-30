package com.example.teli.Api

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("gender")
	val gender: Any? = null,

	@field:SerializedName("dop")
	val dop: Any? = null,

	@field:SerializedName("phone")
	val phone: Long? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class ProfileResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
