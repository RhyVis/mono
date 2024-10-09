package vis.rhynia.monotrix.interfaces.repository

import org.springframework.data.jpa.repository.JpaRepository
import vis.rhynia.monotrix.elements.data.text.EntryStrSave

interface EntryStrSaveRepo : JpaRepository<EntryStrSave, Long>
