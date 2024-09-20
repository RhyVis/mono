package top.rhynia.monotrix.components

import org.springframework.stereotype.Service
import top.rhynia.monotrix.elements.data.TextArknights
import top.rhynia.monotrix.elements.data.TextGenshin
import top.rhynia.monotrix.elements.data.TextSimpleData
import top.rhynia.monotrix.elements.data.TextSpamMax
import top.rhynia.monotrix.elements.data.TextSpamMin
import top.rhynia.monotrix.elements.web.post.PostSpam
import top.rhynia.monotrix.elements.web.result.PackedResult
import top.rhynia.monotrix.interfaces.data.TextSimpleEntry
import top.rhynia.monotrix.interfaces.repository.TextArknightsRepository
import top.rhynia.monotrix.interfaces.repository.TextGenshinRepository
import top.rhynia.monotrix.interfaces.repository.TextSpamMaxRepository
import top.rhynia.monotrix.interfaces.repository.TextSpamMinRepository

@Service
class FuncSpam(
    private val arknightsRepository: TextArknightsRepository,
    private val genshinRepository: TextGenshinRepository,
    private val spamMinRepository: TextSpamMinRepository,
    private val spamMaxRepository: TextSpamMaxRepository
) {
    fun fetchSpam(type: String, code: String, limit: Int = 1): List<TextSimpleEntry> {
        if (limit > 1) {
            return when (type) {
                "arknights" -> fetchArknights(limit)
                "genshin" -> fetchGenshin(limit)
                "spam_min" -> fetchSpamMin(limit)
                "spam_max" -> fetchSpamMax(limit)
                else -> listOf(TextSimpleData())
            }.map { TextSimpleData(it.id, FuncDict.codexRaw(it.text, code)) }
        } else {
            val result = when (type) {
                "arknights" -> fetchArknights()
                "genshin" -> fetchGenshin()
                "spam_min" -> fetchSpamMin()
                "spam_max" -> fetchSpamMax()
                else -> TextSimpleData()
            }
            result.text = FuncDict.codexRaw(result.text, code)
            return listOf(result)
        }
    }

    fun fetchSpam(post: PostSpam): PackedResult {
        return PackedResult(fetchSpam(post.type, post.code, post.limit))
    }

    private fun fetchArknights(): TextArknights {
        return arknightsRepository.findRand() ?: TextArknights(0, "Empty")
    }

    private fun fetchArknights(limit: Int): List<TextArknights> {
        return arknightsRepository.findRand(limit)
    }

    private fun fetchGenshin(): TextGenshin {
        return genshinRepository.findRand() ?: TextGenshin(0, "Empty")
    }

    private fun fetchGenshin(limit: Int): List<TextGenshin> {
        return genshinRepository.findRand(limit)
    }

    private fun fetchSpamMin(): TextSpamMin {
        return spamMinRepository.findRand() ?: TextSpamMin(0, "Empty")
    }

    private fun fetchSpamMin(limit: Int): List<TextSpamMin> {
        return spamMinRepository.findRand(limit)
    }

    private fun fetchSpamMax(): TextSpamMax {
        return spamMaxRepository.findRand() ?: TextSpamMax(0, "Empty")
    }

    private fun fetchSpamMax(limit: Int): List<TextSpamMax> {
        return spamMaxRepository.findRand(limit)
    }
}