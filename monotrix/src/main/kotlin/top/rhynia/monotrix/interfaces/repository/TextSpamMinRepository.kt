package top.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import top.rhynia.monotrix.elements.data.TextSpamMin

interface TextSpamMinRepository : JpaRepository<TextSpamMin, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM spam_min ORDER BY RAND() LIMIT 1")
    fun findRand(): TextSpamMin?

    @Query(nativeQuery = true, value = "SELECT * FROM spam_min ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<TextSpamMin>
}