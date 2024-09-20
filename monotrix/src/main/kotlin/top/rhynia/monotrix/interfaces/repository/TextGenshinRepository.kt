package top.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import top.rhynia.monotrix.elements.data.TextGenshin

interface TextGenshinRepository : JpaRepository<TextGenshin, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM genshin ORDER BY RAND() LIMIT 1")
    fun findRand(): TextGenshin?

    @Query(nativeQuery = true, value = "SELECT * FROM genshin ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<TextGenshin>
}