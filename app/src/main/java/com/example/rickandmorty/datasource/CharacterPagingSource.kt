package com.example.rickandmorty.datasource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Info
import com.example.rickandmorty.data.remote.Status
import com.example.rickandmorty.domain.usecase.GetAllCharactersUseCase

private const val STARTING_PAGE_INDEX = 1

class CharacterPagingSource constructor(
    private val service: GetAllCharactersUseCase, var name: String?, var status: String?
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX

        return try {

            lateinit var response: List<Character>
            lateinit var info: Info
            var nextKey: Int? = null

            service.invoke(name,status,pageIndex).collect{
                when(it.status){
                    Status.SUCCESS -> {
                        response = it.data!!.results
                        info = it.data.info
                    }
                    Status.ERROR -> it.message
                }

            }

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