package top.rhynia.monotrix.elements.tarot

import kotlinx.serialization.Serializable

@Serializable
data class TarotDeck(
    val name: String,
    val loc: String,
    val full: Boolean,
    val hasR: Boolean,
    val deck: List<TarotCard>
)
