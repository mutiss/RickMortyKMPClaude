package com.mutissx.rickmortykmpclaude.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mutissx.rickmortykmpclaude.data.local.CharacterLocalDataSource
import com.mutissx.rickmortykmpclaude.data.mapper.toDomain
import com.mutissx.rickmortykmpclaude.domain.model.Character

class CharacterDbPagingSource(
    private val localDataSource: CharacterLocalDataSource,
    private val query: String
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val offset = params.key ?: 0
        val items = localDataSource.getCharactersPaged(query, params.loadSize, offset)
        return LoadResult.Page(
            data = items.map { it.toDomain() },
            prevKey = if (offset == 0) null else maxOf(0, offset - params.loadSize),
            nextKey = if (items.size < params.loadSize) null else offset + items.size
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        return maxOf(0, anchorPosition - state.config.pageSize / 2)
    }
}
