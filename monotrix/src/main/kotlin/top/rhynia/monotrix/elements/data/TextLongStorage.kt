package top.rhynia.monotrix.elements.data

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "str_save")
data class TextLongStorage(
    @Id
    val id: Long,
    @Column(name = "text")
    var text: String,
    @Column(name = "note")
    var note: String
)
