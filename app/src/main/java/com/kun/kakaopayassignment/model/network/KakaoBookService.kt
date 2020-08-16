package com.kun.kakaopayassignment.model.network

import com.kun.kakaopayassignment.model.data.BookResponse
import com.kun.kakaopayassignment.model.data.Common.Companion.KAKAO_SEARCH_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoBookService {
    companion object {
        const val SEARCH_BOOK_URL = "/v3/search/book"
    }

    @GET(SEARCH_BOOK_URL)
    suspend fun searchBook(
        @Header("Authorization") apiKey: String = KAKAO_SEARCH_API_KEY,
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page : Int,
        @Query("size") size : Int = 50,
        @Query("target") target : String
    ) : Response<BookResponse>

}