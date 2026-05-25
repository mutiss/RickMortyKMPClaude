package com.mutissx.rickmortykmpclaude.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mutissx.rickmortykmpclaude.data.local.CharacterLocalDataSource
import com.mutissx.rickmortykmpclaude.data.mapper.toEntity
import com.mutissx.rickmortykmpclaude.data.remote.CharacterApiService
import com.mutissx.rickmortykmpclaude.domain.model.Character

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val apiService: CharacterApiService,
    private val localDataSource: CharacterLocalDataSource
) : RemoteMediator<Int, Character>() {

    override suspend fun initialize(): InitializeAction =
        InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val maxPage = localDataSource.getMaxPage()
                        ?: return MediatorResult.Success(endOfPaginationReached = false)
                    maxPage.toInt() + 1
                }
            }

            val response = apiService.getCharacters(page)
            val endOfPagination = response.info.next == null

            if (loadType == LoadType.REFRESH) {
                localDataSource.deleteAll()
            }
            localDataSource.saveCharacters(response.results.map { it.toEntity(page) })

            MediatorResult.Success(endOfPaginationReached = endOfPagination)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
