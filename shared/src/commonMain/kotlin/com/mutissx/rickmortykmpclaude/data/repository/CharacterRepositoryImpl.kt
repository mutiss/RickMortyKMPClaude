package com.mutissx.rickmortykmpclaude.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mutissx.rickmortykmpclaude.data.local.CharacterLocalDataSource
import com.mutissx.rickmortykmpclaude.data.mapper.toDomain
import com.mutissx.rickmortykmpclaude.data.paging.CharacterDbPagingSource
import com.mutissx.rickmortykmpclaude.data.paging.CharacterPagingSource
import com.mutissx.rickmortykmpclaude.data.paging.CharacterRemoteMediator
import com.mutissx.rickmortykmpclaude.data.remote.CharacterApiService
import com.mutissx.rickmortykmpclaude.domain.model.Character
import com.mutissx.rickmortykmpclaude.domain.model.Episode
import com.mutissx.rickmortykmpclaude.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class CharacterRepositoryImpl(
    private val apiService: CharacterApiService,
    private val localDataSource: CharacterLocalDataSource
) : CharacterRepository {

    override fun getCharactersPagingData(query: String): Flow<PagingData<Character>> =
        if (query.isBlank()) {
            Pager(
                config = PagingConfig(pageSize = 20, prefetchDistance = 5, enablePlaceholders = false),
                remoteMediator = CharacterRemoteMediator(apiService, localDataSource),
                pagingSourceFactory = { CharacterDbPagingSource(localDataSource, "") }
            ).flow
        } else {
            Pager(
                config = PagingConfig(pageSize = 20, prefetchDistance = 3, enablePlaceholders = false),
                pagingSourceFactory = { CharacterPagingSource(apiService, query) }
            ).flow
        }

    override suspend fun getCharacter(id: Int): Result<Character> {
        return try {
            Result.success(apiService.getCharacter(id).toDomain())
        } catch (networkError: Exception) {
            val cached = localDataSource.getCharacterById(id)
            if (cached != null) {
                Result.success(cached.toDomain())
            } else {
                Result.failure(networkError)
            }
        }
    }

    override suspend fun getEpisodes(urls: List<String>): Result<List<Episode>> = runCatching {
        val ids = urls.mapNotNull { url -> url.substringAfterLast("/").toIntOrNull() }
        apiService.getEpisodes(ids).map { it.toDomain() }
    }
}
