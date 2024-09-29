package top.rhynia.monotrix.components

import org.springframework.stereotype.Service
import top.rhynia.monotrix.elements.data.text.EntryStrNav
import top.rhynia.monotrix.elements.web.ApiResponse
import top.rhynia.monotrix.interfaces.repository.EntryStrNavRepo

@Service
class FuncNav(private val repo: EntryStrNavRepo) {
    fun getIndex(id: Long): ApiResponse {
        val data = getIndexData(id)
        return if (id >= 0) ApiResponse(data) else ApiResponse(-1, -1)
    }

    fun getIndexList(): ApiResponse {
        val data = getIndexListData()
        return if (data.isNotEmpty()) ApiResponse(data) else ApiResponse(-1, -1)
    }

    private fun getIndexData(id: Long): EntryStrNav {
        return repo.findById(id).orElse(EntryStrNav(-1, "", "", ""))
    }

    private fun getIndexListData(): List<EntryStrNav> {
        return repo.findAll()
    }
}
