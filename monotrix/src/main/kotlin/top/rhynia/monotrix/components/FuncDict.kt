package top.rhynia.monotrix.components

import com.github.houbb.opencc4j.util.ZhConverterUtil
import top.rhynia.monotrix.elements.ApiResponse
import top.rhynia.monotrix.util.DictAssembly.dictEmojiToHan
import top.rhynia.monotrix.util.DictAssembly.dictHanToEmoji
import top.rhynia.monotrix.util.DictAssembly.dictHanToSpark
import top.rhynia.monotrix.util.DictAssembly.dictSparkToHan
import top.rhynia.monotrix.util.DictAssembly.dictUnicodeDiff
import kotlin.random.Random

object FuncDict {
    fun codex(text: String, type: String = "nmsl", code: Boolean = true): ApiResponse {
        return ApiResponse(codexRaw(text, type, code))
    }

    fun codexRaw(text: String, type: String = "nmsl", code: Boolean = true): String {
        return when (type) {
            "nmsl" -> if (code) codeEmoji(text) else decodeEmoji(text)
            "trad" -> if (code) codeTrad(text) else decodeTrad(text)
            "sprk" -> if (code) codeSpark(text) else decodeSpark(text)
            "unid" -> codeUnicodeDiff(text)
            else -> text
        }
    }

    private fun codeEmoji(text: String): String {
        return replaceRefDictArray(text, dictHanToEmoji)
    }

    private fun decodeEmoji(text: String): String {
        return replaceRefDict(text, dictEmojiToHan)
    }

    private fun codeSpark(text: String): String {
        return replaceRefDict(text, dictHanToSpark)
    }

    private fun decodeSpark(text: String): String {
        return replaceRefDict(text, dictSparkToHan)
    }

    private fun codeUnicodeDiff(text: String): String {
        return replaceRefDict(text, dictUnicodeDiff)
    }

    private fun codeTrad(text: String): String {
        return ZhConverterUtil.toTraditional(text)
    }

    private fun decodeTrad(text: String): String {
        return ZhConverterUtil.toSimple(text)
    }

    private fun replaceRefDictArray(text: String, dict: Map<String, Array<String>>): String {
        val result = StringBuilder()
        for (c in text) {
            val alt = dict[c.toString()]
            if (alt != null) {
                if (alt.size > 1) {
                    result.append(alt[Random.nextInt(alt.size)])
                } else {
                    result.append(alt.first().toString())
                }
            } else {
                result.append(c.toString())
            }
        }
        return result.toString()
    }

    private fun replaceRefDict(text: String, dict: Map<String, String>): String {
        val result = StringBuilder()
        for (c in text) {
            val alt = dict[c.toString()]
            if (alt != null) {
                result.append(alt)
            } else {
                result.append(c.toString())
            }
        }
        return result.toString()
    }
}
