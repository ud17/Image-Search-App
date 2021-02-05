package com.codinginflow.imagesearchapp.di

import com.codinginflow.imagesearchapp.api.UnsplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module             //annotates the object to indicate the we will get our dependencies from here
@InstallIn(ApplicationComponent::class)    //this annotation decides the scope, i.e if application component then this will work unless the app is running
object AppModule {

    @Provides           //indicates that the upcoming function will provide dependency
    @Singleton          //will ensure that only a single instance of this function is created throughout the app
    fun provideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit) : UnsplashApi =
        retrofit.create(UnsplashApi::class.java)

}