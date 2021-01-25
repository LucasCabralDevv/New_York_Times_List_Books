package com.lucascabral.newyorktimesbooks.data.response

import com.google.gson.annotations.SerializedName

data class BookResultResponse(
        @SerializedName("book_details")
        val bookDetailsResponse: List<BookDetailsResponse>
)
