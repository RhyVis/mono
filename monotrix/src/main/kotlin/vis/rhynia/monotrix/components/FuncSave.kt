package vis.rhynia.monotrix.components

import org.springframework.stereotype.Service
import vis.rhynia.monotrix.elements.data.entity.StrSave
import vis.rhynia.monotrix.interfaces.repository.StrSaveRepository

@Service
class FuncSave(
    private val repo: StrSaveRepository,
) {
    fun queryStr(id: Long): StrSave = repo.findById(id).orElse(StrSave(-1, "", ""))

    fun updateStr(
        id: Long,
        text: String,
        note: String = "",
    ) {
        repo.findById(id).ifPresentOrElse(
            {
                it.text = text
                it.note = note
                repo.save(it)
            },
            { repo.save(StrSave(id, text, note)) },
        )
    }

    fun deleteStr(id: Long) {
        repo.deleteById(id)
    }
}
