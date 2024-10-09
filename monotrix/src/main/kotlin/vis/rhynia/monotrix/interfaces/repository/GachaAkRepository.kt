package vis.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import vis.rhynia.monotrix.elements.data.entity.GachaAk

interface GachaAkRepository : JpaRepository<GachaAk, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM gacha_ak ORDER BY RAND() LIMIT 1")
    fun findRand(): GachaAk?

    @Query(nativeQuery = true, value = "SELECT * FROM gacha_ak ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<GachaAk>
}
