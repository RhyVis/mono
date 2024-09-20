package top.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import top.rhynia.monotrix.elements.data.TextLongStorage

interface TextLongStorageRepository : JpaRepository<TextLongStorage, Long>
