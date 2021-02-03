package com.codinginflow.imagesearchapp.data

import com.codinginflow.imagesearchapp.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

//@Inject takes one of the already made instances and passes it to the constructor without making a new one...
@Singleton  //we only need a single instance of this inside of our entire app and hence we declare this as singleton
class UnsplashRepository @Inject constructor(private val unsplashApi: UnsplashApi) {
}