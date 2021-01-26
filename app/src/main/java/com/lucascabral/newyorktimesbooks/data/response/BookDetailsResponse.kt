package com.lucascabral.newyorktimesbooks.data.response

import com.google.gson.annotations.SerializedName
import com.lucascabral.newyorktimesbooks.data.model.Book

data class BookDetailsResponse(
        @SerializedName("title")
        val title: String,
        @SerializedName("author")
        val author: String,
        @SerializedName("description")
        val description: String
) {
        fun getBookModel() = Book(
                title = this.title,
                author = this.author,
                description = this.description
        )
}
