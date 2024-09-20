package top.rhynia.monotrix.elements.data

import top.rhynia.monotrix.interfaces.data.TextSimpleEntry

data class TextSimpleData(
    override val id: Long = 0,
    override var text: String = "Dummy",
): TextSimpleEntry