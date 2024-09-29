package top.rhynia.monotrix.elements.data.tarot

import kotlinx.serialization.Serializable

@Serializable
data class TarotCard(
    val index: Int,
    val name: String,
    val loc: String,
    val img: String,
    val desc: TarotDesc
) {
    fun buildDrawn(imgEndpoint: String = "", reverse: Boolean = false) = TarotCardDrawn(
        index,
        name,
        loc,
        "$imgEndpoint/$img",
        reverse,
        desc
    )
}
