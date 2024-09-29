package top.rhynia.monotrix.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.rhynia.monotrix.elements.web.ApiResponse

@RestController
@RequestMapping("/api")
class ApiController {
    @RequestMapping("/")
    fun root(): ApiResponse {
        return ApiResponse("API Root")
    }
}
