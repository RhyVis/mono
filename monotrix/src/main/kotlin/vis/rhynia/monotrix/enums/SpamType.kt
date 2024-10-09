package vis.rhynia.monotrix.enums

import vis.rhynia.monotrix.interfaces.Log
import vis.rhynia.monotrix.interfaces.Log.Companion.log

@Log
enum class SpamType(
    loc: String,
) {
    NONE(""),
    ARKNIGHTS("明日方舟"),
    GENSHIN("原神"),
    SPAM_MIN("小喷怡情"),
    SPAM_MAX("火力全开"),
    ACGN("反二圣经"),
    DINNER("复制粘贴"), ;

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
