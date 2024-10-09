package vis.rhynia.monotrix.components

import org.springframework.stereotype.Service
import vis.rhynia.monotrix.elements.data.entity.StrNav
import vis.rhynia.monotrix.elements.web.ApiResponse
import vis.rhynia.monotrix.interfaces.repository.StrNavRepository

@Service
class FuncNav(
    private val repo: StrNavRepository,
) {
    fun getIndex(id: Long): ApiResponse {
        val data = getIndexData(id)
        return if (id >= 0) ApiResponse(data) else ApiResponse(-1, -1)
    }

    fun getIndexList(): ApiResponse {
        val data = getIndexListData()
        return if (data.isNotEmpty()) ApiResponse(data) else ApiResponse(-1, -1)
    }

    private fun getIndexData(id: Long): StrNav = repo.findById(id).orElse(StrNav(-1, "", "", ""))

    private fun getIndexListData(): List<StrNav> = repo.findAll()
}
