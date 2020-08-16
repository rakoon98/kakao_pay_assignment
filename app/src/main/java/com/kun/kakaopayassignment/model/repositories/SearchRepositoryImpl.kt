package com.kun.kakaopayassignment.model.repositories

import com.kun.kakaopayassignment.BuildConfig
import com.kun.kakaopayassignment.model.data.BookResponse
import com.kun.kakaopayassignment.model.data.Common
import com.kun.kakaopayassignment.model.network.ResultConverter
import retrofit2.http.Query

interface SearchRepositoryImpl  {
    /**
     *  책 검색
     */
    suspend fun searchBook(
        kApiKey : String = Common.KAKAO_SEARCH_API_KEY,
        query : String,
        sort : String,
        page : Int,
        size : Int = 50,
        target : String
    ) : ResultConverter<BookResponse>

}