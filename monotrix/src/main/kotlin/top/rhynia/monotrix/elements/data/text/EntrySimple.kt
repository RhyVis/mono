package top.rhynia.monotrix.elements.data.text

import top.rhynia.monotrix.interfaces.data.EntryText

data class EntrySimple(
    override val id: Long = 0,
    override var text: String = "Dummy",
) : EntryText
