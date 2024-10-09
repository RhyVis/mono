package vis.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import vis.rhynia.monotrix.elements.data.text.EntryMemeAcgn

interface EntryMemeAcgnRepo : JpaRepository<EntryMemeAcgn, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM meme_acgn ORDER BY RAND() LIMIT 1")
    fun findRand(): EntryMemeAcgn?

    @Query(nativeQuery = true, value = "SELECT * FROM meme_acgn ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<EntryMemeAcgn>
}
