package com.mc.toysimplemonkey.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true   // 모르는 키가 와도 에러 안 남?
        coerceInputValues = true   // null이 와도 기본값으로 대체?
    }
}