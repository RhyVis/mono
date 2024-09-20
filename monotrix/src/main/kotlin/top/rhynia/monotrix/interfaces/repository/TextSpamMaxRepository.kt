package top.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import top.rhynia.monotrix.elements.data.TextSpamMax

interface TextSpamMaxRepository : JpaRepository<TextSpamMax, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM spam_max ORDER BY RAND() LIMIT 1")
    fun findRand(): TextSpamMax?

    @Query(nativeQuery = true, value = "SELECT * FROM spam_max ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<TextSpamMax>
}