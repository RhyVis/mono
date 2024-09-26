package top.rhynia.monotrix.elements

data class ApiResponse(
    val code: Int,
    val data: Any
) {
    /**
     * Return a 0, 0 only object
     */
    constructor() : this(0, 0)

    /**
     * Return a 0, data object
     */
    constructor(data: Any) : this(0, data)
}
