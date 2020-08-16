package com.kun.kakaopayassignment.model.network

sealed class ResultConverter<out T> {
    data class Success<out T>(val data: T) : ResultConverter<T>()
    data class Error(val exception: Exception) : ResultConverter<Nothing>()
}
