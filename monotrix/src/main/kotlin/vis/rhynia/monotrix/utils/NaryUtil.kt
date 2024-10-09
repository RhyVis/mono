package vis.rhynia.monotrix.utils

import kotlin.math.pow

/**
 * 数组化进制转换的方法
 */
object NaryUtil {
    fun intToNary(
        int: Int,
        radix: Int = 2,
    ): MutableList<Int> {
        if (radix < 2) throw IllegalArgumentException("radix must be at least 2")
        return mutableListOf<Int>().apply {
            var i = int
            while (i > 0) {
                add(0, i % radix)
                i /= radix
            }
        }
    }

    fun naryToInt(
        nary: List<Int>,
        radix: Int = 2,
    ): Int = nary.reversed().mapIndexed { i, n -> n * radix.toDouble().pow(i).toInt() }.sum()
}
