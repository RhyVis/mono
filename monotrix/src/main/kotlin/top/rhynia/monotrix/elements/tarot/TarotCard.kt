package top.rhynia.monotrix.elements.tarot

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
        index = this.index,
        name = this.name,
        loc = this.loc,
        img = imgEndpoint + "/" + this.img,
        rev = reverse,
        desc = this.desc
    )
}
