package com.lucascabral.newyorktimesbooks.data

import com.lucascabral.newyorktimesbooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewYorkTimesService {

    @GET("lists.json")
    fun getBooks(
            @Query("api-key") apiKey: String = "BoiGkzKYrH2k8EzWdJWr7t45ztTGBESD",
            @Query("list") list: String = "hardcover-fiction"
    ) : Call<BookBodyResponse>
}