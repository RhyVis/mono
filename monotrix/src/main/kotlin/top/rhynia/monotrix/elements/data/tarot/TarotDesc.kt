package top.rhynia.monotrix.elements.data.tarot

import kotlinx.serialization.Serializable

@Serializable
data class TarotDesc(
    val upright: String,
    val reverse: String,
    val desc: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TarotDesc

        if (upright != other.upright) return false
        if (reverse != other.reverse) return false
        if (!desc.contentEquals(other.desc)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = upright.hashCode()
        result = 31 * result + reverse.hashCode()
        result = 31 * result + desc.contentHashCode()
        return result
    }
}
