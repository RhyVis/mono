package vis.rhynia.monotrix.controllers.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monotrix.components.FuncAuth
import vis.rhynia.monotrix.elements.web.ApiResponse
import vis.rhynia.monotrix.elements.web.post.PostStr

@RestController
@RequestMapping("/api")
class AuthController(
    private val funcAuth: FuncAuth,
) {
    @PostMapping("/auth")
    fun postValidation(
        @RequestBody post: PostStr,
    ): ApiResponse = ApiResponse(funcAuth.authHash(post.text))
}
