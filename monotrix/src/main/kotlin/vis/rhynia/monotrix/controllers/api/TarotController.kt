package vis.rhynia.monotrix.controllers.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monotrix.components.FuncTarot
import vis.rhynia.monotrix.elements.web.ApiResponse
import vis.rhynia.monotrix.elements.web.post.PostTarot

@RestController
@RequestMapping("/api")
class TarotController(
    private val funcTarot: FuncTarot,
) {
    @GetMapping("/tarot")
    fun getTarot(): ApiResponse = ApiResponse(funcTarot.draw("waite", true, 1)[0].name)

    @PostMapping("/tarot")
    fun postTarot(
        @RequestBody post: PostTarot,
    ): ApiResponse = funcTarot.draw(post)

    @GetMapping("/tarot/info")
    fun getTarotInfo(): ApiResponse = funcTarot.deckInfo()
}
