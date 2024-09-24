package top.rhynia.monotrix.components

import org.springframework.stereotype.Service
import top.rhynia.monotrix.elements.data.text.EntryStrSave
import top.rhynia.monotrix.interfaces.repository.EntryStrSaveRepo

@Service
class FuncSave(private val repo: EntryStrSaveRepo) {
    fun queryStr(id: Long): EntryStrSave {
        return repo.findById(id).orElse(EntryStrSave(-1, "", ""))
    }

    fun updateStr(id: Long, text: String, note: String = "") {
        repo.findById(id).ifPresentOrElse(
            { it.text = text; it.note = note; repo.save(it) },
            { repo.save(EntryStrSave(id, text, note)) }
        )
    }

    fun deleteStr(id: Long) {
        repo.deleteById(id)
    }
}
