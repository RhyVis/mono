package vis.rhynia.monotrix.elements.web.post

data class PostSpam(
    val type: String,
    val code: String,
    val limit: Int = 1,
)
