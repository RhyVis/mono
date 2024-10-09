package vis.rhynia.monotrix.components

import com.github.houbb.opencc4j.util.ZhConverterUtil
import org.springframework.stereotype.Component
import vis.rhynia.monotrix.elements.web.ApiResponse
import vis.rhynia.monotrix.enums.CodexType
import vis.rhynia.monotrix.enums.CodexType.NMSL
import vis.rhynia.monotrix.enums.CodexType.SPRK
import vis.rhynia.monotrix.enums.CodexType.TRAD
import vis.rhynia.monotrix.enums.CodexType.UNID
import vis.rhynia.monotrix.extensions.fillRand
import vis.rhynia.monotrix.extensions.fillZero
import vis.rhynia.monotrix.interfaces.Log
import vis.rhynia.monotrix.utils.DictAssembly.dictEmojiToHan
import vis.rhynia.monotrix.utils.DictAssembly.dictHanToEmoji
import vis.rhynia.monotrix.utils.DictAssembly.dictHanToSpark
import vis.rhynia.monotrix.utils.DictAssembly.dictSparkToHan
import vis.rhynia.monotrix.utils.DictAssembly.dictUnicodeDiff
import vis.rhynia.monotrix.utils.NaryUtil.intToNary
import vis.rhynia.monotrix.utils.NaryUtil.naryToInt
import vis.rhynia.monotrix.utils.StrUtil
import kotlin.random.Random

@Log
@Component
class FuncCodex {
    fun codex(
        text: String,
        type: String = "nmsl",
        code: Boolean = true,
    ): ApiResponse = ApiResponse(codexRaw(text, type, code))

    fun codexRaw(
        text: String,
        type: String = "nmsl",
        code: Boolean = true,
    ): String {
        val t = CodexType.tryGetValue(type.ifBlank { "none" }.uppercase())
        return when (t) {
            NMSL -> if (code) encodeEmoji(text) else decodeEmoji(text)
            TRAD -> if (code) encodeTrad(text) else decodeTrad(text)
            SPRK -> if (code) encodeSpark(text) else decodeSpark(text)
            UNID -> encodeUnicodeDiff(text)
            else -> text
        }
    }

    companion object {
        private fun encodeEmoji(text: String): String = StrUtil.replaceRefArrMap(text, dictHanToEmoji)

        private fun decodeEmoji(text: String): String = StrUtil.replaceRefMap(text, dictEmojiToHan)

        private fun encodeSpark(text: String): String = StrUtil.replaceRefMap(text, dictHanToSpark)

        private fun decodeSpark(text: String): String = StrUtil.replaceRefMap(text, dictSparkToHan)

        private fun encodeUnicodeDiff(text: String): String = StrUtil.replaceRefMap(text, dictUnicodeDiff)

        private fun encodeTrad(text: String): String = ZhConverterUtil.toTraditional(text)

        private fun decodeTrad(text: String): String = ZhConverterUtil.toSimple(text)

        private fun generateKeyShift(
            key: String,
            length: Int,
        ): List<Int> =
            key.ifBlank { null }?.run {
                val nArr = key.toCharArray().map { it.code }
                mutableListOf<Int>().apply {
                    for (i in 0 until length) {
                        add(nArr[i % nArr.size])
                    }
                }
            } ?: listOf()

        private fun encodeNary(
            text: String,
            key: String,
            baseShift: Int,
            ref: Map<Int, String>,
        ): String {
            val radix = ref.size
            val keyShiftArr = generateKeyShift(key, text.length)
            // 位构造 = 2位随机偏移记录 + 4位长度值 + 16位字符码 = randShiftNaryArr + sizeNaryArr + textNaryArr
            val ls =
                text
                    .mapIndexed { i, c ->
                        val randShift = Random.nextInt(4)
                        val randShiftNaryArr = intToNary(randShift, radix).fillZero(2)
                        val finalShift = baseShift + randShift + keyShiftArr.getOrElse(i) { 0 }
                        val textNaryArr = intToNary(c.code + finalShift, radix)
                        val sizeNaryArr = intToNary(textNaryArr.size, radix).fillZero(4)
                        textNaryArr.fillRand(16, radix)
                        randShiftNaryArr + sizeNaryArr + textNaryArr
                    }.flatten()
            return StrUtil.replaceRefMap(ls, ref)
        }

        private fun decodeNary(
            encoded: String,
            key: String,
            baseShift: Int,
            ref: Map<Int, String>,
        ): String {
            val reverseRef = ref.map { (k, v) -> v to k }.toMap()
            val radix = ref.size
            val keyShiftArr = generateKeyShift(key, encoded.length)
            return encoded
                .toCharArray()
                .toList()
                .chunked(22)
                .mapIndexed { i, chunk ->
                    chunk.map { reverseRef.getOrElse(it.toString()) { 0 } }.run {
                        val randShift = naryToInt(slice(0..1), radix)
                        val finalShift = baseShift + randShift + keyShiftArr.getOrElse(i) { 0 }
                        val length = naryToInt(slice(2..5), radix)
                        val code = naryToInt(slice(22 - length..21), radix) - finalShift
                        code.toChar()
                    }
                }.toString()
        }
    }
}
