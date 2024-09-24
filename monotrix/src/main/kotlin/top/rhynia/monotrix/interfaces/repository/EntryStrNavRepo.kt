package top.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import top.rhynia.monotrix.elements.data.text.EntryStrNav

interface EntryStrNavRepo : JpaRepository<EntryStrNav, Long>
