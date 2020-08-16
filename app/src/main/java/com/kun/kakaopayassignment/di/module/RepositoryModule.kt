package com.kun.kakaopayassignment.di.module

import android.app.Application
import com.kun.kakaopayassignment.model.network.KakaoBookService
import com.kun.kakaopayassignment.model.repositories.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSearchRepository(
        kakaoBookService: KakaoBookService
    ) : SearchRepository = SearchRepository(kakaoBookService)

}