package top.rhynia.monotrix.elements.web.json

import kotlinx.serialization.Serializable

@Serializable
data class ConfMapping(
    val mappings: Map<String, String>
)
