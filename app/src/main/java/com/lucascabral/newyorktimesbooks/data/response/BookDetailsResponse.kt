package com.lucascabral.newyorktimesbooks.data.response

import com.google.gson.annotations.SerializedName

data class BookDetailsResponse(
        @SerializedName("title")
        val title: String,
        @SerializedName("author")
        val author: String,
        @SerializedName("description")
        val description: String
)
