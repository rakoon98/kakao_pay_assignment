package com.kun.kakaopayassignment.model.repositories

import com.kun.kakaopayassignment.model.data.BookResponse
import com.kun.kakaopayassignment.model.network.KakaoBookService
import com.kun.kakaopayassignment.model.network.ResultConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SearchRepository @Inject constructor(
    private val kakaoBookService: KakaoBookService
) : SearchRepositoryImpl {

    override suspend fun searchBook(kApiKey : String, query: String, sort: String, page: Int, size: Int, target: String)
    : ResultConverter<BookResponse> = suspendCoroutine { continuation ->
        CoroutineScope(Dispatchers.IO).launch {
            try {
                kakaoBookService.searchBook(kApiKey, query,sort,page,size,target).run {
                    if(code() in 200..299){
                        continuation.resume(ResultConverter.Success(body()!!))
                    } else {
                        throw HttpException(this)
                    }
                }
            }catch (e:Exception){
                continuation.resume(ResultConverter.Error(e))
            }
        }
    }

}