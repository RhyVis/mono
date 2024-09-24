package top.rhynia.monotrix.components

import org.springframework.stereotype.Service
import top.rhynia.monotrix.elements.data.text.EntryStrNav
import top.rhynia.monotrix.elements.web.result.PackedResult
import top.rhynia.monotrix.interfaces.repository.EntryStrNavRepo

@Service
class FuncNav(private val repo: EntryStrNavRepo) {
    fun getIndex(id: Long): PackedResult {
        val data = getIndexData(id)
        return if (id >= 0) PackedResult(data) else PackedResult(-1, -1)
    }

    fun getIndexList(): PackedResult {
        val data = getIndexListData()
        return if (data.isNotEmpty()) PackedResult(data) else PackedResult(-1, -1)
    }

    private fun getIndexData(id: Long): EntryStrNav {
        return repo.findById(id).orElse(EntryStrNav(-1, "", "", ""))
    }

    private fun getIndexListData(): List<EntryStrNav> {
        return repo.findAll()
    }
}
