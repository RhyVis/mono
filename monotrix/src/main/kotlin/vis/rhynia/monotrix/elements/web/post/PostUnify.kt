package vis.rhynia.monotrix.elements.web.post

data class PostUnify(
    var sArr: Array<String>,
    var iArr: IntArray,
    var bArr: BooleanArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostUnify

        if (!sArr.contentEquals(other.sArr)) return false
        if (!iArr.contentEquals(other.iArr)) return false
        if (!bArr.contentEquals(other.bArr)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sArr.contentHashCode()
        result = 31 * result + iArr.contentHashCode()
        result = 31 * result + bArr.contentHashCode()
        return result
    }
}
