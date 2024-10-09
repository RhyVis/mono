package vis.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import vis.rhynia.monotrix.elements.data.entity.SpamMax

interface SpamMaxRepository : JpaRepository<SpamMax, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM spam_max ORDER BY RAND() LIMIT 1")
    fun findRand(): SpamMax?

    @Query(nativeQuery = true, value = "SELECT * FROM spam_max ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<SpamMax>
}
