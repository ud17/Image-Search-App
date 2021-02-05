package com.codinginflow.imagesearchapp.data

import androidx.paging.PagingSource
import com.codinginflow.imagesearchapp.api.UnsplashApi
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_INDEX = 1

class UnsplashPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query : String
) : PagingSource<Int , UnsplashPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: STARTING_INDEX  //params.key is basically the page number.
                                        //When loading the first page params.key is null hence using elvis operator to assign value as 1 in case of null
        return try {
            val response = unsplashApi.searchPhotos(query , position , params.loadSize )
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == STARTING_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception : IOException) {
            LoadResult.Error(exception)
        } catch (exception : HttpException) {
            LoadResult.Error(exception)
        }
    }
}