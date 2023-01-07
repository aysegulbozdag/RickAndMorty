package com.example.rickandmorty.datasource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Info
import com.example.rickandmorty.data.network.Status
import com.example.rickandmorty.domain.GetCharacterUseCase
import kotlinx.coroutines.flow.onEach

private const val STARTING_PAGE_INDEX = 1

class CharacterPagingSource constructor(
    private val service: GetCharacterUseCase, var name: String?, var status: String?
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX

        return try {
            lateinit var response: List<Character>
            lateinit var info: Info
            service().onEach { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        response = result.data!!.results
                        info = result.data.info
                    }
                }
            }

            //  val response = service.getAllCharacters(name,status,pageIndex)
            //   val character=response.results
            var nextKey: Int? = null

            if (info.next != null) {
                val uri = Uri.parse(info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextKey = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = response,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }}
}