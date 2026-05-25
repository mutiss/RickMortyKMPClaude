package com.mutissx.rickmortykmpclaude.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mutissx.rickmortykmpclaude.data.mapper.toDomain
import com.mutissx.rickmortykmpclaude.data.remote.CharacterApiService
import com.mutissx.rickmortykmpclaude.domain.model.Character

class CharacterPagingSource(
    private val apiService: CharacterApiService,
    private val query: String = ""
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getCharacters(page, query)
            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.info.next == null) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? =
        state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
}
