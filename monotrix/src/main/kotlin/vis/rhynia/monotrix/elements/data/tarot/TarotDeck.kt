package vis.rhynia.monotrix.elements.data.tarot

import kotlinx.serialization.Serializable

@Serializable
data class TarotDeck(
    val name: String,
    val loc: String,
    val full: Boolean,
    val hasR: Boolean,
    val deck: List<TarotCard>,
) {
    fun buildInfo(): TarotDeckInfo =
        TarotDeckInfo(
            name,
            loc,
            full,
        )
}
