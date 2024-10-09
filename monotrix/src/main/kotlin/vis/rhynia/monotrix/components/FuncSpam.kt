package vis.rhynia.monotrix.components

import org.springframework.stereotype.Service
import vis.rhynia.monotrix.elements.data.text.EntryGachaAk
import vis.rhynia.monotrix.elements.data.text.EntryGachaGs
import vis.rhynia.monotrix.elements.data.text.EntryMemeAcgn
import vis.rhynia.monotrix.elements.data.text.EntryMemeDinner
import vis.rhynia.monotrix.elements.data.text.EntrySimple
import vis.rhynia.monotrix.elements.data.text.EntrySpamMax
import vis.rhynia.monotrix.elements.data.text.EntrySpamMin
import vis.rhynia.monotrix.elements.web.ApiResponse
import vis.rhynia.monotrix.elements.web.post.PostSpam
import vis.rhynia.monotrix.interfaces.data.EntryText
import vis.rhynia.monotrix.interfaces.repository.EntryGachaAkRepo
import vis.rhynia.monotrix.interfaces.repository.EntryGachaGsRepo
import vis.rhynia.monotrix.interfaces.repository.EntryMemeAcgnRepo
import vis.rhynia.monotrix.interfaces.repository.EntryMemeDinnerRepo
import vis.rhynia.monotrix.interfaces.repository.EntrySpamMaxRepo
import vis.rhynia.monotrix.interfaces.repository.EntrySpamMinRepo

@Service
class FuncSpam(
    private val repoAk: EntryGachaAkRepo,
    private val repoGs: EntryGachaGsRepo,
    private val repoSpamMin: EntrySpamMinRepo,
    private val repoSpamMax: EntrySpamMaxRepo,
    private val repoAcgn: EntryMemeAcgnRepo,
    private val repoDinner: EntryMemeDinnerRepo,
    private val funcCodex: FuncCodex,
) {
    fun fetchSpam(
        type: String,
        code: String,
        limit: Int = 1,
    ): List<EntryText> {
        if (limit > 1) {
            return when (type) {
                "arknights" -> fetchArknights(limit)
                "genshin" -> fetchGenshin(limit)
                "spam_min" -> fetchSpamMin(limit)
                "spam_max" -> fetchSpamMax(limit)
                "acgn" -> fetchAcgn(limit)
                "dinner" -> fetchDinner(limit)
                else -> listOf(EntrySimple())
            }.map { EntrySimple(it.id, funcCodex.codexRaw(it.text, code)) }
        } else {
            val result =
                when (type) {
                    "arknights" -> fetchArknights()
                    "genshin" -> fetchGenshin()
                    "spam_min" -> fetchSpamMin()
                    "spam_max" -> fetchSpamMax()
                    "acgn" -> fetchAcgn()
                    "dinner" -> fetchDinner()
                    else -> EntrySimple()
                }
            result.text = funcCodex.codexRaw(result.text, code)
            return listOf(result)
        }
    }

    fun fetchSpam(post: PostSpam): ApiResponse = ApiResponse(fetchSpam(post.type, post.code, post.limit))

    private fun fetchArknights(): EntryGachaAk = repoAk.findRand() ?: EntryGachaAk(0, "Empty")

    private fun fetchArknights(limit: Int): List<EntryGachaAk> = repoAk.findRand(limit)

    private fun fetchGenshin(): EntryGachaGs = repoGs.findRand() ?: EntryGachaGs(0, "Empty")

    private fun fetchGenshin(limit: Int): List<EntryGachaGs> = repoGs.findRand(limit)

    private fun fetchSpamMin(): EntrySpamMin = repoSpamMin.findRand() ?: EntrySpamMin(0, "Empty")

    private fun fetchSpamMin(limit: Int): List<EntrySpamMin> = repoSpamMin.findRand(limit)

    private fun fetchSpamMax(): EntrySpamMax = repoSpamMax.findRand() ?: EntrySpamMax(0, "Empty")

    private fun fetchSpamMax(limit: Int): List<EntrySpamMax> = repoSpamMax.findRand(limit)

    private fun fetchAcgn(): EntryMemeAcgn = repoAcgn.findRand() ?: EntryMemeAcgn(0, "Empty")

    private fun fetchAcgn(limit: Int): List<EntryMemeAcgn> = repoAcgn.findRand(limit)

    private fun fetchDinner(): EntryMemeDinner = repoDinner.findRand() ?: EntryMemeDinner(0, "Empty")

    private fun fetchDinner(limit: Int): List<EntryMemeDinner> = repoDinner.findRand(limit)
}
