package vis.rhynia.monotrix.controllers.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monotrix.components.FuncNav
import vis.rhynia.monotrix.elements.web.ApiResponse

@RestController
@RequestMapping("/api")
class NavController(
    private val funcNav: FuncNav,
) {
    @GetMapping("/nav")
    fun navIndexList(): ApiResponse = funcNav.getIndexList()
}
