package vis.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import vis.rhynia.monotrix.elements.data.text.EntrySpamMax

interface EntrySpamMaxRepo : JpaRepository<EntrySpamMax, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM spam_max ORDER BY RAND() LIMIT 1")
    fun findRand(): EntrySpamMax?

    @Query(nativeQuery = true, value = "SELECT * FROM spam_max ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<EntrySpamMax>
}
