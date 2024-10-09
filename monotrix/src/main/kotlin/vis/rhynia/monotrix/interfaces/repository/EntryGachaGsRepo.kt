package vis.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import vis.rhynia.monotrix.elements.data.text.EntryGachaGs

interface EntryGachaGsRepo : JpaRepository<EntryGachaGs, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM gacha_gs ORDER BY RAND() LIMIT 1")
    fun findRand(): EntryGachaGs?

    @Query(nativeQuery = true, value = "SELECT * FROM gacha_gs ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<EntryGachaGs>
}
