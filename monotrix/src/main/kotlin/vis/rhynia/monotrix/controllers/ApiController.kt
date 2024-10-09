package vis.rhynia.monotrix.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monotrix.elements.web.ApiResponse

@RestController
@RequestMapping("/api")
class ApiController {
    @RequestMapping("/")
    fun root(): ApiResponse = ApiResponse("API Root")
}
