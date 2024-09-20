package top.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import top.rhynia.monotrix.elements.data.TextArknights

interface TextArknightsRepository : JpaRepository<TextArknights, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM arknights ORDER BY RAND() LIMIT 1")
    fun findRand(): TextArknights?

    @Query(nativeQuery = true, value = "SELECT * FROM arknights ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<TextArknights>
}