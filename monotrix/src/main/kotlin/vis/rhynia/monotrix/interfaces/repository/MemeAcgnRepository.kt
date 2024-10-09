package vis.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import vis.rhynia.monotrix.elements.data.entity.MemeAcgn

interface MemeAcgnRepository : JpaRepository<MemeAcgn, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM meme_acgn ORDER BY RAND() LIMIT 1")
    fun findRand(): MemeAcgn?

    @Query(nativeQuery = true, value = "SELECT * FROM meme_acgn ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<MemeAcgn>
}
