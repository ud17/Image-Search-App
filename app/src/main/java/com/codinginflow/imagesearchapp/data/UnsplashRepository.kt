package com.codinginflow.imagesearchapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.codinginflow.imagesearchapp.api.UnsplashApi
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

//@Inject takes one of the already made instances and passes it to the constructor without making a new one...
@Singleton  //we only need a single instance of this inside of our entire app and hence we declare this as singleton
class UnsplashRepository @Inject constructor(private val unsplashApi: UnsplashApi) {
    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false                  //enabling this can place placeholders of object in advance
            ),
            pagingSourceFactory = {
                UnsplashPagingSource(
                    unsplashApi,
                    query
                )
            }
        ).liveData                       //converting it into a stream of live data so that our fragment/activity can observer live data, live updates etc
                                        //can also opt for 'flow' instead of 'live data' but it has some advanced features that we don't require as of now
}