package top.rhynia.monotrix.elements.data.text

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "str_nav")
data class EntryStrNav(
    @Id
    val id: Long,
    @Column(name = "data")
    var data: String,
    @Column(name = "label")
    var label: String,
    @Column(name = "note")
    var note: String
)
