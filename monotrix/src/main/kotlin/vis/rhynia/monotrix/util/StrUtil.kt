package vis.rhynia.monotrix.util

import kotlin.random.Random

object StrUtil {
    fun replaceRefMap(
        text: String,
        ref: Map<String, String>,
    ): String = text.map { ref[it.toString()] ?: it }.joinToString("")

    fun replaceRefArrMap(
        text: String,
        ref: Map<String, Array<String>>,
    ): String =
        text
            .map {
                ref[it.toString()]?.run { if (size > 1) this[Random.nextInt(size)] else this[0] } ?: it
            }.joinToString("")

    fun replaceRefArr(
        intList: List<Int>,
        ref: Array<String>,
    ): String =
        intList.joinToString("") {
            if (it < ref.size) ref[it] else it.toString()
        }

    fun replaceRefMap(
        intList: List<Int>,
        ref: Map<Int, String>,
    ): String = intList.joinToString("") { ref.getOrElse(it) { it.toString() } }
}
