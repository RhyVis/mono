package top.rhynia.monotrix.util

import kotlinx.serialization.json.Json

object LoaderUtil {
    fun jsonToStringMap(json: String): Map<String, String> {
        return Json.decodeFromString<Map<String, String>>(json)
    }

    fun jsonToArrayMap(json: String): Map<String, Array<String>> {
        return Json.decodeFromString<Map<String, Array<String>>>(json)
    }
}
