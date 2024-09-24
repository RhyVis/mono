package top.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import top.rhynia.monotrix.elements.data.text.EntrySpamMin

interface EntrySpamMinRepo : JpaRepository<EntrySpamMin, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM spam_min ORDER BY RAND() LIMIT 1")
    fun findRand(): EntrySpamMin?

    @Query(nativeQuery = true, value = "SELECT * FROM spam_min ORDER BY RAND() LIMIT ?1")
    fun findRand(limit: Int): List<EntrySpamMin>
}
