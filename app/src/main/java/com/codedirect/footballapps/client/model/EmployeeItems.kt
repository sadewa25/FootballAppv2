package com.codedirect.footballapps.client.model

import com.google.gson.annotations.SerializedName

data class EmployeeItems(

	@field:SerializedName("name_user")
	val nameUser: String? = null,

	@field:SerializedName("email_user")
	val emailUser: String? = null,

	@field:SerializedName("password_user")
	val passwordUser: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null
)