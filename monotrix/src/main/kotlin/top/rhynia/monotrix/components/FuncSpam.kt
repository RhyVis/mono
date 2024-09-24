package top.rhynia.monotrix.components

import org.springframework.stereotype.Service
import top.rhynia.monotrix.elements.data.text.EntryGachaAk
import top.rhynia.monotrix.elements.data.text.EntryGachaGs
import top.rhynia.monotrix.elements.data.text.EntryMemeAcgn
import top.rhynia.monotrix.elements.data.text.EntryMemeDinner
import top.rhynia.monotrix.elements.data.text.EntrySimple
import top.rhynia.monotrix.elements.data.text.EntrySpamMax
import top.rhynia.monotrix.elements.data.text.EntrySpamMin
import top.rhynia.monotrix.elements.web.post.PostSpam
import top.rhynia.monotrix.elements.web.result.PackedResult
import top.rhynia.monotrix.interfaces.data.EntryText
import top.rhynia.monotrix.interfaces.repository.EntryGachaAkRepo
import top.rhynia.monotrix.interfaces.repository.EntryGachaGsRepo
import top.rhynia.monotrix.interfaces.repository.EntryMemeAcgnRepo
import top.rhynia.monotrix.interfaces.repository.EntryMemeDinnerRepo
import top.rhynia.monotrix.interfaces.repository.EntrySpamMaxRepo
import top.rhynia.monotrix.interfaces.repository.EntrySpamMinRepo

@Service
class FuncSpam(
    private val repoAk: EntryGachaAkRepo,
    private val repoGs: EntryGachaGsRepo,
    private val repoSpamMin: EntrySpamMinRepo,
    private val repoSpamMax: EntrySpamMaxRepo,
    private val repoAcgn: EntryMemeAcgnRepo,
    private val repoDinner: EntryMemeDinnerRepo,
) {
    fun fetchSpam(type: String, code: String, limit: Int = 1): List<EntryText> {
        if (limit > 1) {
            return when (type) {
                "arknights" -> fetchArknights(limit)
                "genshin" -> fetchGenshin(limit)
                "spam_min" -> fetchSpamMin(limit)
                "spam_max" -> fetchSpamMax(limit)
                "acgn" -> fetchAcgn(limit)
                "dinner" -> fetchDinner(limit)
                else -> listOf(EntrySimple())
            }.map { EntrySimple(it.id, FuncDict.codexRaw(it.text, code)) }
        } else {
            val result = when (type) {
                "arknights" -> fetchArknights()
                "genshin" -> fetchGenshin()
                "spam_min" -> fetchSpamMin()
                "spam_max" -> fetchSpamMax()
                "acgn" -> fetchAcgn()
                "dinner" -> fetchDinner()
                else -> EntrySimple()
            }
            result.text = FuncDict.codexRaw(result.text, code)
            return listOf(result)
        }
    }

    fun fetchSpam(post: PostSpam): PackedResult {
        return PackedResult(fetchSpam(post.type, post.code, post.limit))
    }

    private fun fetchArknights(): EntryGachaAk {
        return repoAk.findRand() ?: EntryGachaAk(0, "Empty")
    }

    private fun fetchArknights(limit: Int): List<EntryGachaAk> {
        return repoAk.findRand(limit)
    }

    private fun fetchGenshin(): EntryGachaGs {
        return repoGs.findRand() ?: EntryGachaGs(0, "Empty")
    }

    private fun fetchGenshin(limit: Int): List<EntryGachaGs> {
        return repoGs.findRand(limit)
    }

    private fun fetchSpamMin(): EntrySpamMin {
        return repoSpamMin.findRand() ?: EntrySpamMin(0, "Empty")
    }

    private fun fetchSpamMin(limit: Int): List<EntrySpamMin> {
        return repoSpamMin.findRand(limit)
    }

    private fun fetchSpamMax(): EntrySpamMax {
        return repoSpamMax.findRand() ?: EntrySpamMax(0, "Empty")
    }

    private fun fetchSpamMax(limit: Int): List<EntrySpamMax> {
        return repoSpamMax.findRand(limit)
    }

    private fun fetchAcgn(): EntryMemeAcgn {
        return repoAcgn.findRand() ?: EntryMemeAcgn(0, "Empty")
    }

    private fun fetchAcgn(limit: Int): List<EntryMemeAcgn> {
        return repoAcgn.findRand(limit)
    }

    private fun fetchDinner(): EntryMemeDinner {
        return repoDinner.findRand() ?: EntryMemeDinner(0, "Empty")
    }

    private fun fetchDinner(limit: Int): List<EntryMemeDinner> {
        return repoDinner.findRand(limit)
    }
}
