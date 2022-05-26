package com.example.rickandmorty

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.network.RickAndMortyApiService
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.max

private const val TMDB_STARTING_PAGE_INDEX = 1

class CharacterPagingSource(private val service: RickAndMortyApiService):PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
      return state.anchorPosition?.let { anchorPosition ->
         state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
             ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }}


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX

        return try {
            val response = service.getAllCharacters(
                page = pageIndex
            )
            val pagedResponse = response.body()
            val character=pagedResponse?.results
            var nextKey: Int? = null
            if (pagedResponse?.pageInfo?.next != null) {
                val uri = Uri.parse(pagedResponse.pageInfo.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextKey = nextPageQuery?.toInt()
            }
            LoadResult.Page(
                data = character.orEmpty(),
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}