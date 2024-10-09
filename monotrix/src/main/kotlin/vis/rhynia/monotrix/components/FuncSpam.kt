package vis.rhynia.monotrix.components

import org.springframework.stereotype.Service
import vis.rhynia.monotrix.elements.data.entity.GachaAk
import vis.rhynia.monotrix.elements.data.entity.GachaGs
import vis.rhynia.monotrix.elements.data.entity.MemeAcgn
import vis.rhynia.monotrix.elements.data.entity.MemeDinner
import vis.rhynia.monotrix.elements.data.entity.SpamMax
import vis.rhynia.monotrix.elements.data.entity.SpamMin
import vis.rhynia.monotrix.elements.data.text.EntrySimple
import vis.rhynia.monotrix.elements.web.post.PostUnify
import vis.rhynia.monotrix.enums.SpamType
import vis.rhynia.monotrix.enums.SpamType.ACGN
import vis.rhynia.monotrix.enums.SpamType.ARKNIGHTS
import vis.rhynia.monotrix.enums.SpamType.DINNER
import vis.rhynia.monotrix.enums.SpamType.GENSHIN
import vis.rhynia.monotrix.enums.SpamType.SPAM_MAX
import vis.rhynia.monotrix.enums.SpamType.SPAM_MIN
import vis.rhynia.monotrix.interfaces.data.EntryText
import vis.rhynia.monotrix.interfaces.repository.GachaAkRepository
import vis.rhynia.monotrix.interfaces.repository.GachaGsRepository
import vis.rhynia.monotrix.interfaces.repository.MemeAcgnRepository
import vis.rhynia.monotrix.interfaces.repository.MemeDinnerRepository
import vis.rhynia.monotrix.interfaces.repository.SpamMaxRepository
import vis.rhynia.monotrix.interfaces.repository.SpamMinRepository

@Service
class FuncSpam(
    private val repoAk: GachaAkRepository,
    private val repoGs: GachaGsRepository,
    private val repoSpamMin: SpamMinRepository,
    private val repoSpamMax: SpamMaxRepository,
    private val repoAcgn: MemeAcgnRepository,
    private val repoDinner: MemeDinnerRepository,
    private val funcCodex: FuncCodex,
) {
    fun fetchSpam(
        type: String,
        code: String,
        limit: Int = 1,
    ): List<EntryText> {
        val t = SpamType.tryGetValue(type.ifBlank { "none" }.uppercase())
        return if (limit > 1) {
            when (t) {
                ARKNIGHTS -> fetchArknights(limit)
                GENSHIN -> fetchGenshin(limit)
                SPAM_MIN -> fetchSpamMin(limit)
                SPAM_MAX -> fetchSpamMax(limit)
                ACGN -> fetchAcgn(limit)
                DINNER -> fetchDinner(limit)
                else -> listOf(EntrySimple())
            }.map {
                EntrySimple(
                    it.id,
                    funcCodex.code(
                        PostUnify(
                            arrayOf(it.text, code),
                            intArrayOf(),
                            booleanArrayOf(true),
                        ),
                    ),
                )
            }
        } else {
            listOf(
                when (t) {
                    ARKNIGHTS -> fetchArknights()
                    GENSHIN -> fetchGenshin()
                    SPAM_MIN -> fetchSpamMin()
                    SPAM_MAX -> fetchSpamMax()
                    ACGN -> fetchAcgn()
                    DINNER -> fetchDinner()
                    else -> EntrySimple()
                }.apply {
                    text =
                        funcCodex.code(
                            PostUnify(
                                arrayOf(this.text, code),
                                intArrayOf(),
                                booleanArrayOf(true),
                            ),
                        )
                },
            )
        }
    }

    private fun fetchArknights(): GachaAk = repoAk.findRand() ?: GachaAk(0, "Empty")

    private fun fetchArknights(limit: Int): List<GachaAk> = repoAk.findRand(limit)

    private fun fetchGenshin(): GachaGs = repoGs.findRand() ?: GachaGs(0, "Empty")

    private fun fetchGenshin(limit: Int): List<GachaGs> = repoGs.findRand(limit)

    private fun fetchSpamMin(): SpamMin = repoSpamMin.findRand() ?: SpamMin(0, "Empty")

    private fun fetchSpamMin(limit: Int): List<SpamMin> = repoSpamMin.findRand(limit)

    private fun fetchSpamMax(): SpamMax = repoSpamMax.findRand() ?: SpamMax(0, "Empty")

    private fun fetchSpamMax(limit: Int): List<SpamMax> = repoSpamMax.findRand(limit)

    private fun fetchAcgn(): MemeAcgn = repoAcgn.findRand() ?: MemeAcgn(0, "Empty")

    private fun fetchAcgn(limit: Int): List<MemeAcgn> = repoAcgn.findRand(limit)

    private fun fetchDinner(): MemeDinner = repoDinner.findRand() ?: MemeDinner(0, "Empty")

    private fun fetchDinner(limit: Int): List<MemeDinner> = repoDinner.findRand(limit)
}
