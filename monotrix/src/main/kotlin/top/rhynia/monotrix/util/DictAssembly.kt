package top.rhynia.monotrix.util

import cn.hutool.core.io.resource.ResourceUtil
import top.rhynia.monotrix.interfaces.Log
import top.rhynia.monotrix.interfaces.Log.Companion.log

@Log
object DictAssembly {
    lateinit var dictHanToEmoji: Map<String, Array<String>>
    lateinit var dictEmojiToHan: Map<String, String>
    lateinit var dictHanToSpark: Map<String, String>
    lateinit var dictSparkToHan: Map<String, String>
    lateinit var dictPinyinToEmoji: Map<String, String>
    lateinit var dictUnicodeDiff: Map<String, String>

    fun init() {
        val rawHanToEmoji = ResourceUtil.readUtf8Str("conf/codex/dict_han_emoji.json")
        val rawEmojiToHan = ResourceUtil.readUtf8Str("conf/codex/dict_emoji_han.json")
        val rawHanToSpark = ResourceUtil.readUtf8Str("conf/codex/dict_han_spark.json")
        val rawSparkToHan = ResourceUtil.readUtf8Str("conf/codex/dict_spark_han.json")
        val rawPinyinToEmoji = ResourceUtil.readUtf8Str("conf/codex/dict_py_emoji.json")
        val rawUnicodeDiff = ResourceUtil.readUtf8Str("conf/codex/unicode_diff.json")

        dictHanToEmoji = LoaderUtil.jsonToArrayMap(rawHanToEmoji)
        dictEmojiToHan = LoaderUtil.jsonToStringMap(rawEmojiToHan)
        dictHanToSpark = LoaderUtil.jsonToStringMap(rawHanToSpark)
        dictSparkToHan = LoaderUtil.jsonToStringMap(rawSparkToHan)
        dictPinyinToEmoji = LoaderUtil.jsonToStringMap(rawPinyinToEmoji)
        dictUnicodeDiff = LoaderUtil.jsonToStringMap(rawUnicodeDiff)

        log.info("Loaded Dict:")
        log.info(" > h2e ${dictHanToEmoji.keys.size}")
        log.info(" > e2h ${dictEmojiToHan.keys.size}")
        log.info(" > h2s ${dictHanToSpark.keys.size}")
        log.info(" > s2h ${dictSparkToHan.keys.size}")
        log.info(" > p2e ${dictPinyinToEmoji.keys.size}")
        log.info(" > ud ${dictUnicodeDiff.keys.size}")
    }
}