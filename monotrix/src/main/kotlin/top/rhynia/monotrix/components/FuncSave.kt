package top.rhynia.monotrix.components

import org.springframework.stereotype.Service
import top.rhynia.monotrix.elements.data.TextLongStorage
import top.rhynia.monotrix.interfaces.repository.TextLongStorageRepository

@Service
class FuncSave(private val repo: TextLongStorageRepository) {
    fun queryStr(id: Long): TextLongStorage {
        return repo.findById(id).orElse(TextLongStorage(-1, "", ""))
    }

    fun updateStr(id: Long, text: String, note: String = "") {
        repo.findById(id).ifPresentOrElse(
            { it.text = text; it.note = note; repo.save(it) },
            { repo.save(TextLongStorage(id, text, note)) }
        )
    }

    fun deleteStr(id: Long) {
        repo.deleteById(id)
    }
}
