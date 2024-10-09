package vis.rhynia.monotrix.enums

import vis.rhynia.monotrix.interfaces.Log
import vis.rhynia.monotrix.interfaces.Log.Companion.log

@Log
enum class CodexType {
    NONE,
    NMSL,
    TRAD,
    SPRK,
    UNID,
    NARY, ;

    companion object {
        fun tryGetValue(name: String): CodexType =
            try {
                valueOf(name)
            } catch (e: Exception) {
                log.error("Unrecognized type of $name", e)
                NONE
            }
    }
}
