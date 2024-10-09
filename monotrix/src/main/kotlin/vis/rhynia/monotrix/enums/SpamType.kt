package vis.rhynia.monotrix.enums

import vis.rhynia.monotrix.interfaces.Log
import vis.rhynia.monotrix.interfaces.Log.Companion.log

@Log
enum class SpamType {
    NONE,
    ARKNIGHTS,
    GENSHIN,
    SPAM_MIN,
    SPAM_MAX,
    ACGN,
    DINNER, ;

    companion object {
        fun tryGetValue(name: String): SpamType =
            try {
                valueOf(name)
            } catch (e: Exception) {
                log.error("Unrecognized type of $name", e)
                NONE
            }
    }
}
