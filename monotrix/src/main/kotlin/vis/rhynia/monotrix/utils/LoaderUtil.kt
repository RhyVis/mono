package vis.rhynia.monotrix.utils

import kotlinx.serialization.json.Json

object LoaderUtil {
    fun jsonToStringMap(json: String): Map<String, String> = Json.decodeFromString<Map<String, String>>(json)

    fun jsonToArrayMap(json: String): Map<String, Array<String>> = Json.decodeFromString<Map<String, Array<String>>>(json)
}
