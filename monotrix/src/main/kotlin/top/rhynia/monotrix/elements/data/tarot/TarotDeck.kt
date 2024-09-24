package top.rhynia.monotrix.elements.data.tarot

import kotlinx.serialization.Serializable

@Serializable
data class TarotDeck(
    val name: String,
    val loc: String,
    val full: Boolean,
    val hasR: Boolean,
    val deck: List<TarotCard>
) {
    fun buildInfo(): TarotDeckInfo {
        return TarotDeckInfo(
            name = name,
            loc = loc,
            full = full,
        )
    }
}
