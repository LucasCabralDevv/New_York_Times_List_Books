package com.lucascabral.newyorktimesbooks.data.response

import com.google.gson.annotations.SerializedName

data class BookBodyResponse (
        @SerializedName("results")
        val bookResults: List<BookResultResponse>
)