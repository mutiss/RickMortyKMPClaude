package com.mutissx.rickmortykmpclaude.data.local

import com.mutissx.rickmortykmpclaude.db.CharacterDatabase
import com.mutissx.rickmortykmpclaude.db.CharacterEntity

class CharacterLocalDataSource(private val database: CharacterDatabase) {

    fun getCharactersPaged(query: String, limit: Int, offset: Int): List<CharacterEntity> =
        database.characterQueries.selectPaged(
            query = query,
            limit = limit.toLong(),
            offset = offset.toLong()
        ).executeAsList()

    fun countAll(query: String): Long =
        database.characterQueries.countAll(query).executeAsOne()

    fun getMaxPage(): Long? =
        database.characterQueries.getMaxPage().executeAsOne().MAX

    fun getCharacterById(id: Int): CharacterEntity? =
        database.characterQueries.selectById(id.toLong()).executeAsOneOrNull()

    fun saveCharacters(entities: List<CharacterEntity>) {
        database.characterQueries.transaction {
            entities.forEach { entity ->
                database.characterQueries.insertCharacter(
                    id = entity.id,
                    name = entity.name,
                    imageUrl = entity.imageUrl,
                    status = entity.status,
                    species = entity.species,
                    gender = entity.gender,
                    originName = entity.originName,
                    locationName = entity.locationName,
                    episodeUrls = entity.episodeUrls,
                    page = entity.page
                )
            }
        }
    }

    fun deleteAll() {
        database.characterQueries.deleteAll()
    }
}
