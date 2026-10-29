package com.mc.toysimplemonkey.core.di

import com.mc.toysimplemonkey.core.packages.data.repository.SimpleMobileRepositoryImpl
import com.mc.toysimplemonkey.core.packages.data.repository.SimplePhoneSpecRepositoryImpl
import com.mc.toysimplemonkey.core.packages.domain.repository.SimpleMobileRepository
import com.mc.toysimplemonkey.core.packages.domain.repository.SimplePhoneSpecRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSimpleMobileRepository(impl: SimpleMobileRepositoryImpl): SimpleMobileRepository

    @Binds
    abstract fun bindSimplePhoneSpecRepository(impl: SimplePhoneSpecRepositoryImpl): SimplePhoneSpecRepository


}