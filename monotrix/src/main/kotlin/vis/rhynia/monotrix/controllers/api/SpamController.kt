package vis.rhynia.monotrix.controllers.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monotrix.components.FuncSpam
import vis.rhynia.monotrix.elements.web.ApiResponse
import vis.rhynia.monotrix.elements.web.post.PostUnify

@RestController
@RequestMapping("/api")
class SpamController(
    private val funcSpam: FuncSpam,
) {
    @GetMapping("/spam")
    fun getSpam(): ApiResponse = ApiResponse(funcSpam.fetchSpam("genshin", "none"))

    @PostMapping("/spam")
    fun postSpam(
        @RequestBody post: PostUnify,
    ): ApiResponse =
        ApiResponse(
            funcSpam.fetchSpam(
                type = post.sArr[0],
                code = post.sArr[1],
                limit = post.iArr[0],
            ),
        )
}
