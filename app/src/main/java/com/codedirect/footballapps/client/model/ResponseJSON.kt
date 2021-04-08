package com.codedirect.footballapps.client.model

import com.google.gson.annotations.SerializedName

data class ResponseJSON(

	@field:SerializedName("employee")
	val employee: List<EmployeeItems?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)