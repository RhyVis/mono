package top.rhynia.monotrix.components

import cn.hutool.http.HttpUtil
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import top.rhynia.monotrix.configs.MainConf
import top.rhynia.monotrix.elements.data.tarot.TarotCardDrawn
import top.rhynia.monotrix.elements.data.tarot.TarotDeck
import top.rhynia.monotrix.elements.data.tarot.TarotDeckInfo
import top.rhynia.monotrix.elements.web.json.ConfMapping
import top.rhynia.monotrix.elements.web.post.PostTarot
import top.rhynia.monotrix.elements.web.result.PackedResult
import top.rhynia.monotrix.interfaces.Log
import top.rhynia.monotrix.interfaces.Log.Companion.log
import kotlin.random.Random

@Log
@Component
class FuncTarot(private val conf: MainConf) {
    private val decks = mutableMapOf<String, TarotDeck>()
    private val deckMainOnly = mutableMapOf<String, Boolean>()
    private val deckInfo = mutableMapOf<String, TarotDeckInfo>()

    fun init() {
        val mainConfUri = UriComponentsBuilder.fromHttpUrl(conf.endpoint)
            .pathSegment("tarot", "conf", "_conf.json").encode().build().toUriString()
        val mainConf = Json.decodeFromString<ConfMapping>(HttpUtil.get(mainConfUri))
        for (entry in mainConf.mappings) {
            val deckFileUri = UriComponentsBuilder.fromHttpUrl(conf.endpoint)
                .pathSegment("tarot", "conf", entry.value).encode().build().toUriString()
            val deck = Json.decodeFromString<TarotDeck>(HttpUtil.get(deckFileUri))
            decks[entry.key] = deck
            deckInfo[entry.key] = deck.buildInfo()
            if (deck.full) {
                val altName = deck.name + "_main"
                val alt = TarotDeck(
                    altName,
                    deck.loc,
                    false,
                    deck.hasR,
                    deck.deck.slice(IntRange(0, 21))
                )
                decks[altName] = alt
                deckMainOnly[entry.key] = false
            } else {
                deckMainOnly[entry.key] = true
            }
        }

        log.info("Processed ${decks.size} decks")
        for (entry in decks.entries) {
            log.info(" > ${entry.key}: ${entry.value.deck.size}")
        }
    }

    fun draw(deck: String, full: Boolean, count: Int): List<TarotCardDrawn> {
        return if (!deckMainOnly.containsKey(deck)) {
            emptyList()
        } else {
            if (deckMainOnly[deck]!!) {
                drawShuffled(deck, count)
            } else {
                if (full) {
                    drawShuffled(deck, count)
                } else {
                    drawShuffled(deck + "_main", count)
                }
            }
        }
    }

    fun draw(post: PostTarot): PackedResult {
        log.info("Received request of $post")
        val r = draw(post.deck, post.full, post.count)
        log.info("Drawn ${r.map { it.name }}")
        return PackedResult(r)
    }

    fun deckInfo(): PackedResult {
        return PackedResult(deckInfo)
    }

    private fun drawShuffled(deck: String, count: Int): List<TarotCardDrawn> {
        val deckEntity = decks[deck]!!
        val deckShuffled = deckEntity.deck.shuffled()
        val deckPicked = deckShuffled.take(count)
        val deckImgPrefix = UriComponentsBuilder.fromHttpUrl(conf.endpoint)
            .pathSegment("tarot", "img").encode().build().toUriString()
        return if (deckEntity.hasR) {
            deckPicked.map { it.buildDrawn(deckImgPrefix, Random.nextBoolean()) }
        } else {
            deckPicked.map { it.buildDrawn(deckImgPrefix) }
        }
    }
}
