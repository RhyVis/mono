package vis.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import vis.rhynia.monotrix.elements.data.text.EntryMemeDinner

interface EntryMemeDinnerRepo : JpaRepository<EntryMemeDinner, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM meme_dinner ORDER BY RAND() LIMIT 1")
    fun findRand(): EntryMemeDinner?

    @Query(nativeQuery = true, value = "SELECT * FROM meme_dinner ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<EntryMemeDinner>
}
