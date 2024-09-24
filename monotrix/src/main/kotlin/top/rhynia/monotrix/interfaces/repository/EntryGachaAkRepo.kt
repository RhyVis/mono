package top.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import top.rhynia.monotrix.elements.data.text.EntryGachaAk

interface EntryGachaAkRepo : JpaRepository<EntryGachaAk, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM gacha_ak ORDER BY RAND() LIMIT 1")
    fun findRand(): EntryGachaAk?

    @Query(nativeQuery = true, value = "SELECT * FROM gacha_ak ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<EntryGachaAk>
}
